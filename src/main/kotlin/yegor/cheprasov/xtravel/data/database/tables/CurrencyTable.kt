package yegor.cheprasov.xtravel.data.database.tables

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object CurrencyTable : Table("currency") {
    val currencyCode: Column<String> = varchar("currency_code", 3).uniqueIndex()
    val fontCode: Column<String> = varchar("font_code", 5)
}