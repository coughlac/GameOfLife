import _root_.GameOfLife.Grid

class GameOfLifeTest extends org.scalatest.FunSuite {

  test("Next generation cannot contain live cells if all cells in the current generation are dead") {
    val grid: Grid = List(
      List('.', '.'),
      List('.', '.'))
    assert(grid === new GameOfLife(grid).nextGeneration)
  }

  test(" Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.") {
    val currentGenerationGrid: Grid = List(
      List('*', '.'),
      List('*', '.'))

    val expectedNextGenerationGrid: Grid = List(
      List('.', '.'),
      List('.', '.'))

    assert(new GameOfLife(currentGenerationGrid).nextGeneration === expectedNextGenerationGrid)
  }

  test(" Any live cell with more than three live neighbours dies, as if by overcrowding.") {
    val currentGenerationGrid: Grid = List(
      List('*', '*', '*'),
      List('*', '*', '*'))

    val expectedNextGenerationGrid: Grid = List(
      List('*', '.', '*'),
      List('*', '.', '*'))

    assert(new GameOfLife(currentGenerationGrid).nextGeneration === expectedNextGenerationGrid)
  }

  test("Any live cell with two or three live neighbours lives on to the next generation.") {
    val currentGenerationGrid: Grid = List(
      List('*', '.'),
      List('*', '*'))

    val expectedNextGenerationGrid: Grid = List(
      List('*', '*'),
      List('*', '*'))

    assert(new GameOfLife(currentGenerationGrid).nextGeneration === expectedNextGenerationGrid)
  }

  test(" Any dead cell with exactly three live neighbours becomes a live cell.") {
    val currentGenerationGrid: Grid = List(
      List('.', '*'),
      List('*', '*'))

    val expectedNextGenerationGrid: Grid = List(
      List('*', '*'),
      List('*', '*'))

    assert(new GameOfLife(currentGenerationGrid).nextGeneration === expectedNextGenerationGrid)
  }

  test("Acceptance test") {
    val currentGenerationGrid: Grid = List(
      List('.', '.', '.', '.', '.', '.', '.', '.'),
      List('.', '.', '.', '.', '*', '.', '.', '.'),
      List('.', '.', '.', '*', '*', '.', '.', '.'),
      List('.', '.', '.', '.', '.', '.', '.', '.'))

    val expectedNextGenerationGrid: Grid = List(
      List('.', '.', '.', '.', '.', '.', '.', '.'),
      List('.', '.', '.', '*', '*', '.', '.', '.'),
      List('.', '.', '.', '*', '*', '.', '.', '.'),
      List('.', '.', '.', '.', '.', '.', '.', '.'))

    assert(new GameOfLife(currentGenerationGrid).nextGeneration === expectedNextGenerationGrid)
  }
}
