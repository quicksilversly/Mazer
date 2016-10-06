package com.mazer


class Kruskal(val height: Int, val length: Int, var maz: Array[Array[Int]]) extends Maze {
  
  def this(height: Int, length: Int) {
    this(height, length, Array.ofDim[Int](height, length))
  }
  
  override def maze = {
    new Kruskal(1, 1)
  }
}

object Kruskal {
  def apply(height: Int, length: Int) = new Kruskal(height, length)
}