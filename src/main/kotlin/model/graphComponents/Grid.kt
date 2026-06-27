package model.graphComponents

import kotlin.random.Random

/** An NxN matrix of cells. Cells are initialized with border walls and a randomized weight by default. */
data class Grid(
    val width: Int,
    val height: Int,
    val cells: List<Cell> = List(width * height) { i ->
        val x = i % height
        val y = i / height
        val isWall = x == 0 || x == width - 1 || y == 0 || y == height - 1
        Cell(x, y, isWall,
            if (isWall) null else Random.nextDouble(0.5, 1.0))
    }
) {

    init {
        require(width > 0 && height > 0) { "Width and height must be positive" }
    }
}