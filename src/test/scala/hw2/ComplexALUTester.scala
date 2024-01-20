package hw2

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class ComplexALUTester extends AnyFlatSpec with ChiselScalatestTester {
  behavior of "ComplexALU"
  it should "correctly calculate realOut onlyAdd=true" in {
    test(new ComplexALU(width = 5, onlyAdder = true)) { dut =>
      ???
    }
  }

  it should "correctly calculate realOut onlyAdd=false" in {
    test(new ComplexALU(width=5, onlyAdder=false)) { dut =>
      ???
    }
  }

  it should "correctly calculate imagOut onlyAdd=true" in {
    test(new ComplexALU(width=5, onlyAdder=true)) { dut =>
      ???
    }
  }

  it should "correctly calculate imagOut onlyAdd=false" in {
    test(new ComplexALU(width=5, onlyAdder=false)) { dut =>
      ???
    }
  }
}
