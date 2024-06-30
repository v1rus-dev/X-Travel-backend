package yegor.cheprasov.xtravel.data.utils

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ColumnType
import org.jetbrains.exposed.sql.Table

class CharArrayColumnType : ColumnType() {
    override fun sqlType(): String = "TEXT"

    override fun valueFromDB(value: Any): CharArray {
        return when (value) {
            is String -> value.toCharArray()
            is CharArray -> value
            else -> error("Unexpected value of type CharArray: $value")
        }
    }

    override fun notNullValueToDB(value: Any): Any {
        return (value as CharArray).concatToString()
    }

    override fun nonNullValueToString(value: Any): String {
        return "'${(value as CharArray).concatToString()}'"
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + sqlType().hashCode()
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this == other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false

        other as CharArrayColumnType

        return true
    }
}

fun Table.charArray(name: String): Column<CharArray> = registerColumn(name, CharArrayColumnType())