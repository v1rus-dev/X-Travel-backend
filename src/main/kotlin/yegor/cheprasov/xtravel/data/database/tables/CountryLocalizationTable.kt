package yegor.cheprasov.xtravel.data.database.tables

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column

object CountryLocalizationTable : IdTable<Long>(name = "country_localization") {
    override val id: Column<EntityID<Long>> = long("id").entityId().autoinc()
    val countryId = reference("id", CountryTable)
    val languageCode = varchar("language_code", 10)
    val name = varchar("name", 60)
    val description = text("description")
    val shortName = varchar("short_name", 30).nullable()
}