package com.mazer

import scala.util.Random

class DepthFirstSearch(val height: Int, val length: Int, var maz: Array[Array[Int]]) extends Maze {

  def this(height: Int, length: Int) {
    this(height, length, Array.ofDim[Int](height, length))
  }
  
  override def maze = {
    maz(0)(0) = 1
    val stack = List[(Int, Int)]()
    mazify((0, 0) :: stack, (0, 0))
  }

  private def mazify(stack: List[(Int, Int)], currentCell: (Int, Int)): DepthFirstSearch = {
    if (stack.nonEmpty) {
      val unvisitedNeighbor = getRandomUnvisitedNeighbor(maz, currentCell)
      if (unvisitedNeighbor._1) {
        val newCurrentCell = unvisitedNeighbor._2
        maz(newCurrentCell._1)(newCurrentCell._2) = 1
        mazify(currentCell :: stack, newCurrentCell)
      } else if (stack.nonEmpty) {
        val newCurrentCell = stack.head
        mazify(stack.tail, newCurrentCell)
      }
    }
    new DepthFirstSearch(height, length, maz)
  }
    
  private def neighbors(cell: (Int, Int)) = {
    List((cell._1 - 1, cell._2), (cell._1 + 1, cell._2), (cell._1, cell._2 + 1), (cell._1, cell._2 - 1))
  }
  
  private def insideMaze(cell: (Int, Int)) = {
    cell._1 >= 0 && cell._1 < height && cell._2 >= 0 && cell._2 < length
  }
  
  private def notOriginalCell(cell: (Int, Int), originalCell: (Int, Int)) = {
    cell._1 != originalCell._1 && cell._2 != originalCell._2
  }
  
  private def cellVisited(cell: (Int, Int)) = {
    maz(cell._1)(cell._2) == DepthFirstSearch.Visited
  }
  
  private def hasValidNeighbors(cell: (Int, Int), originalCell: (Int, Int)) = {
    val neighboringCells = neighbors(cell)
    val filteredNeighbors = neighboringCells.filter(c => insideMaze(c) && cellVisited(c) && notOriginalCell(c, originalCell))
    val isValidCell = filteredNeighbors.isEmpty
    isValidCell
  }
  
  private def getUnvisitedNeighbors(maze: Array[Array[Int]], cell: (Int, Int)) = {
    val neighboringCells = neighbors(cell)
    val filteredNeighbors = neighboringCells.filter(c => c._1 >= 0 && c._1 < height && c._2 >= 0 && c._2 < length && maze(c._1)(c._2) != 1 && hasValidNeighbors(c, cell))
    filteredNeighbors
  }
  
  private def getRandomUnvisitedNeighbor(maze: Array[Array[Int]], cell: (Int, Int)): (Boolean, (Int, Int)) = {
    val neighbors = getUnvisitedNeighbors(maze, cell)
    if (neighbors.length > 0)
      (true, neighbors(Random.nextInt(neighbors.size)))
    else
      (false, (-1, -1))
  }
}

object DepthFirstSearch { 
  private val Visited = 1
  
  def apply(height: Int, length: Int) = new DepthFirstSearch(height, length)
}