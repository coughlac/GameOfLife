import _root_.GameOfLife.Grid

//
// Problem Description [http://codingdojo.org/cgi-bin/index.pl?KataGameOfLife]
//
// This Kata is about calculating the next generation of Conway's game of life, given any starting position. See http://en.wikipedia.org/wiki/Conway%27s_Game_of_Life for background.
//
// You start with a two dimensional grid of cells, where each cell is either alive or dead. In this version of the problem, the grid is finite, and no life can exist off the edges. When calculating the next generation of the grid, follow these rules:
//
//   1. Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
//   2. Any live cell with more than three live neighbours dies, as if by overcrowding.
//   3. Any live cell with two or three live neighbours lives on to the next generation.
//   4. Any dead cell with exactly three live neighbours becomes a live cell.
// You should write a program that can accept an arbitrary grid of cells, and will output a similar grid showing the next generation.
//

class GameOfLife(grid: Grid) {
  val live: Char = '*'

  val dead: Char = '.'

  def nextGeneration: Grid = {
    val indexedGrid = grid.map(_.zipWithIndex).zipWithIndex
    indexedGrid.map {
      case (row, rowIndex) => row.map {
        case (cell, columnIndex) => next(cell, rowIndex, columnIndex)
      }
    }
  }

  private def next(cell: Char, x: Int, y: Int): Char = {
    liveNeighboursOf(x, y) match {
      case n if n > 3 || n < 2 => dead
      case 3 => live
      case _ => cell
    }
  }

  private def liveNeighboursOf(x: Int, y: Int) = {
    val neighbours = for {
      i <- -1 to 1
      j <- -1 to 1 if !(i == 0 && j == 0)
    } yield neighbourExists(x + i)(y + j)
    neighbours.flatten.count(_ == live)
  }

  private def neighbourExists(x: Int)(y: Int): Option[Char] = for{
    row <- grid.lift(x)
    cell <- row.lift(y)
  } yield cell
}

object GameOfLife {
  type Grid = List[List[Char]]
}
