package yegor.cheprasov.xtravel.data.database.tables

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column

object CountryInfoTable : IdTable<Long>(name = "country_info") {
    override val id: Column<EntityID<Long>> = long("id").entityId().autoinc()

    val countryId = reference("country_id", CountryTable)
    val areaKm2 = double("area_km2")
    val phoneCode = integer("phone_code")
    val literacyRate = double("literacy_rate").nullable()
    val isoCode = varchar("iso_code", 3)
    val currency = reference("currency", CurrencyTable.currencyCode)
}