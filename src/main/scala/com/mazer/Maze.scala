package com.mazer

trait Maze {
  val length: Int 
  val height: Int
  def valueAt(cell: Cell): Int
  
  override def toString() = {
    val strBuilder = new StringBuilder()
    for {
      i <- 0 until length
      j <- 0 until height
    } {
      strBuilder.append(valueAt(Cell(i, j)))
      if (j == height - 1) strBuilder.append("\n")
    }
    strBuilder.toString
  }
}
