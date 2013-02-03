package mh
import math._

class Counter[A](val map: Map[A, Int]) extends AnyVal {

  def contains(a: A): Boolean = map.contains(a)

  def count(a: A): Int = map.getOrElse(a, 0)

  def size: Int = map.values.sum

  def keys: Set[A] = map.keySet

  def set(key: A, value: Int): Counter[A] =
    if (value <= 0)
      if (count(key) == 0) this
      else new Counter(map - key)
    else new Counter(map + (key -> value))

  def add(key: A, value: Int): Counter[A] =
    set(key, count(key) + value)

  // counters

  def +(key: A): Counter[A] = add(key, 1)

  def -(key: A): Counter[A] = add(key, -1)

  def ++(keys: Seq[A]): Counter[A] = (this /: keys)(_+_)

  def --(keys: Seq[A]): Counter[A] = (this /: keys)(_-_)

  def ++(that: Counter[A]): Counter[A] = (this /: that.map) {
    case (r, (key, value)) => r.add(key, value)
  }

  def --(that: Counter[A]): Counter[A] = (this /: that.map) {
    case (r, (key, value)) => r.add(key, -value)
  }

  // set operations

  // intersection
  def &(that: Counter[A]): Counter[A] =
    (Counter.empty[A] /: (keys & that.keys)) { (ctr, key) =>
      ctr.set(key, min(count(key), that.count(key)))
    }

  // union
  def |(that: Counter[A]): Counter[A] =
    (Counter.empty[A] /: (keys | that.keys)) { (ctr, key) =>
      ctr.set(key, max(count(key), that.count(key)))
    }

  override def toString = map.toString
}

object Counter {
  def empty[A]: Counter[A] = new Counter(Map.empty[A, Int])
  def apply[A](seq: Seq[A]): Counter[A] = Counter.empty[A] ++ seq
}
