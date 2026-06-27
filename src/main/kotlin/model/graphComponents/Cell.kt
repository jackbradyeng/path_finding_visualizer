package model.graphComponents

/** The building block of a grid. Feature x & y coordinates and a border flag. */
data class Cell(val x: Int, val y: Int, val isWall: Boolean)
