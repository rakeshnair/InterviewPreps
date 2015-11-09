package com.rakesh

import scala.reflect.ClassTag

/**
 * A stack implements a data structure which allows to store and retrieve objects in a last-in-first-out (LIFO) fashion.
 */
trait CustomStack[A] {
  def push (element: A): Unit
  def pop : A
  def isEmpty: Boolean
  def peek: A
}

// Cannot find class tag for element of type A
class FixedSizeCustomStack[A: ClassTag](size: Int) extends CustomStack[A] {
  private val data = new Array[A](size)
  private var top = 0

  override def push(element: A): Unit = {
    if (top == size) throw new StackOverflowException("Stack is full. Cannot add new elements.")
    data(top) = element
    top += 1
  }

  override def peek: A = {
    if (isEmpty) throw new StackEmptyException("Stack is empty.")
    data(top-1)
  }

  override def isEmpty: Boolean = top == 0

  override def pop: A = {
    if (isEmpty) throw new StackEmptyException("Stack is empty.")
    top -= 1
    data(top)
  }

  case class StackEmptyException(message: String) extends Exception(message)

  case class StackOverflowException(message: String) extends Exception(message)
}

object StackImpl {
  def main(args: Array[String]) {
    val stack = new FixedSizeCustomStack[Int](3)
    stack.push(1)
    stack.push(2)
    stack.push(3)
    stack.push(4)
  }
}
