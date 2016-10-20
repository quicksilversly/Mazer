package com.mazer

import scala.util.Random

class DepthFirstSearchMaze(val length: Int, val height: Int) extends Maze {
  private val maze = {
    val maz = Array.ofDim[Int](length, height)
    maz(0)(0) = 1
    val stack = List[Cell]()
    mazify(Cell(0, 0) :: stack, Cell(0, 0), maz)
  }

  def valueAt(cell: Cell) = maze(cell.x)( cell.y)

  private def mazify(stack: List[Cell], currentCell: Cell, maze: Array[Array[Int]]): Array[Array[Int]] = {
    if (stack.nonEmpty) {
      val unvisitedNeighbor = getRandomUnvisitedNeighbor(maze, currentCell) match {
        case unvisitedNeighbor: Some[Cell] => {
          val newCurrentCell = unvisitedNeighbor.get
          maze(newCurrentCell.x)(newCurrentCell.y) = 1
          mazify(currentCell :: stack, newCurrentCell, maze)
        }
        case None => {
          val newCurrentCell = stack.head
          mazify(stack.tail, newCurrentCell, maze)
        }
      }
    }
    maze
  }

  private def neighbors(cell: Cell) = {
    List(Cell(cell.x - 1, cell.y),
         Cell(cell.x + 1, cell.y),
         Cell(cell.x, cell.y + 1),
         Cell(cell.x, cell.y - 1))
  }

  private def insideMaze(cell: Cell) = {
    cell.x >= 0 && cell.x < length && cell.y >= 0 && cell.y < height
  }

  private def notOriginalCell(cell: Cell, originalCell: Cell) = {
    cell.x != originalCell.x && cell.y != originalCell.y
  }

  private def cellVisited(maze: Array[Array[Int]], cell: Cell) = {
    maze(cell.x)(cell.y) == DepthFirstSearchMaze.Visited
  }

  private def hasValidNeighbors(maze: Array[Array[Int]], cell: Cell, originalCell: Cell) = {
    val neighboringCells = neighbors(cell)
    val filteredNeighbors = neighboringCells.filter(c => {
      insideMaze(c) && cellVisited(maze, c) && notOriginalCell(c, originalCell)
    })
    val isValidCell = filteredNeighbors.isEmpty
    isValidCell
  }

  private def getUnvisitedNeighbors(maze: Array[Array[Int]], cell: Cell) = {
    def xInBounds(x: Int) = x >= 0 && x < length
    def yInBounds(y: Int) = y >= 0 && y < height
    val neighboringCells = neighbors(cell)
    neighboringCells.filter(c => {
      xInBounds(c.x) &&
      yInBounds(c.y) &&
      maze(c.x)(c.y) != 1 &&
      hasValidNeighbors(maze, c, cell)
    })
  }

  private def getRandomUnvisitedNeighbor(maze: Array[Array[Int]], cell: Cell): Option[Cell] = {
    val neighbors = getUnvisitedNeighbors(maze, cell)
    if (neighbors.length > 0)
      Option(neighbors(Random.nextInt(neighbors.size)))
    else
      None
  }
}

object DepthFirstSearchMaze {
  private val Visited = 1

  def apply(height: Int, length: Int) = new DepthFirstSearchMaze(height, length)
}
