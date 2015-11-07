object RotatedSortedArray {

  val numArray = Array(24, 26, 30, 1, 5, 10, 12, 13, 15)
  val target = 13

  def findInRotatedSortedArray(array: Array[Int], target: Int): Boolean = {
    val middleIndex = array.length/2
    println(s"array: ${array.mkString(",")}")

    if (target == array(middleIndex)) return true

    if (middleIndex == 0) return false

    if (target > array(middleIndex)) {
      if (target <= array(array.length-1)) {
        return findInRotatedSortedArray(array.slice(middleIndex+1, array.length), target)
      } else {
        return findInRotatedSortedArray(array.slice(0, middleIndex), target)
      }
    } else {
      if (target >= array(0)) {
        return findInRotatedSortedArray(array.slice(0, middleIndex), target)
      } else {
        return findInRotatedSortedArray(array.slice(middleIndex+1, array.length), target)
      }
    }
    false
  }

  def displayMiddle(array: Array[Int]): Unit = {
    val middleIndex = array.length/2

    println(s"array: ${array.mkString(",")}, middle: ${array(middleIndex)}")

    if (middleIndex != 0) {
      displayMiddle(array.slice(0, middleIndex))
    }

    if (middleIndex != array.length-1) {
      displayMiddle(array.slice(middleIndex+1, array.length))
    }
  }

  def main(args: Array[String]): Unit = {
    println(findInRotatedSortedArray(numArray, target))
  }
}
