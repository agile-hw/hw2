package hw2

import chisel3._


/**
  * 
  * @param coefs : in ascending exponent order -> Seq(1, 2, 3) == 1 + 2x + 3x^2
  * @param width : the width of x
  */
class PolyEval(coefs: Seq[Int], width: Int) extends Module {
  val io = IO(new Bundle {
    val x      = Input(UInt(width.W))
    val out    = Output(UInt())
  })

  ???
}
