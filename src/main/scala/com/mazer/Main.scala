package com.mazer

object Main extends App {
  println("Depth first search maze: ")
  println(DepthFirstSearch(22, 55).maze.toString())

  println("Kruskal maze: ")
  println(Kruskal(22, 55).maze.toString())
  
  println("Empty maze: ")
  println(Empty(22, 55).maze.toString())
}