package yegor.cheprasov.xtravel.features.utils

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import yegor.cheprasov.xtravel.utils.FileService

class UtilsController : KoinComponent {

    private val fileService: FileService by inject()
    private val utils: Utils = Utils(fileService)

    suspend fun compressLargeImages(call: ApplicationCall) {
        try {
            val maxFileSize = 3200 * 1024L
            utils.compressLargeImages(call.receive(CompressImageReceiveRemote::class).folderName, maxFileSize)
            call.respond(HttpStatusCode.OK)
        } catch (e: Exception) {
            call.respond(HttpStatusCode.BadRequest, e.message ?: "")
        }
    }

}