package yegor.cheprasov.xtravel.data.database

import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database

fun Application.configureDatabase(): Database {
    val database = Database.connect(
        "jdbc:postgresql://localhost:5432/xtravel-database",
        driver = "org.postgresql.Driver",
        user = "postgres",
        password = "Xtghfcjd3841011"
    )
    return database
}