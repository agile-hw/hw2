package hw2

import chisel3._
import chisel3.tester._
import chisel3.tester.RawTester.test
import chisel3.experimental.BundleLiterals._

object SinGenBehavior {
    def testSinGenStride(stride: Int): Unit = {
        val s = ???
        val n = ???
        test(new SinGen(s, n)) { dut =>
          ???
        }
    }

    behavior of "SinGen"
    it should "correctly calculate output for stride=1" in {
        testSinGenStride(1)
    }

    it should "correctly calculate output for stride=2" in {
        testSinGenStride(2)
    }

    it should "correctly calculate output for stride=4" in {
        testSinGenStride(4)
    }
}
