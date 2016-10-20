package com.mazer

object Main extends App {
  println("Depth first search maze: ")
  println(DepthFirstSearchMaze(22, 55).toString())

  println("Kruskal maze: ")
  println(KruskalMaze(22, 55).toString())
  
  println("Empty maze: ")
  println(EmptyMaze(22, 55).toString())
}
