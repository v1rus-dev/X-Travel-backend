package yegor.cheprasov.xtravel.plugins

import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*
import java.io.File

fun Application.configureFiles() {
    routing {
        staticFiles("/resources", File("files"))
    }
}