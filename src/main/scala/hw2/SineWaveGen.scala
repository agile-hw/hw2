package hw2

import chisel3._
import chisel3.util.log2Ceil


class SineWave(val period: Int, val amplitude: Int) {
  require(period > 0)
  val B: Double = (2.0 * math.Pi) / period.toDouble

  def apply(index: Int): Int = (amplitude.toDouble * math.sin(B * index)).toInt
}


/**
  *
  * @param s : SineWave (internally contains period & amplitude)
  * ________________________________
  * @field stride:  UInt      (Input)
  * @field en:      Bool      (Input)
  * @field out:     SInt      (Output)
  */
class SineWaveGenIO (sw: SineWave) extends Bundle {
  ???
}


/**
  * 
  * @param s : SineWave (internally contains period)
  */
class SineWaveGen(sw: SineWave) extends Module {
  val io = ???
}
