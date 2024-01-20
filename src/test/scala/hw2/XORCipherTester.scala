package hw2

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class XORCipherTester extends AnyFlatSpec with ChiselScalatestTester {
  val key  = ???
  val data = ???
  val width = ???

  behavior of "XORCipher"
  it should "go through common case (empty -> ready -> encrypted -> decrypted -> empty" in {
    test(new XORCipher(width)).withAnnotations(Seq(WriteVcdAnnotation)) { dut =>
      // empty -> ready
      dut.io.state.expect(CipherState.empty)
      dut.io.in.poke(key.U)
      dut.io.cmds.clear.poke(false.B)
      dut.io.cmds.loadKey.poke(true.B)
      dut.io.cmds.loadAndEnc.poke(false.B)
      dut.io.cmds.decrypt.poke(false.B)
      dut.io.out.expect(0.U)
      dut.io.full.expect(false.B)
      dut.io.encrypted.expect(false.B)
      dut.clock.step()

      ???
    }
  }

  // You will want to create additional tests to cover the other FSM arcs
}
