package hw2

import chisel3._
import chisel3.tester._
import chisel3.tester.RawTester.test
import chisel3.experimental.BundleLiterals._

object SinGenBehavior {
    def testSinGenStride(stride: Int): Boolean = {
        true
    }
    def testSinGenStride1: Boolean = testSinGenStride(1)

    def testSinGenStride2: Boolean = testSinGenStride(2)

    def testSinGenStride4: Boolean = testSinGenStride(4)
}
   