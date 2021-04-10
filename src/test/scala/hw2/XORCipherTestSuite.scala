package hw2

import chisel3._
import chisel3.tester._
import chisel3.tester.RawTester.test
import chisel3.experimental.BundleLiterals._

object XORCipherBehavior {
    def pokeCmd(s : XORCipherCmds, in: (Boolean, Boolean, Boolean, Boolean)) = {
        val (rst, enc, read, dec) = in
        s.poke(chiselTypeOf(s).Lit(_.rst -> rst.B,
                                           _.enc -> enc.B,
                                           _.read -> read.B,
                                           _.dec -> dec.B))
    }

    val none      = (false, false, false, false)
    val reset     = (true, false, false, false)
    val encrypt   = (false, true, false, false)
    val read      = (false, false, true, false)
    val decrypt   = (false, false, false, true)
    val testStates = Seq(decrypt, read, encrypt)
    
    def testXORCipher(memSize: Int, wordSize: Int): Boolean = {
        require(memSize >= testStates.length)
        val key = (1 << wordSize) - 1  
        test(new XORCipher(memSize, wordSize)) { c =>
            // step through initial clean phase 
            pokeCmd(c.io.cmds, none)
            testStates.foreach(ts => {
                // attempt to change state (but shouldn't be able to)
                pokeCmd(c.io.cmds, ts)
                c.io.state.expect(XORCipher.cleanMem) // clean state
                c.clock.step(1)
            })
            pokeCmd(c.io.cmds, none)
            c.clock.step(memSize - testStates.length)

            c.io.state.expect(XORCipher.ready) // ready state

            c.clock.step(2) // catch if address not reseting right

            // start encrypting
            pokeCmd(c.io.cmds, encrypt)
            c.clock.step(1)
            pokeCmd(c.io.cmds, none)
            (0 until memSize - 1).foreach(w => {
                c.io.in.poke(w.U)
                c.io.key.poke(key.U)
                c.clock.step(1)
                c.io.state.expect(XORCipher.writeAndEncrypt) // encrypt state
            })
            // enc final word
            c.io.in.poke((memSize - 1).U)
            c.io.key.poke(key.U)
            c.clock.step(1)
            
            // reset inputs
            c.io.in.poke(0.U)
            c.io.key.poke(0.U)
            c.clock.step(1)
            c.io.state.expect(XORCipher.ready) // ready state

            // start reading
            pokeCmd(c.io.cmds, read)
            c.clock.step(1)
            pokeCmd(c.io.cmds, none)
            (0 until memSize - 1).foreach(w => {
                c.io.state.expect(XORCipher.readCiphertext) // read state
                c.io.out.expect((w ^ key).U) // should be the encrypted value
                c.clock.step(1)
            })
            // read final word
            c.io.state.expect(XORCipher.readCiphertext) // read state
            c.io.out.expect(((memSize - 1) ^ key).U)
            c.io.key.poke(key.U)
            c.clock.step(1)

            // reset inputs
            c.io.in.poke(0.U)
            c.io.key.poke(0.U)
            c.clock.step(1)
            c.io.state.expect(XORCipher.ready) // ready state

            // start decrypting
            pokeCmd(c.io.cmds, decrypt)
            c.clock.step(1)
            pokeCmd(c.io.cmds, none)
            (0 until memSize - 1).foreach(w => {
                c.io.key.poke(key.U)
                c.io.state.expect(XORCipher.readAndDecrypt) // decrypt state
                c.io.out.expect(w.U) // should be plaintext value
                c.clock.step(1)
                c.io.state.expect(XORCipher.readAndDecrypt) // decrypt state
            })
            // dec final word
            c.io.out.expect((memSize - 1).U)
            c.io.key.poke(key.U)
            c.clock.step(1)

            // reset inputs
            c.io.in.poke(0.U)
            c.io.key.poke(0.U)
            c.clock.step(1)
            c.io.state.expect(XORCipher.ready) // ready state

            // start cleaning
            pokeCmd(c.io.cmds, reset)
            c.clock.step(1)
            pokeCmd(c.io.cmds, none)
            (0 until memSize).foreach(w => {
                c.io.state.expect(XORCipher.cleanMem) // clean state
                c.clock.step(1)
            })

            // check state doesn't move from ready
            c.clock.step(10)
            c.io.state.expect(XORCipher.ready) // still in ready state

            // start reading
            pokeCmd(c.io.cmds, read)
            c.clock.step(1)
            pokeCmd(c.io.cmds, none)
            (0 until memSize).foreach(w => {
                c.io.state.expect(XORCipher.readCiphertext) // read state
                c.io.out.expect(0.U) // should be the cleaned value
                c.clock.step(1)
            })
        }
        true
    }


