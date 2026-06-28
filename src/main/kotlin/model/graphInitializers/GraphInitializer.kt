package model.graphInitializers

import model.graphComponents.Cell
import model.graphComponents.Edge

interface GraphInitializer {

    fun initializeCells(width: Int, height: Int) : List<Cell>
    fun initializeEdges(width: Int, height: Int, cells: List<Cell>) : Map<Cell, List<Edge>>
}