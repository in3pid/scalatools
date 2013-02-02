package mh

object Streamers {
  def compose[A](init: A)(f: A => A): Stream[A] = init #:: compose(f, f(init))
}
