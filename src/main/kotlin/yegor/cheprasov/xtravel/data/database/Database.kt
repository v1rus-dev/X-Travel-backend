package yegor.cheprasov.xtravel.data.database

import org.jetbrains.exposed.sql.Database

fun createDatabase(): Database =
    Database.connect(
        "jdbc:postgresql://localhost:5432/xtravel-database",
        driver = "org.postgresql.Driver",
        user = "postgres",
        password = "Xtghfcjd3841011"
    )