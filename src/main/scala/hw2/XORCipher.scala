package hw2

import chisel3._
import chisel3.util._


object CipherState extends ChiselEnum {
  val empty, ready, encrypted, decrypted = Value
}

class XORCipherCmds extends Bundle {
  val clear      = Input(Bool())
  val loadKey    = Input(Bool())
  val loadAndEnc = Input(Bool())
  val decrypt    = Input(Bool())
}


/**
  * @param width :    Int
  * @field in:        UInt           (Input) - payload or key
  * @field cmds:      XORCipherCmds  (Input)
  * @field out:       UInt           (Output)
  * @field full:      Bool           (Output)
  * @field encrypted: Bool           (Output)
  * @field state:     CipherState    (Output) - visible for testing
  */
class XORCipherIO(width: Int) extends Bundle {
  ???
}


/**
  * @param width Int
  */
class XORCipher(width: Int) extends Module {
  val io = IO(new XORCipherIO(width))

  ???
}
