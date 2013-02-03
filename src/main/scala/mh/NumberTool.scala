package mh

import scala.collection.BitSet
import math._


object BitTools {
  // bitsequence #i of length n
  def bitseq(n: Int, i: Int): Vector[Boolean] = {
    def f(n: Int, i: Int, out: Vector[Boolean]): Vector[Boolean] =
      if (n == 0) out
      else f(n - 1, i >> 1, out :+ (i % 2 == 1))
    f(n, i, Vector.empty[Boolean])
  }
}


// some number theory

class NumberTool(val n: Int) extends AnyVal {
  def numCompositions: Int = pow(2, n-1).toInt
  def composition(i: Int): Vector[Int] = {
    def inner(bits: Seq[Boolean], count: Int, out: Vector[Int]): Vector[Int] = {
      if (bits.size == 0) out :+ count
      else if (bits.head) inner(bits.tail, count + 1, out)
      else inner(bits.tail, 1, out :+ count)
    }
    inner(BitTools.bitseq(n-1, i), 1, Vector.empty[Int])
  }
  def compositions: Stream[Vector[Int]] =
    (0 until numCompositions).toStream.map(i => composition(i))
}

object NumberTool {
  def apply(n: Int): NumberTool = new NumberTool(n)
}

