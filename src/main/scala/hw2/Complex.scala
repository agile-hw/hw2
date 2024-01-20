package hw2

import chisel3._

// Note ??? will compile but not work at runtime.

/**
  * @param width : Int
  * ___________________
  * @field real:  SInt
  * @field imag:  SInt
  * @method def sumReal(that:  ComplexNum): SInt 
  * @method def sumImag(that:  ComplexNum): SInt 
  * @method def diffReal(that: ComplexNum): SInt
  * @method def diffImag(that: ComplexNum): SInt
  */
class ComplexNum(width: Int) extends Bundle {
  ???
}


/**
  * @param width     : Int
  * @param onlyAdder : Boolean
  * ___________________
  * @field doAdd: Option[Bool]  (Input)
  * @field c0:  ComplexNum      (Input)
  * @field c1:  ComplexNum      (Input)
  * @field out: ComplexNum      (Output)
  */
class ComplexALUIO(width: Int, onlyAdder: Boolean) extends Bundle {
  ???
}


/**
  * @param width     : Int
  * @param onlyAdder : Boolean
  */
class ComplexALU(width: Int, onlyAdder: Boolean) extends Module {
  val io = IO(new ComplexALUIO(width, onlyAdder))
  ???
}
