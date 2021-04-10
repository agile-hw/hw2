package hw2

import chisel3._
import chisel3.util._

/**
  * @field rst:  Bool
  * @field enc:  Bool
  * @field read: Bool
  * @field dec:  Bool
  */
class XORCipherCmds extends Bundle {
    ???
    override def cloneType = (new XORCipherCmds).asInstanceOf[this.type]
}

/**
  * @param wordSize: Int
  * @field in:       UInt           (Input)
  * @field key:      UInt           (Input)
  * @field cmds:     XORCipherCmds  (Input)
  * @field out:      UInt           (Output)
  * @field state:    UInt           (Output)
  */
class XORCipherIO(wordSize: Int) extends Bundle {
    ???
    override def cloneType = (new XORCipherIO(wordSize)).asInstanceOf[this.type]
}

object XORCipher {
    // States
    val cleanMem :: ready :: writeAndEncrypt :: readAndDecrypt :: readCiphertext :: Nil = Enum(5)
}
/**
  * @param memSize: Int
  * @param wordSize: Int
  */
class XORCipher(memSize: Int, wordSize: Int) extends Module {
    val io = IO(new XORCipherIO(wordSize))
    ???
}