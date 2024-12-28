package yegor.cheprasov.xtravel.security

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.application.*
import io.ktor.server.auth.jwt.*
import java.util.*

class JwtConfig(private val environment: ApplicationEnvironment) {

    companion object {
        private const val validityInMs = 36_000_00 * 24 // 24 hours
        private const val refreshTokenValidityInMs = 36_000_00 * 24 * 7 // 7 days
    }

    private val secret = environment.config.property("jwt.secret").getString()
    private val refreshSecret = environment.config.property("jwt.refreshSecret").getString()
    private val issuer = environment.config.property("jwt.issuer").getString()
    private val audience = environment.config.property("jwt.audience").getString()
    private val myRealm = environment.config.property("jwt.realm").getString()
    private val accessTokenAlgorithm = Algorithm.HMAC256(secret)
    private val refreshTokenAlgorithm = Algorithm.HMAC256(refreshSecret)

    val realm = myRealm

    val accessTokenVerifier: JWTVerifier = JWT
        .require(accessTokenAlgorithm)
        .withIssuer(issuer)
        .build()

    val refreshTokenVerifier: JWTVerifier = JWT
        .require(refreshTokenAlgorithm)
        .withIssuer(issuer)
        .build()

    fun makeAccessToken(login: String, role: String, userId: String): String =
        JWT.create()
            .withAudience(audience)
            .withIssuer(issuer)
            .withClaim("login", login)
            .withClaim("role", role)
            .withClaim("userId", userId)
            .withIssuedAt(Date())
            .withExpiresAt(Date(System.currentTimeMillis() + validityInMs))
            .sign(accessTokenAlgorithm)

    fun makeRefreshToken(userId: String): String =
        JWT.create()
            .withAudience(audience)
            .withIssuer(issuer)
            .withClaim("userId", userId)
            .withIssuedAt(Date())
            .withExpiresAt(Date(System.currentTimeMillis() + refreshTokenValidityInMs))
            .sign(refreshTokenAlgorithm)

}

fun JWTPrincipal.getUserId(): String = this.payload.getClaim("userId").asString()