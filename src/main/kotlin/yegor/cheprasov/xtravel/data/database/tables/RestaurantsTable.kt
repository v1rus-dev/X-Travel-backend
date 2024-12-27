package yegor.cheprasov.xtravel.data.database.tables

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column

object RestaurantsTable : IdTable<Long>(name = "restaurants") {
    override val id: Column<EntityID<Long>> = long("id").entityId()

    val cityId = reference("city_id", CityTable)
    val nameRu = varchar("name_ru", 60)
    val nameEn = varchar("name_en", 60)
    val descriptionRu = text("description_ru")
    val descriptionEn = text("description_en")
    val restaurantType = reference("restaurant_type", RestaurantTypeTable.type)
    val address = varchar("address", 100)
    val latitude = double("latitude")
    val longitude = double("longitude")
    val websiteUrl = varchar("website_url", 100).nullable()
    val rating = float("rating").nullable()
    val averageCheck = float("average_check").nullable()
    val folderName = varchar("folder_name", 30)
}