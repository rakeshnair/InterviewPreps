package com.rakesh

import scala.collection.mutable

class CustomLinkedList[A] extends mutable.Buffer[A] {

  // Class to represent each element of the linked list
  private case class Node(var data: A, var next: Node)

  // Maintain pointers to the first and last element of the list
  private var headNode: Node = null
  private var tailNode: Node = null

  // Keep a counter so that we can compute length() in constant time
  private var numElements = 0

  /**
   * Add new element to the head
   */
  def +=:(elem: A): this.type = {
    headNode = Node(elem, headNode)
    if (tailNode == null) {
      tailNode = headNode
    }
    numElements += 1
    this
  }

  /**
   * Add new element to the end
   */
  def +=(elem: A): this.type = {
    if (tailNode == null) {
      tailNode = Node(elem, null)
      headNode = tailNode
    } else {
      tailNode.next = Node(elem, null)
      tailNode = tailNode.next
    }
    numElements += 1
    this
  }

  /**
   * Remove all the elements
   */
  override def clear(): Unit = {
    headNode = null
    tailNode = null
    numElements = 0
  }

  /**
   * Total length of the linked list
   */
  override def length: Int = numElements

  /**
   * Obtain the nth element in the list
   */
  override def apply(n: Int): A = {
    if (n < 0 || n >= numElements) {
      throw new InvalidIndexException(s"Index $n is not in Range(0..$numElements)")
    }
    var runner = headNode
    for (i <- 0 until n) runner = runner.next
    runner.data
  }

  /**
   * Update an element in a linked list
   */
  override def update(n: Int, newElem: A): Unit = {
    if (n < 0 || n >= numElements) {
      throw new InvalidIndexException(s"Index $n is not in Range(0..$numElements)")
    }
    var runner = headNode
    for (i <- 0 until n) runner = runner.next
    runner.data = newElem
  }

  /**
   * Remove element in the nth position
   */
  override def remove(n: Int): A = {
    if (n < 0 || n >= numElements) {
      throw new InvalidIndexException(s"Index $n is not in Range(0..$numElements)")
    }
    var slowRunner = headNode
    var fastRunner = headNode.next
    for (i <- 0 until n) {
      fastRunner = fastRunner.next
      slowRunner = slowRunner.next
    }
    val data = slowRunner.data
    if (fastRunner == null) {
      slowRunner.next = fastRunner
    } else {
      slowRunner.next = fastRunner.next
    }

    data
  }

  override def insertAll(n: Int, elems: Traversable[A]): Unit = ???

  override def iterator: Iterator[A] = ???

  case class InvalidIndexException(message: String) extends Exception(message)

}

object CustomLinkedList {
  def main (args: Array[String]) {
    val cll = new CustomLinkedList[Int]
    cll += 12
    cll += 13
  }
}