package com.mazer

trait Maze {
  val height: Int
  val length: Int 
  var maz: Array[Array[Int]]
  
  def maze: Maze 
  
  override def toString() = {
    val strBuilder = new StringBuilder()
    for (i <- 0 until height) {
      for (j <- 0 until length) {
        strBuilder.append(maz(i)(j))
      }
      strBuilder.append("\n")
    }
    strBuilder.toString()
  }
}
  