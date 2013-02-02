package mh

object Streamers {
  // Stream(init, f(init), f(f(init)), ...)
  def compose[A](init: A)(f: A => A): Stream[A] = init #:: compose(f(init))(f)
  def repeat[A](init: A): Stream[A] = init #:: repeat(init)
}
