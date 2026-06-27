package model.graphInitializers

import model.graphComponents.Cell

class KingsGraphInitializer : GraphInitializer {

    override fun initializeCells(width: Int, height: Int): List<Cell> {

        require(width > 0 && height > 0) { "Width and height must be positive." }
        require(width == height) { "A King's Graph must have the same width and height." }

        return List(width * height) { i ->
            val x = i % height
            val y = i / height
            val isWall = x == 0 || x == width - 1 || y == 0 || y == height - 1
            Cell(x, y, isWall)
        }
    }
}