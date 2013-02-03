package mh

import scala.collection.BitSet
import math._

class NumberTool(val n: Int) extends AnyVal {
  def numCompositions: Int = pow(2, n-1).toInt
  def composition(i: Int): Vector[Int] = {
    def inner(bits: Seq[Boolean], count: Int, out: Vector[Int]): Vector[Int] = {
      if (bits.size == 0) out :+ count
      else if (bits.head) inner(bits.tail, count + 1, out)
      else inner(bits.tail, 1, out :+ count)
    }
    inner(NumberTool(n-1).bitSeq(i), 1, Vector.empty[Int])
  }
  def compositions: Stream[Vector[Int]] =
    (0 until numCompositions).toStream.map(i => composition(i))

  def numBitSeqs = pow(2, n).toInt
  def bitSeq(i: Int): Vector[Boolean] =
    ((Vector.empty[Boolean], i) /: (0 until n)) {
      case ((r, i), x) => (r :+ (i % 2 == 1), i >> 1)
    }._1
  def bitSeqs: Stream[Vector[Boolean]] =
    (0 until numBitSeqs).toStream.map(bitSeq(_))
}

object NumberTool {
  def apply(n: Int): NumberTool = new NumberTool(n)
}

