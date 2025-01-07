package yegor.cheprasov.xtravel.data.database.dto.country

data class ShortCountryDTO(
    val countryId: Long,
    val countryName: String,
    val folderName: String,
    val shortName: String? = null
)
