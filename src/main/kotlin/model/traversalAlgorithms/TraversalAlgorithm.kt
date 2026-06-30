package model.traversalAlgorithms

import model.graphComponents.Cell
import model.graphComponents.Grid

interface TraversalAlgorithm {

    fun traverse(cell: Cell, grid: Grid, visited: List<Cell>): List<Cell>
}