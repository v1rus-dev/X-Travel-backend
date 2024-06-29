package yegor.cheprasov.xtravel.data.database.entities.utils

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ColumnType
import org.jetbrains.exposed.sql.IDateColumnType
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.statements.api.PreparedStatementApi
import java.sql.ResultSet
import java.sql.Timestamp
import java.time.Instant
import java.time.LocalDateTime

open class TimestampWithoutTimezoneColumnType : ColumnType(), IDateColumnType {
    override fun sqlType(): String = "TIMESTAMP WITHOUT TIME ZONE"

    override fun valueFromDB(value: Any): Any = when (value) {
        is LocalDateTime -> value
        is Timestamp -> value.toLocalDateTime()
        else -> error("Unexpected value: $value of ${value::class.qualifiedName}")
    }

    override fun setParameter(stmt: PreparedStatementApi, index: Int, value: Any?) {
        if (value == null) return
        stmt[index] = when (value) {
            is LocalDateTime -> Timestamp.valueOf(value)
            else -> value
        }
    }

    override fun readObject(rs: ResultSet, index: Int): Any? {
        return rs.getObject(index, LocalDateTime::class.java)
    }

    override fun notNullValueToDB(value: Any): Any = Timestamp.valueOf(value as LocalDateTime)

    override val hasTimePart: Boolean = true
}

fun Table.timestampWithoutTimeZone(name: String): Column<Instant> = registerColumn<Instant>(
    name = name,
    type = TimestampWithoutTimezoneColumnType()
)