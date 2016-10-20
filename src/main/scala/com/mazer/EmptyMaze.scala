package com.mazer

class EmptyMaze(val length: Int, val height: Int) extends Maze {
  
  def valueAt(cell: Cell) = 0
}

object EmptyMaze {
  def apply(height: Int, length: Int) = new EmptyMaze(height, length)
}
