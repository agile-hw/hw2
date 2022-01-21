package hw2

import chisel3._
import chisel3.util._


class XORCipherCmds extends Bundle {
    val clear      = Input(Bool())
    val loadKey    = Input(Bool())
    val loadAndEnc = Input(Bool())
    val decrypt    = Input(Bool())
}


/**
  * @param width:     Int
  * @field in:        UInt           (Input) - payload or key
  * @field cmds:      XORCipherCmds  (Input)
  * @field out:       UInt           (Output)
  * @field full:      Bool           (Output)
  * @field encrypted: Bool           (Output)
  * @field state:     UInt           (Output) - visible for testing
  */
class XORCipherIO(width: Int) extends Bundle {
    ???
}


object XORCipher {
    // States
    val empty :: ready :: encrypted :: decrypted :: Nil = Enum(4)
}


/**
  * @param width Int
  */
class XORCipher(width: Int) extends Module {
    val io = IO(new XORCipherIO(width))
    
    // import state names into namespace
    import XORCipher._
    
    ???
}
