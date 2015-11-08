package com.rakesh

object ForYieldUsage {
  /**
   * Sum of numbers from 1 to 10 using for-yield
   */
  def main(args: Array[String]) {
    println((for (i <- 0 to 10) yield i).sum)
  }
}
