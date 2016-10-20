package com.mazer

import org.scalatest.FlatSpec

class EmptyMazeSpec extends FlatSpec {

  "An empty maze" should "be empty" in {
    val emptyMaze = Empty(10, 10)
    for {
      i <- 0 until emptyMaze.length
      j <- 0 until emptyMaze.height
    } {
      assert(emptyMaze.valueAt(Cell(i, j)) == 0)
    }
  }
}