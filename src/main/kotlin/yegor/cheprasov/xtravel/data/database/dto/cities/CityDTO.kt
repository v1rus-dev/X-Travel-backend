package yegor.cheprasov.xtravel.data.database.dto.cities

data class CityDTO(
    val cityId: Long,
    val countryId: Long,
    val internalName: String,
    val folderName: String,
)
