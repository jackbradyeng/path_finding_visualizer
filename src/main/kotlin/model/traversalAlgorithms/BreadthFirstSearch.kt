package model.traversalAlgorithms

import model.graphComponents.Cell
import model.graphComponents.Edge
import model.graphComponents.Grid

class BreadthFirstSearch : TraversalAlgorithm {

    override fun traverse(cell: Cell, grid: Grid, visited: List<Cell>): List<Cell> {
        val visitedMutable = visited.toMutableList()
        // non-recursive BFS requires a queue (FIFO) for correct functionality
        val queue: ArrayDeque<Cell> = ArrayDeque()

        queue.add(cell)
        visitedMutable.add(cell)

        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            val edges: List<Edge> = grid.edges[current] ?: emptyList()

            for (edge in edges) {
                val neighbour = if (edge.first == current) edge.second else edge.first
                if (neighbour !in visitedMutable) {
                    visitedMutable.add(neighbour)
                    queue.add(neighbour)
                }
            }
        }

        return visitedMutable
    }
}