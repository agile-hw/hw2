Homework 2 - Chisel State
=======================

Adopting our agile mindset, some of these problems revise components introduced in prior homework assignments. Although we provide a skeleton for testers, you will need to implement them in order to use them. Be sure not to modify external IO interfaces to maintain compatability with the autograder.

# Problem 1 - Improved ComplexALU (50pts)
> Let's enhance our `ComplexALU` from HW1 using `Bundle`s and encapsulation. This problem will consist of multiple parts that build from each other. 
### Part 1 - ComplexNum 
> Implement the `ComplexNum` bundle in `src/main/scala/hw2/Complex.scala` by adding two `SInt` fields: `real` and `imag` and four methods with the following signatures:
> ```scala
>    def sumReal(that:  ComplexNum): SInt 
>    def sumImag(that:  ComplexNum): SInt 
>    def diffReal(that: ComplexNum): SInt
>    def diffImag(that: ComplexNum): SInt
> ``` 
> The goal of these methods is to effectively overload the `+` and `-` operators so we can easily work with `ComplexNum` in future modules while hiding implementation details. Make sure the arithmetic methods allow for bit width growth.

### Part 2 - ComplexALUIO
> Implement the `ComplexALUIO` bundle in `src/main/scala/hw2/Complex.scala` by adding three `Input` fields: 
> ```scala
>   doAdd: Option[Bool]
>   c0: ComplexNum
>   c1: ComplexNum
> ```
> and one `Output` field: 
>   ```scala
>   out: ComplexNum
> ```
> Ensure the width of `out` allows for bit width growth. For help with optional IO see the [cookbook](https://www.chisel-lang.org/chisel3/docs/cookbooks/cookbook.html#how-do-i-create-an-optional-io).

### Part 3 - ComplexALU 
> Implement the `ComplexALU` module in `src/main/scala/hw2/Complex.scala` using only the methods defined in `ComplexNum` to perform arithmetic. It will behave similarly to HW1, except that if the `onlyAdder` parameter is true, the generated hardware will not even include a port for `doAdd`.
>> - if `doAdd` is high, sum the real inputs and sum the imaginary inputs
>> - if `doAdd` is low, find difference between the real inputs and the difference between the imaginary inputs
>> - if `onlyAdder` is true, only generate logic to sum the real inputs and sum the imaginary inputs. Since we no longer need `doAdd`, it should be absent from the Verilog.


# Problem 2 - Improved PolyEval (40pts)
> Let's enhance our `PolyEval` from HW1 to support arbitrary polynomials. Implement the `PolyEval` module in `src/main/scala/hw2/PolyEval.scala`. The `coefs` parameter is a list of coefficients ordered by ascending exponent powers. The generated hardware should produce the result combinatorally (within a cycle). 
> 
> For example: 
> ```
>   coefs = Seq(4, 5, 6)
>   x = 2
>   out = 4*x^0 + 5*x^1 + 6*x^2 = 4 + 10 + 24 = 38


# Problem 3 - Sine Wave Generator (40pts)
> Sine waves are useful in DSPs, and in this problem, we will implement a sine wave generator (`SinGen`). Over multiple cycles, the generated hardware will produce the output values for a sine wave. Internally, it will track where it is in the period, and use that to index into a lookup table. The lookup table will hold a single period of the sine wave sampled at `n` points, and those precomputed `sin(x)` values will be stored in a ROM. Use the provided `SineWave` code to populate a ROM in the `SinGen` module located in `src/main/scala/hw2/SineWave.scala`. 

### Part 1 - SinGenIO 
> Implement the `SinGenIO` bundle in `src/main/scala/hw2/SineWave.scala` by adding two `Input` fields: 
> ```scala
>   stride: UInt
>   en: Bool
> ```
> and one `Output` field: 
>   ```scala
>   out: SInt
> ``` 

### Part 2 - SinGen 
> Implement the `SinGen` module in `src/main/scala/hw2/SineWave.scala`. Use `SinGenIO` as the module's IO. Each cycle the module will output the next sample if `en` is high, or keep returning the same sample if `en` is low. The `stride` input determines how many samples to step through the ROM each cycle.
>> Example given these parameters:
>> ```scala
>>     val period = 8
>>     val amplitude = 128
>>     val n = 36
>> ```
>> If `stride` is `1` then `SinGen` will return one value each cycle in this order:
>> ```
>> 0, 48, 90, 118, 128, 118, 90, 48, 0, -48, -90, -118, -128, -118, -90, -48
>> ```
>> If `stride` is `2` then `SinGen` will return one value each cycle in this order:
>> ```
>> 0, 90, 128, 90, 0, -90, -128, -90
>> ```


# Problem 4 - XOR Cipher (60pts)
> The XOR operation is the basis of a simple cryptographic encryption algorithm called an [XOR cipher](https://en.wikipedia.org/wiki/XOR_cipher). Given a secret `key` and `data` of the same length, we can encrypt `data` by performing `ciphertext = in ^ key`. We can decrypt `ciphertext` by performing `in = ciphertext ^ key`.

> We will implement a simple XOR cipher. Inside the generated hardware, there will be a register to hold the data (potentially encrypted) and a register to hold the key. We will use a state machine internally to keep track of the status of the system. Upon reset, the system will wait until it is given a secret key to load in. With the secret key stored inside, it is now ready to accept data. When given data, it will encrypt it on the way in. The data can be decrypted, but after a cycle it must be overwritten or zeroed out.
> 
> To encode commands, we use the following input signals:
> - `clear`: zero out both the _data_ and the secret _key_
> - `loadKey`: store `in` into the secret _key_
> - `loadAndEnc`: store `in` encrypted (via XOR with the secret _key_) into _data_
> - `decrypt`: decrypt _data_ with the secret `key`

### Part 1 - XORCipherIO 
> Implement the `XORCipherIO` bundle in `src/main/scala/hw2/XORCipher.scala` by adding two `Input` fields: 
> ```
>   in:   UInt
>   cmds: XORCipherCmds
> ```
> and four `Output` fields:
> ```
>   out:       UInt (output of data register)
>   full:      Bool (data register has valid data)
>   encrypted: Bool (data register has encrypted data)
>   state:     UInt (eases testing of FSM)

### Part 3 - XORCipher
> Implement the `XORCipher` module in `src/main/scala/hw2/XORCipher.scala` using `XORCipherIO` as the IO. We will build a FSM with four states:
> - `clear`: data and key are both 0
> - `ready`: secret key is set
> - `encrypted`: data is stored encrypted
> - `decypted`: data is stored decrypted

> The state transitions will follow this diagram:
<img src="fsm.svg" alt="fsm schematic" style="width:30%;margin-left:auto;margin-right:auto"/>

> In general, at most one signal in XORCipherCmds should be high at a time, but use the following precedence order when multiple are high:
> ```
>   clear > loadKey > loadAndEncrypt > decrypt 
> ```
> For example, any time `clear` is seen, flush the contents and go to the `empty` state`. If none of the transition conditions are satisfied, remain in the present state.

> You may use the tester (after filling it in) located in `src/test/scala/hw2/XORCipherTestSuite.scala` to drive your development.
