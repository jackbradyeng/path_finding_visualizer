package model.graphComponents

/** An NxN matrix of cells. Cells are initialized with border wall flags to designate boundaries. A collection of edges
 * map cells to their traversable neighbors. */
data class Grid(
    val width: Int,
    val height: Int,
    val cells: List<Cell> = List(width * height) { i ->
        val x = i % height
        val y = i / height
        val isWall = x == 0 || x == width - 1 || y == 0 || y == height - 1
        Cell(x, y, isWall)
    },
    val edges: Map<Cell, List<Edge>> = mutableMapOf()
) {

    init {
        require(width > 0 && height > 0) { "Width and height must be positive" }
    }
}