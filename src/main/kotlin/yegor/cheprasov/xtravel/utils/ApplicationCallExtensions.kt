package yegor.cheprasov.xtravel.utils

import io.ktor.server.application.*

fun ApplicationCall.getLang(): String = this.request.headers["lang"] ?: "ru"