package yegor.cheprasov.xtravel.data.database.tables

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column

object CountryTable : IdTable<Long>(name = "countries") {
    override val id: Column<EntityID<Long>> = long("id").entityId().autoinc()

    val folderName = varchar("folder_name", 30)
    val internalName = varchar("internal_name", 30)
}