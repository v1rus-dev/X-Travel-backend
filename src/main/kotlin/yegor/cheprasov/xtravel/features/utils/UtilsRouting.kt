package yegor.cheprasov.xtravel.features.utils

import io.ktor.server.application.*
import io.ktor.server.routing.*


fun Application.configureUtilsRouting() {
    val controller = UtilsController()
    routing {
        put("/utils/compress_images") {
            controller.compressLargeImages(call)
        }
    }
}