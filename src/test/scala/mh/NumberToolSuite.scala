package mh

import org.scalatest.FunSpec

class NumberToolSuite extends FunSpec {
  describe("NumberTool") {
    it("should enumerate bitsequences of a given length") {
      val answers = Map(
        0 -> Set(Vector.empty[Boolean]),
        1 -> Set(Vector(true), Vector(false)),
        2 -> Set(Vector(true, true), Vector(false, true),
                 Vector(true, false), Vector(false, false)))
      answers.foreach {
        case (n, answer) => assert(NumberTool(n).bitSeqs.toSet === answer)
      }
    }
    it("should count compositions of N") {
      val answers = Map(0 -> 0, 1 -> 1, 2 -> 2, 3 -> 4, 4 -> 8)
      answers.foreach {
        case (n, answer) => assert(NumberTool(n).numCompositions === answer)
      }
    }
    it("should enumerate compositions of N") {
      val answers = Map(
        0 -> Set(),
        1 -> Set(Vector(1)),
        2 -> Set(Vector(2), Vector(1, 1)),
        3 -> Set(Vector(3), Vector(2, 1), Vector(1, 2), Vector(1, 1, 1)))
      answers.foreach {
        case (n, answer) => assert(NumberTool(n).compositions.toSet === answer)
      }
    }
  }
}
