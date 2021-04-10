package hw2

// See README.md for license details.

import chisel3._
import chisel3.tester._
import chisel3.tester.RawTester.test
import chisel3.experimental.BundleLiterals._

object PolyEvalBehavior {
    val width = ???
    def testPolyEvalOut(n: Int): Boolean = {
        val coefs = ???
        test(new PolyEval(coefs, width)) { dut =>
            ???
        }
        true
    } 

    def testPolyEvalDeg2 = testPolyEvalOut(3)
    def testPolyEvalDeg3 = testPolyEvalOut(4)
    def testPolyEvalDeg4 = testPolyEvalOut(5)
    def testPolyEvalDeg5 = testPolyEvalOut(6)
}
