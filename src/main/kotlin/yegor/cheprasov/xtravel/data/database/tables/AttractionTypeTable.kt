package yegor.cheprasov.xtravel.data.database.tables

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object AttractionTypeTable : Table(name = "attraction_types") {
    val type: Column<String> = varchar("type", 30).uniqueIndex()
}