package yegor.cheprasov.xtravel.data.utils

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ColumnType
import org.jetbrains.exposed.sql.Table

class StringListColumnType(private val delimiter: String = ",") : ColumnType() {
    override fun sqlType(): String = "TEXT"

    override fun valueFromDB(value: Any): List<String> {
        return when (value) {
            is String -> value.split(delimiter)
            is List<*> -> value.filterIsInstance<String>()
            else -> error("Unexpected value of type List<String>: $value")
        }
    }

    override fun notNullValueToDB(value: Any): Any {
        return (value as List<String>).joinToString(delimiter)
    }

    override fun nonNullValueToString(value: Any): String {
        return "'${(value as List<String>).joinToString(delimiter)}'"
    }
}

fun Table.stringList(name: String, delimiter: String = ","): Column<List<String>> =
    registerColumn(name, StringListColumnType(delimiter))