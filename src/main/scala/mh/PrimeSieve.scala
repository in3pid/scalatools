package mh

import scala.collection.BitSet
/**
 * 00000000000...
 *  0 1 1 1 1 ...
 *   0  1  1  ...
 *    1.
 *     0    1 ...
 */

class PrimeSieve(val primes: BitSet=BitSet(2, 3, 5, 7)) extends AnyVal {
  def last = primes.last
  def contains(n: Int) = primes.contains(n)
  def extend(roof: Int): PrimeSieve = {
    var result = primes ++ (last+1 to roof)
    for { i <- primes } result --= i+i to roof by i
    for { i <- result.range(last+1, roof+1) }
      if (result.contains(i))
        result --= i+i until roof by i
    new PrimeSieve(result)
  }
  override
  def toString = "PrimeSieve(" + primes.size + " primes up to " + last + ")"
}

object PrimeSieve {
  def apply() = new PrimeSieve()
  def apply(n: Int) = new PrimeSieve().extend(n)
}
