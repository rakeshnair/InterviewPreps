package com.rakesh

object Samples {

  // Sum of numbers from 1 to 10 using for-yield
  def forYieldUsage: Unit =  println((for (i <- 0 to 10) yield i).sum)

  // Sum of numbers from 1 to 10 using fill
  def fillUsage: Unit = {
    var i = 0
    println(Array.fill(10)({i += 1; i}).sum)
  }

  // Nil is basically the same as List() or List[Nothing]. Hardly used.
  def NilUsage: Unit = {
    println(s"${System.identityHashCode(Nil)}, ${System.identityHashCode(List())}")
  }

  def main(args: Array[String]) {
    // forYieldUsage
    // fillUsage
    // NilUsage
  }
}
