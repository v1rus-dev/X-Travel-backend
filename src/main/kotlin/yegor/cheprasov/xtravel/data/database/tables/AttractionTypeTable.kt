package yegor.cheprasov.xtravel.data.database.tables

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object AttractionTypeTable : Table(name = "attraction_types") {
    val type: Column<EntityID<String>> = varchar("type", 30).entityId().autoIncrement()
}