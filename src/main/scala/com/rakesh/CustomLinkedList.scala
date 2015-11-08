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

    // Decrement the length
    numElements -= 1

    if (n == 0) {
      // If element to remove is the head
      val removedNode = headNode
      headNode = removedNode.next
      if (headNode == null) tailNode = null
      removedNode.next = null
      removedNode.data
    } else {
      var runner = headNode
      for (i <- 0 until n-1) runner = runner.next

      val removedData = runner.next.data
      runner.next = runner.next.next

      // If element to remove is the tail
      if (runner.next == null) {
        tailNode = runner
      }
      removedData
    }
  }

  /**
   * Insert a list of new elements to the linked list at a particular index
   */
  override def insertAll(n: Int, newElements: Traversable[A]): Unit = {
    if (n < 0 || n > numElements) {
      throw new InvalidIndexException(s"Index $n is not in Range(0..$numElements)")
    }

    if (n == 0) {
      // If the new list of elements were added to the front
      if (newElements.nonEmpty) {
        headNode = Node(newElements.head, headNode)
        var runner = headNode

        // Pick all elements except the head (since head element was already made the headNode)
        for (e <- newElements.tail) {
          runner.next = Node(e, runner.next)
          runner = runner.next
        }

        // If the new list of elements were added to an empty list
        if (runner.next == null) tailNode = runner
      }
    } else {
      var runner = headNode
      // Traverse till the node before the index u want to insert new elements
      for (i <- 0 until n-1) runner = runner.next

      for (e <- newElements) {
        runner.next = Node(e, runner.next)
        runner = runner.next
      }

      // If the new list of elements were added to the end
      if (runner.next == null) tailNode = runner
    }
    numElements += newElements.size
  }

  /**
   * Return an iterator for the linked list
   */
  override def iterator: Iterator[A] = new Iterator[A] {
    var runner = headNode

    override def hasNext: Boolean = runner != null

    override def next(): A = {
      val returnVal = runner.data
      runner = runner.next
      returnVal
    }
  }

  case class InvalidIndexException(message: String) extends Exception(message)

}

object CustomLinkedList {
  def main (args: Array[String]) {
    val cll = new CustomLinkedList[Int]
    cll += 12
    cll += 13
    cll += 14
    cll.insertAll(0, Seq[Int](1,2,3))
    cll.insertAll(cll.length, Seq[Int](4,5,6))
    cll.insert(cll.length/2, 10)
    cll.remove(0)
    cll.foreach(println)
  }
}