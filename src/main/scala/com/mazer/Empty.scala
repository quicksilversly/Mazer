package com.mazer

class Empty(val height: Int, val length: Int, var maz: Array[Array[Int]]) extends Maze {

  def this(height: Int, length: Int) = {
    this(height, length, Array.ofDim[Int](height, length))
  }
  
  override def maze = {
    this
  }
}

object Empty {
  def apply(height: Int, length: Int) = new Empty(height, length)
}