package mh.collection

import mh.Combinator
import org.scalatest.FunSpec

class CounterSuite extends FunSpec {
  val strings = Map(
    "" -> Map.empty[Char, Int],
    "abracadabra" -> Map('a' -> 5,
      'b' -> 2, 'c' -> 1, 'r' -> 2, 'd' -> 1),
    "simsalabim" -> Map('s' -> 2, 'i' -> 2,
      'm' -> 2, 'a' -> 2, 'l' -> 1, 'b' -> 1))
  describe("A Counter") {
    it("should remove key if count <= 0") {
      var ctr = Counter.empty[Int].set(1, 3)
      assert(ctr.count(1) === 3)
      ctr = ctr.set(1, 0)
      assert(ctr.map.getOrElse(1, -1) === -1)
    }
    it("should count insertions (+, ++)") {
      for { (s, answer) <- strings }
        assert(Counter(s).map === answer)
    }
    it("should count deletions (-, --)") {
      val ctr = Counter(List(1, 2, 3)) -- Counter(List(2, 3, 4))
      assert(ctr.map === Map(1 -> 1))
    }
    it("should resolve equality") {
      assert(Counter.empty[Int] === Counter.empty[Int])
      for ((s, answer) <- strings) {
        val ctr1 = Counter(s)
        val ctr2 = Counter(s.reverse)
        assert(ctr1 === ctr2)
        assert(ctr1.map === answer)
      }
    }
    it("should produce unions") {
      val comb = Combinator("abracadabra")
      val ctrs = comb.subsets.map(Counter(_))
      val union = ctrs.foldLeft(Counter.empty[Char]) { _ | _ }
      assert(union.map === strings("abracadabra"))
    }
    it("should produce intersections") {
      val c = Counter(List(1, 2, 3)) &
              Counter(List(2, 4, 6))
      assert(c.count(2) === 1)
      assert(c.size === 1)
    }
    it("should have an empty intersection of disjoint multisets") {
      assert((Counter(List(1, 3, 5)) & Counter(List(2, 4, 6))).size === 0)
    }
  }
}
