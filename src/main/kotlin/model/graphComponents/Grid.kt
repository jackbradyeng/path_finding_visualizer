package model.graphComponents

import model.graphInitializers.GraphInitializer

/** An NxN matrix of cells. Cells are initialized with border wall flags to designate boundaries. A collection of edges
 * map cells to their traversable neighbors. */
data class Grid(
    val width: Int,
    val height: Int,
    val graphInitializer: GraphInitializer,
    val cells: List<Cell> = graphInitializer.initializeCells(width, height),
    val edges: Map<Cell, List<Edge>> = graphInitializer.initializeEdges(width, height, cells)
)