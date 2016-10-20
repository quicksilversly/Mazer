package com.mazer


class KruskalMaze(val length: Int, val height: Int) extends Maze {
  private val maze = {
    val maz = Array.ofDim[Int](length, height)
    maz
  }

  def valueAt(cell: Cell) = maze(cell.x)(cell.y)
}

object KruskalMaze {
  def apply(height: Int, length: Int) = new KruskalMaze(height, length)
}
