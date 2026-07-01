import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import view.PathfindingVisualizer

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Path Finding Visualizer") {
        // PathfindingVisualizer()
    }
}