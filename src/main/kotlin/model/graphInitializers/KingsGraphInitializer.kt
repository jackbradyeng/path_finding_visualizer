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
            edges.addAll(getHorizontalEdges(cell, cells, width))
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

    fun getHorizontalEdges(cell: Cell, grid: List<Cell>, width: Int): List<Edge> {

        val horizontalEdges = mutableListOf<Edge>()

        when (cell.x) {
            0 -> {
                val right = Edge(cell, grid[(cell.y * width) + 1], Random.nextInt(1, 5))
                horizontalEdges.add(right)
            }
            width - 1 -> {
                val left = Edge(cell, grid[(cell.y * width) + cell.x - 1], Random.nextInt(1, 5))
                horizontalEdges.add(left)
            }
            else -> {
                val left = Edge(cell, grid[(cell.y * width) + cell.x - 1], Random.nextInt(1, 5))
                val right = Edge(cell, grid[(cell.y * width) + cell.x + 1], Random.nextInt(1, 5))
                horizontalEdges.add(left)
                horizontalEdges.add(right)
            }
        }
        return horizontalEdges
    }

    /** Finds all the diagonal edges in a King's graph. */
    fun getDiagonalEdges(cell: Cell, grid: List<Cell>, width: Int, height: Int): List<Edge> {

        val diagonalEdges = mutableListOf<Edge>()
        diagonalEdges.addAll(getCornerDiagonalEdges(cell, grid, width, height))
        diagonalEdges.addAll(getSideDiagonalEdges(cell, grid, width, height))
        diagonalEdges.addAll(getInnerDiagonalEdges(cell, grid, width, height))
        return diagonalEdges
    }

    /** Finds the diagonal edges of all corner cells in the graph. A King's graph will have four corner cells with
     * three neighbours each. Therefore, they will only have a single diagonal edge. */
    fun getCornerDiagonalEdges(cell: Cell, grid: List<Cell>, width: Int, height: Int): List<Edge> {

        var edge: Edge? = null

        when (cell.x) {
            0 if cell.y == 0 -> {
                edge = Edge(cell, grid[width - 1], Random.nextInt(1, 5))
            }
            width - 1 if cell.y == 0 -> {
                edge = Edge(cell, grid[width + cell.x - 1], Random.nextInt(1, 5))
            }
            0 if cell.y == height - 1 -> {
                edge = Edge(cell, grid[(cell.y - 1) * width - 1], Random.nextInt(1, 5))
            }
            width - 1 if cell.y == height - 1 -> {
                edge = Edge(cell, grid[(cell.y - 1) * width + cell.x - 1], Random.nextInt(1, 5))
            }
        }
        return listOfNotNull(edge)
    }

    /** Finds the diagonal edges of all side cells that are not in the corners of the graph. Each side cell in a King's
     * graph will have five neighbours. */
    fun getSideDiagonalEdges(cell: Cell, grid: List<Cell>, width: Int, height: Int): List<Edge> {

        var below: Edge? = null
        var above: Edge? = null

        when (cell.x) {
            0 if cell.y != 0 && cell.y != height - 1 -> {
                above = Edge(cell, grid[(cell.y - 1) * width + 1], Random.nextInt(1, 5))
                below = Edge(cell, grid[(cell.y + 1) * width + 1], Random.nextInt(1, 5))
            }
            width -1 if cell.y != 0 && cell.y != height - 1 -> {
                above = Edge(cell, grid[(cell.y - 1) * width + cell.x - 1], Random.nextInt(1, 5))
                below = Edge(cell, grid[(cell.y + 1) * width + cell.x - 1], Random.nextInt(1, 5))
            }
        }
        return listOfNotNull(below, above)
    }

    /** Finds the diagonal edges of all inner cells. i.e. all cells not located at the border of the graph. An inner
     * cell in a King's graph will have eight neighbours. */
    fun getInnerDiagonalEdges(cell: Cell, grid: List<Cell>, width: Int, height: Int): List<Edge> {

        var upperLeft: Edge? = null
        var upperRight: Edge? = null
        var lowerLeft: Edge? = null
        var lowerRight: Edge? = null

        if (cell.x != 0 && cell.x != width - 1 && cell.y != 0 && cell.y != height -1) {
            upperLeft = Edge(cell, grid[(cell.y - 1) * width + cell.x - 1], Random.nextInt(1, 5))
            upperRight = Edge(cell, grid[(cell.y - 1) * width + cell.x + 1], Random.nextInt(1, 5))
            lowerLeft = Edge(cell, grid[(cell.y + 1) * width + cell.x - 1], Random.nextInt(1, 5))
            lowerRight = Edge(cell, grid[(cell.y + 1) * width + cell.x + 1], Random.nextInt(1, 5))
        }

        return listOfNotNull(upperLeft, upperRight, lowerLeft, lowerRight)
    }
}