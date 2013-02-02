package mh

import scala.math._
import scala.util.Random._

trait RandomDistribution[A] extends Iterator[A] {
  def hasNext = true
}

case class GeometricDistribution(
  rho: Double,
  deltaMax: Int) extends RandomDistribution[Int]
{
  def signum = if (nextBoolean) 1 else -1
  def delta(δ: Int=0): Int =  // P(δ = i) = rho*(1-rho)^(i-1)
    if (δ <= deltaMax && nextDouble < rho) delta(δ + 1)
    else δ
  def next = signum * delta()
}
