package model.graphInitializers

import model.graphComponents.Cell

interface GraphInitializer {

    fun initializeCells(width: Int, height: Int) : List<Cell>
}