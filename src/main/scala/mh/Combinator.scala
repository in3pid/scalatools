package mh

import scala.collection.BitSet
import math._

/**
  *
  */

class Combinator[A](val seq: Seq[A]) extends AnyVal {
  def numSubsets: Int = pow(2, seq.size).toInt
  def subset(n: Int): Vector[A] = Combinator.bitmask(n).map(seq)
  def subsets = (0 until numSubsets) map subset
  def combinations(k: Int) = seq.combinations(k)
  def permutations = seq.permutations
//  def partitions =
//
}

object Combinator {
  def apply[A](seq: Seq[A]) = new Combinator(seq)
  def bitmask(n: Int): Vector[Int] = {
    def inner(n: Int, i: Int, out: Vector[Int]): Vector[Int] =
      if (n == 0) out
      else inner(n / 2, i + 1, if (n % 2 == 1) out :+ i else out)
    inner(n, 0, Vector.empty[Int])
  }
}
