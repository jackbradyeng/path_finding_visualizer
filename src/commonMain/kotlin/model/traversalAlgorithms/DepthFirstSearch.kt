package model.traversalAlgorithms

import model.graphComponents.Cell
import model.graphComponents.Edge
import model.graphComponents.Grid

class DepthFirstSearch : TraversalAlgorithm {

    /** Traverses a given graph sequentially, leveraging the stack's LIFO behaviour to achieve DFS. */
    override fun traverse(cell: Cell, grid: Grid, visited: List<Cell>): List<Cell> {
        val visitedMutable = visited.toMutableList()

        // non-recursive DFS requires a stack (LIFO behaviour) for correct functionality
        val stack: ArrayDeque<Cell> = ArrayDeque()

        stack.addLast(cell)

        while (stack.isNotEmpty()) {
            val current = stack.removeLast()

            // skip the current cell if it has already been visited
            if (current in visitedMutable) continue

            // push the current node onto the stack only when it has been visited
            visitedMutable.add(current)

            // if the current cell is the target cell, then break out of the loop
            if (current == grid.end) return visitedMutable

            val edges: List<Edge> = grid.edges[current] ?: emptyList()

            for (edge in edges) {
                val neighbour = if (edge.first == current) edge.second else edge.first
                if (neighbour !in visitedMutable) {
                    stack.addLast(neighbour)
                }
            }
        }

        return visitedMutable
    }
}