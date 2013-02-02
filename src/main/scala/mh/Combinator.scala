package mh

import scala.collection.BitSet
import math._

/**
  * Numbers encode bitmasks.
  * Bitmasks encode subsets.
  * Multimasks can encode multisets and permutations.
  *
  * A multimask is just a bitmask with a natural number
  *  mapped to each set bit.
  *
  */

class BitMask(val mask: BigInt) extends AnyVal {
  def asBitSet = BitSet(indices:_*)
  def indices = {

    var n = 0
    var t = mask
    var out = Vector.empty[Int]
    while (t != 0) {
      if (t.testBit(0)) out :+= n
      t >>= 1
      n += 1
    }
    out
  }
}

object BitMask {
  def apply() = new BitMask(BigInt(0))
  def apply(n: Int) = new BitMask(BigInt(n))
  def apply(n: BigInt) = new BitMask(n)
}

object Tools {
  def remainders[A](
    n:Int,
    seq:Seq[A],
    out:Vector[A]=Vector.empty[A]): Vector[A] =
    if (n == 0)
      out
    else if (n % 2 == 1)
      remainders(n / 2, seq.tail, out :+ seq(0))
    else
      remainders(n / 2, seq.tail, out)
}

class Combinator[A](val seq: Seq[A]) extends AnyVal {
  import Tools._
  def numSubsets: Int = pow(2, seq.size).toInt
  def subset(n: Int): Vector[A] = remainders(n, seq)
  def subsets = (0 until numSubsets) map subset
  def combinations(k: Int) = seq.combinations(k)
  def permutations = seq.permutations
}

object Combinator {
  def apply[A](seq: Seq[A]) = new Combinator(seq)
}
