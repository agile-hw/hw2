// See README.md for license details.

package hw2

import chisel3.tester._
import org.scalatest.FreeSpec

class HW2Tester extends FreeSpec with ChiselScalatestTester {
    "ComplexALU should correctly calculate realOut onlyAdd=true" in {
        assert(ComplexALUBehavior.testComplexALUrealOutOnlyAdder)
    }

    "ComplexALU should correctly calculate realOut onlyAdd=false" in {
        assert(ComplexALUBehavior.testComplexALUrealOut)
    }

    "ComplexALU should correctly calculate imagOut onlyAdd=true" in {
        assert(ComplexALUBehavior.testComplexALUimagOutOnlyAdder)
    }

    "ComplexALU should correctly calculate imagOut onlyAdd=false" in {
        assert(ComplexALUBehavior.testComplexALUimagOut)
    }

    "PolyEval should correctly calculate output for deg(2) poly" in {
        assert(PolyEvalBehavior.testPolyEvalDeg2)
    }

    "PolyEval should correctly calculate output for deg(3) poly" in {
        assert(PolyEvalBehavior.testPolyEvalDeg3)
    }

    "PolyEval should correctly calculate output for deg(4) poly" in {
        assert(PolyEvalBehavior.testPolyEvalDeg4)
    }

    "PolyEval should correctly calculate output for deg(5) poly" in {
        assert(PolyEvalBehavior.testPolyEvalDeg5)
    }

    "SinGen should correctly calculate output for stride=1" in {
        assert(SinGenBehavior.testSinGenStride1)
    }

    "SinGen should correctly calculate output for stride=2" in {
        assert(SinGenBehavior.testSinGenStride2)
    }

    "SinGen should correctly calculate output for stride=4" in {
        assert(SinGenBehavior.testSinGenStride4)
    }

    "XORCipher FSM is correct if everything passes in" in {
        val n = 64
        val wordSize = 32
        assert(XORCipherBehavior.testXORCipher(n, wordSize))
    }

    "XORCipher FSM state transitions are correct if everything passes in" in {
        val n = 64
        val wordSize = 32
        assert(XORCipherBehavior.testXORCipherStates(n, wordSize))
    }
}
