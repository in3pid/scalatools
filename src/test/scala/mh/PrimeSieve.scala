package mh.primesieve

import org.scalatest.FunSpec

object MyPrimes {
  val primes = collection.immutable.SortedSet(2, 3, 5, 7, 11, 13, 17, 19)
}

class PrimeSieveSuite extends FunSpec {
  import MyPrimes._
  describe("PrimeSieve") {
    it("2, 3, 5, 7, 11, 13, 17, 19 are the primes up to 20") {
      val ps = PrimeSieve(20)
      assert((0 to 20).forall(n => ps.contains(n) == primes.contains(n)))
    }
    it("... through various extensions") {

    }
  }
}
