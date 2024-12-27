package yegor.cheprasov.xtravel.plugins

import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import io.ktor.util.*
import org.koin.ktor.ext.inject
import yegor.cheprasov.xtravel.security.JwtConfig

fun Application.configureSecurity() {
    authentication {
        oauth("auth-oauth-google") {
            urlProvider = { "http://localhost:8080/callback" }
            providerLookup = {
                OAuthServerSettings.OAuth2ServerSettings(
                    name = "google",
                    authorizeUrl = "https://accounts.google.com/o/oauth2/auth",
                    accessTokenUrl = "https://accounts.google.com/o/oauth2/token",
                    requestMethod = HttpMethod.Post,
                    clientId = System.getenv("GOOGLE_CLIENT_ID"),
                    clientSecret = System.getenv("GOOGLE_CLIENT_SECRET"),
                    defaultScopes = listOf("https://www.googleapis.com/auth/userinfo.profile")
                )
            }
            client = HttpClient(Apache)
        }
    }
}

fun Application.configureJWT() {
    val jwtConfig: JwtConfig by inject<JwtConfig>()

    authentication {
        jwt("auth-jwt") {
            validate { jwtCredential ->
                if (jwtCredential.payload.getClaim("username").asString() != "") {
                    JWTPrincipal(jwtCredential.payload)
                } else {
                    null
                }
            }
            realm = jwtConfig.realm
            verifier(jwtConfig.verifier)

            challenge { defaultScheme, realm ->
                call.respond(HttpStatusCode.Unauthorized, "Token is not valid or has expired")
            }
        }
    }
}

class UserSession(accessToken: String)
