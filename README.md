Homework 2 - Chisel State
=======================

Adopting our agile mindset, some of these problems revise components introduced in prior homework assignments. Although we provide a skeleton for testers, you will often need to implement them in order to use them. Be sure not to modify external IO interfaces to maintain compatability with the autograder.

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
> The goal of these methods is to overload the `+` and `-` operators so we can easily work with `ComplexNum` in future modules while hiding implementation details. Make sure the arithmetic methods allow for bit growth.


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
> Ensure the width of `out` allows for bit growth. For help with optional IO see: https://github.com/ucb-bar/chisel3-wiki/blob/master/Cookbook.md.

### Part 3 - ComplexALU 
> Implement the `ComplexALU` module in `src/main/scala/hw2/Complex.scala` using only the methods defined in `ComplexNum` to perform arithmetic.
>> - if `doAdd` sum the real inputs and sum the imaginary inputs
>> - if not `doAdd` find difference between the real inputs and the difference between the imaginary inputs
>> - if `onlyAdder` only generate logic to sum the real inputs and sum the imaginary inputs. Since we no longer need `doAdd` it should be absent from the Verilog.

# Problem 2 - Improved PolyEval (40pts)
> Let's enhance our `PolyEval` from HW1 to support arbitrary polynomials. Implement the `PolyEval` module in `src/main/scala/hw2/PolyEval.scala`. The `coefs` parameter is a list of coefficients ordered by ascending exponent powers. Return the evaluation in a single cycle. 
> 
> For example: 
> ```
>   Coefs = Seq(0, 1, 2)
>   x = 5
>   out = 0*x^0 + 1*x^1 + 2*x^2 = 0 + 5 + 50 = 55

# Problem 3 - Sine Wave Generator (40pts)
> Sine waves are useful in DSPs. For this problem, we will implement a sine wave generator (`SinGen`), and over multiple cycles, it will output values for a sine wave. Internally, it will be implemented as a lookup table (ROM with `n` precomputed `sin(x)` values) with a counter. Use the provided `SineWave` code to populate a ROM in the `SinGen` module located in `src/main/scala/hw2/SineWave.scala`. 

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
> Implement the `SinGen` bundle in `src/main/scala/hw2/SineWave.scala`. Use `SinGenIO` as the module's IO. Each cycle the module will output a new value if `en` is high, or keep returning the same value if `en` is low. The `stride` input determines how much step through the ROM by each cycle.
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
> The XOR operation is the basis of a simple cryptographic encryption algorithm called an XOR cipher. Given a secret `key` and an input `in` of the same length, we can encrypt `in` by performing `ciphertext = in ^ key`. We can decrypt `ciphertext` by performing `in = ciphertext ^ key`. More details here: https://en.wikipedia.org/wiki/XOR_cipher. 

> In this problem we will implement an encrypted memory that utilizes a XOR cipher. The contents of memory will always be encrypted, and the user must provide the `key` as an input. Upon reset, the module initializes itself by _cleaning_ its `Mem` by writing `0.U` to each of its `memSize` words. The module will then wait until a command is received. To encode commands, we use the following input signals:
> - `rst`: clean the memory (different than implicit reset)
> - `enc`: encrypt `in` with `key` and write the result to memory
> - `read`: read (ciphertext) from memory and output the data unmodified
> - `dec`: read from memory and output the data decrypted with `key`

> Note that there is no address input. The memory will always be accessed in its entirety. Each state will cycle through all of the `memSize` words in order, so we must internally track which word we are on.


### Part 1 - XORCipherSigs 
> Implement the `XORCipherCmds` bundle in `src/main/scala/hw2/XORCipher.scala` by adding four `Input` fields: 
> ```
>   rst:  Bool
>   enc:  Bool
>   read: Bool
>   dec:  Bool
> ```
> 
> These are the input signals to our FSM.

### Part 2 - XORCipherIO 
> Implement the `XORCipherIO` bundle in `src/main/scala/hw2/XORCipher.scala` by adding three `Input` fields: 
> ```
>   in:   UInt
>   key:  UInt
>   cmds: XORCipherCmds
> ```
> and two `Output` fields:
> ```
>   out:   UInt
>   state: UInt

### Part 3 - XORCipher
> Implement the `XORCipher` module in `src/main/scala/hw2/XORCipher.scala` using `XORCipherIO` as the IO. Instantiate a `Mem` with `memSize` words of width `wordSize`. We will build a FSM with five states:
> - `cleanMem`: write 0.U to each word in Mem over memSize cycles
> - `ready`: wait until a command is received 
> - `writeAndEncrypt`: write io.in ^ io.key to each word in Mem over memSize cycles
> - `readAndDecrypt`: output Mem[word] ^ io.key for each word in Mem over memSize cycles
> - `readCiphertext`: output Mem[word] for each word in Mem over memSize cycles

> The state transitions will follow this diagram:
> ![Alt text](fsm.png?raw=true "Title")

> Whenever the `rst` command is received, the state the next cycle should be `cleanMem`. When other commands are received in states other than `ready`, they can be ignored until the current operation completes. The FSM diagram uses the signal `done` to convey a given operation has completed. You can assume the FSM will go to `ready` for at least one cycle between commands. In the `ready` state, we use the following priorities on our input commands (to ensure our data's security):
> ```
>   rst > enc > read > dec 
> ```
> For example if we are in the `ready` state and both the `enc` and `read` signals are high, we will transition to the `writeAndEncrypt` state.

> You may use the tester located in `src/test/scala/hw2/XORCipherTestSuite.scala` to drive your development.
