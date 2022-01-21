package hw2

// See README.md for license details.

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

object PolyEvalBehavior {
    val width = ???
    def testPolyEvalOut(n: Int): Unit = {
        val coefs = ???
        test(new PolyEval(coefs, width)) { dut =>
            ???
        }
    }

    behavior of "PolyEval"
    it should "correctly calculate output for deg(2) poly" in {
      testPolyEvalOut(3)
    }

    it should "correctly calculate output for deg(3) poly" in {
      testPolyEvalOut(4)
    }

    it should "correctly calculate output for deg(4) poly" in {
      testPolyEvalOut(5)
    }

    it should "correctly calculate output for deg(5) poly" in {
      testPolyEvalOut(6)
    }
}