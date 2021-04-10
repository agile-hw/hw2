package hw2

import chisel3._
import math.{sin, Pi}
import chisel3.util.log2Ceil
import Chisel.log2Up

class SineWave(val period: Int, val amplitude: Int) {
    require(period > 0)
    val B = (2.0 * Pi) / period.toDouble 
    
    def apply(x: Double): Int = {
        (this.amplitude.toDouble * sin(this.B * x)).toInt
    }

    def sample(n: Int): Seq[Int] = {
        val freq = this.period.toDouble / n.toDouble
        (0 until n).map(x => this.apply(x * freq))
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
    override def cloneType = (new SinGenIO(s, n)).asInstanceOf[this.type]
}

/**
  * 
  * @param s: SineWave 
  * @param n: The total number of points sampled -> ROM
  */
class SinGen(s: SineWave, n: Int) extends Module {
    val io = ???
}