    // Easier test, only checks states not mem values
    def testXORCipherStates(memSize: Int, wordSize: Int): Boolean = {
        require(memSize >= testStates.length)
        val key = (1 << wordSize) - 1  
        test(new XORCipher(memSize, wordSize)) { c =>
            // step through initial clean phase 
            pokeCmd(c.io.cmds, none)
            testStates.foreach(ts => {
                // attempt to change state (but shouldn't be able to)
                pokeCmd(c.io.cmds, ts)
                c.io.state.expect(XORCipher.cleanMem) // clean state
                c.clock.step(1)
            })
            c.clock.step(memSize - testStates.length)

            c.io.state.expect(XORCipher.ready) // ready state

            // start encrypting
            pokeCmd(c.io.cmds, encrypt)
            c.clock.step(1)
            pokeCmd(c.io.cmds, none)
            (0 until memSize - 1).foreach(w => {
                c.clock.step(1)
                c.io.state.expect(XORCipher.writeAndEncrypt) // encrypt state
            })
            // enc final word
            c.clock.step(1)
            
            // reset inputs
            c.clock.step(1)
            c.io.state.expect(XORCipher.ready) // ready state

            // start reading
            pokeCmd(c.io.cmds, read)
            c.clock.step(1)
            pokeCmd(c.io.cmds, none)
            (0 until memSize - 1).foreach(w => {
                c.io.state.expect(XORCipher.readCiphertext) // read state
                c.clock.step(1)
            })
            // read final word
            c.io.state.expect(XORCipher.readCiphertext) // read state
            c.clock.step(1)

            // reset inputs
            c.clock.step(1)
            c.io.state.expect(XORCipher.ready) // ready state

            // start decrypting
            pokeCmd(c.io.cmds, decrypt)
            c.clock.step(1)
            pokeCmd(c.io.cmds, none)
            (0 until memSize - 1).foreach(w => {
                c.io.state.expect(XORCipher.readAndDecrypt) // decrypt state
                c.clock.step(1)
                c.io.state.expect(XORCipher.readAndDecrypt) // decrypt state
            })
            // dec final word
            c.clock.step(1)

            // reset inputs
            c.clock.step(1)
            c.io.state.expect(XORCipher.ready) // ready state

            // start cleaning
            pokeCmd(c.io.cmds, reset)
            c.clock.step(1)
            pokeCmd(c.io.cmds, none)
            (0 until memSize).foreach(w => {
                c.io.state.expect(XORCipher.cleanMem) // clean state
                c.clock.step(1)
            })

            // check state doesn't move from ready
            c.clock.step(100)
            c.io.state.expect(XORCipher.ready) // still in ready state

            // start reading
            pokeCmd(c.io.cmds, read)
            c.clock.step(1)
            pokeCmd(c.io.cmds, none)
            c.clock.step(1)
            pokeCmd(c.io.cmds, reset) // reset early
            c.clock.step(1)
            c.io.state.expect(XORCipher.cleanMem) // clean state
        }
        true
    }


    val memSize = 4
    val wordSize = 4
}


