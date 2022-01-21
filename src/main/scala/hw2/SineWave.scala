package hw2

import chisel3._
import chisel3.util.log2Ceil


class SineWave(val period: Int, val amplitude: Int) {
    require(period > 0)
    val B: Double = (2.0 * math.Pi) / period.toDouble
  
    def apply(x: Double): Int = {
        (amplitude.toDouble * math.sin(B * x)).toInt
    }

    def sample(n: Int): Seq[Int] = {
        val sampleInterval = period.toDouble / n.toDouble
        (0 until n).map(x => apply(x * sampleInterval))
    }
}


/**
  * 
  * @param s: SineWave 
  * @param n: The total number of points sampled -> ROM 
  * ________________________________
  * @field stride:  UInt      (Input)
  * @field en:      Bool      (Input)
  * @field out:     SInt      (Output)
  */
class SinGenIO (s: SineWave, n: Int) extends Bundle {
    ???
}


/**
  * 
  * @param s: SineWave 
  * @param n: The total number of points sampled -> ROM
  */
class SinGen(s: SineWave, n: Int) extends Module {
    val io = ???
}
