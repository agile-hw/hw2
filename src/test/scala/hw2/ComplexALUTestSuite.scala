// See README.md for license details.

package hw2

import chisel3._
import chisel3.tester._
import chisel3.tester.RawTester.test
import chisel3.experimental.BundleLiterals._

object ComplexALUBehavior {
    def testComplexALUrealOutOnlyAdder(): Boolean = {
        test(new ComplexALU(width=5, onlyAdder=true)) { dut =>
            ???
        }
        true
    }
    
    def testComplexALUrealOut(): Boolean = {
        test(new ComplexALU(width=5, onlyAdder=false)) { dut =>
            ???
        }
        true
    }
    
    def testComplexALUimagOutOnlyAdder(): Boolean = {
        test(new ComplexALU(width=5, onlyAdder=true)) { dut =>
            ???
        }
        true
    }
    
    def testComplexALUimagOut(): Boolean = {
        test(new ComplexALU(width=5, onlyAdder=false)) { dut =>
            ???
        }
        true
    }
}


