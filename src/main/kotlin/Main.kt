import model.graphComponents.Grid
import model.graphInitializers.KingsGraphInitializer

fun main() {
    val grid = Grid(5, 5, KingsGraphInitializer())
    println(grid.toString())
}