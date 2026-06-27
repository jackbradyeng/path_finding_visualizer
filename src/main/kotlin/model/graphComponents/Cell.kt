package model.graphComponents

/** The building block of a grid. Feature x & y coordinates, a border flag, and an optional weight. Walls are not
 * traversable and therefore do not have weights. */
data class Cell(val x: Int, val y: Int, val isWall: Boolean, val weight: Double?)
