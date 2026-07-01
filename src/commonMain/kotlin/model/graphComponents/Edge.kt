package model.graphComponents

/** The path between two cells in a grid. First and second nodes are interchangeable. Walls have no weights since they
 * are not traversable. */
data class Edge(val first: Cell, val second: Cell, val weight: Int?)