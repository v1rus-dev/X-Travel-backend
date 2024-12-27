package yegor.cheprasov.xtravel.security

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.Payload
import io.ktor.server.application.*
import io.ktor.server.auth.jwt.*
import java.util.*

class JwtConfig(private val environment: ApplicationEnvironment) {

    companion object {
        private const val validityInMs = 36_000_00 * 10 // 10 hours
    }

    private val secret = environment.config.property("jwt.secret").getString()
    private val issuer = environment.config.property("jwt.issuer").getString()
    private val audience = environment.config.property("jwt.audience").getString()
    private val myRealm = environment.config.property("jwt.realm").getString()
    private val algorithm = Algorithm.HMAC256(secret)

    val realm = myRealm

    val verifier: JWTVerifier = JWT
        .require(algorithm)
        .withIssuer(issuer)
        .build()

    fun makeToken(login: String, role: String, userId: String): String =
        JWT.create()
            .withAudience(audience)
            .withIssuer(issuer)
            .withClaim("login", login)
            .withClaim("role", role)
            .withClaim("userId", userId)
            .withIssuedAt(Date())
            .withExpiresAt(Date(System.currentTimeMillis() + validityInMs))
            .sign(algorithm)

}

fun JWTPrincipal.getUserId(): String = this.payload.getClaim("userId").asString()