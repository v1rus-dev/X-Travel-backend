package yegor.cheprasov.xtravel.data.database.dto.country

data class CountryDTO(
    val countryId: Long,
    val countryName: String,
    val countryDescription: String,
    val area: Double,
    val folderName: String,
    val shortName: String? = null,
)
