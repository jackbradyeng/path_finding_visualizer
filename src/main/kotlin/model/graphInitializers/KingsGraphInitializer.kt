package model.graphInitializers

import model.graphComponents.Cell
import model.graphComponents.Edge
import kotlin.random.Random

class KingsGraphInitializer : GraphInitializer {

    override fun initializeCells(width: Int, height: Int): List<Cell> {

        require(width > 3 && height > 3) { "Width and height must be positive. No trivial graphs allowed." }
        require(width == height) { "A King's Graph must have the same width and height." }

        return List(width * height) { i ->
            val x = i % height
            val y = i / height
            val isWall = x == 0 || x == width - 1 || y == 0 || y == height - 1
            Cell(x, y, isWall)
        }
    }

    /** Returns a map of all edges in the graph to their corresponding cells. */
    override fun initializeEdges(width: Int, height: Int, cells: List<Cell>): Map<Cell, List<Edge>> {

        val cellMap = mutableMapOf<Cell, List<Edge>>()

        // add the vertical, horizontal, and diagonal edges for each cell in the grid
        for (cell in cells) {
            val edges: MutableList<Edge> = mutableListOf()
            edges.addAll(getVerticalEdges(cell, cells, width, height))
            edges.addAll(getHorizontalEdges(cell, cells, width, height))
            edges.addAll(getDiagonalEdges(cell, cells, width, height))
            cellMap[cell] = edges
        }
        return cellMap
    }

    fun getVerticalEdges(cell: Cell, grid: List<Cell>, width: Int, height: Int): List<Edge> {

        val verticalEdges = mutableListOf<Edge>()

        when (cell.y) {
            // if the cell is on the first row, we only want to check for edges below
            0 -> {
                val below = Edge(cell, grid[width + cell.x], Random.nextInt(1, 5))
                verticalEdges.add(below)
            }
            // if the cell is on the bottom row, we only want to check for edges above
            height - 1 -> {
                val above = Edge(cell, grid[(cell.y - 1) * width + cell.x], Random.nextInt(1, 5))
                verticalEdges.add(above)
            }
            // if the cell lies somewhere in the middle of the grid, we want to check above and below
            else -> {
                val above = Edge(cell, grid[(cell.y - 1) * width + cell.x], Random.nextInt(1, 5))
                val below = Edge(cell, grid[(cell.y + 1) * width + cell.x], Random.nextInt(1, 5))
                verticalEdges.add(above)
                verticalEdges.add(below)
            }
        }
        return verticalEdges
    }

    fun getHorizontalEdges(cell: Cell, grid: List<Cell>, width: Int, height: Int): List<Edge> {

        val horizontalEdges = mutableListOf<Edge>()

        when (cell.x) {
            0 -> {
                val right = Edge(cell, grid[(cell.y * width) + 1], Random.nextInt(1, 5))
                horizontalEdges.add(right)
            }
            width - 1 -> {
                val left = Edge(cell, grid[(cell.y * width) - 1], Random.nextInt(1, 5))
                horizontalEdges.add(left)
            }
            else -> {
                val left = Edge(cell, grid[(cell.y * width) - 1], Random.nextInt(1, 5))
                val right = Edge(cell, grid[(cell.x * width) + 1], Random.nextInt(1, 5))
                horizontalEdges.add(left)
                horizontalEdges.add(right)
            }
        }
        return horizontalEdges
    }

    fun getDiagonalEdges(cell: Cell, grid: List<Cell>, width: Int, height: Int): List<Edge> {
        return TODO("Provide the return value")
    }
}