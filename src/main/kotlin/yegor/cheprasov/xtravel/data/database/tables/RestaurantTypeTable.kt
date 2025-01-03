package yegor.cheprasov.xtravel.data.database.tables

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object RestaurantTypeTable : Table(name = "restaurant_types") {
    val type: Column<EntityID<String>> = varchar("type", 30).entityId()
    val nameRu = varchar("name_ru", 30)
    val nameEn = varchar("name_en", 30)
    val description_ru = text("description_ru").nullable()
    val description_en = text("description_en").nullable()
}