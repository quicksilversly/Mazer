package com.mazer

class Empty(val length: Int, val height: Int) extends Maze {
  
  def valueAt(cell: Cell) = 0
}

object Empty {
  def apply(height: Int, length: Int) = new Empty(height, length)
}