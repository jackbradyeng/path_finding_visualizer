package model.traversalAlgorithms

import model.graphComponents.Cell

interface TraversalAlgorithm {

    fun traverse(cell : Cell): Cell
}