package model.graphComponents

import model.graphInitializers.GraphInitializer

/** An NxN matrix of cells. Cells are initialized with border wall flags to designate boundaries. A collection of edges
 * map cells to their traversable neighbors. */
data class Grid(
    val width: Int,
    val height: Int,
    val graphInitializer: GraphInitializer,
    var cells: List<Cell> = mutableListOf(),
    var edges: Map<Cell, List<Edge>> = mutableMapOf()
) {

    init {
        cells = graphInitializer.initializeCells(width, height)
    }
}