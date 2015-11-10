package com.rakesh

import scala.reflect.ClassTag
import scala.collection.mutable

class Cache[K: ClassTag, V: ClassTag] {
  private val cache = scala.collection.mutable.HashMap.empty[K, V]

  def keys: Iterable[K] = cache.keys

  def += (kv: (K, V)): Unit = cache += kv

  def ++= (t: TraversableOnce[(K, V)]): Unit = cache ++= t

  def clear(): Unit = cache.clear()

  def apply(k: K): V = cache(k)

  def update(k: K, v: V): Unit = cache(k) = v

  override def toString: String = cache.toString()
}

class CacheRef[K, V] (cache: Cache[K, V]) {
  def fn: Unit = println(cache)
  def fn1(t: mutable.Map[K, V]) = println(t)
}

object CacheApp {
  def main(args: Array[String]) {
    val cache = new Cache[String, String]
    val tempMap = Map("john" -> "A", "Tom" -> "B")
    cache ++= tempMap

    new CacheRef[String, String](cache).fn
    val mutableTempMap = mutable.Map("john" -> "A", "Tom" -> "B")
    mutableTempMap += "john" -> "muir"

    new CacheRef[String, String](cache).fn1(mutableTempMap)
  }
}
