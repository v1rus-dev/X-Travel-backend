package yegor.cheprasov.xtravel.data.database.tables

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column

object AttractionWebsiteTable : IdTable<Long>(name = "attraction_websites") {
    override val id: Column<EntityID<Long>> = long("id").entityId()

    val attractionId = reference("attraction_id", AttractionTable)
    val websiteUrl = varchar("website_url", 100)
}