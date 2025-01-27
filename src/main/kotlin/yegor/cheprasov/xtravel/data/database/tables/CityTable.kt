package yegor.cheprasov.xtravel.data.database.tables

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column

object CityTable : IdTable<Long>(name = "cities") {
    override val id: Column<EntityID<Long>> = long("id").entityId().autoinc()

    val countryId = reference("country_id", CountryTable)
    val folderName = varchar("folder_name", 30)
    val internalName = varchar("internal_name", 30)
}