package com.rakesh

trait CustomQueue[A] {
  def enqueue (element: A): Unit
  def dequeue: A
  def isEmpty: Boolean
  def peek: A
}
