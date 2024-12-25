package yegor.cheprasov.xtravel.utils

import io.ktor.server.application.*
import io.ktor.server.plugins.*

fun ApplicationCall.getWebAddress(): String =
    "${this.request.origin.scheme}://${this.request.origin.serverHost}:${this.request.origin.serverPort}"