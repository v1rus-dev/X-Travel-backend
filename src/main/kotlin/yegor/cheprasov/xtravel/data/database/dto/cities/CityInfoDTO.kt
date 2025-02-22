package yegor.cheprasov.xtravel.data.database.dto.cities

data class CityInfoDTO(
    val id: Long,
    val name: String,
    val description: String,
    val countryId: Long,
    val countryName: String,
    val latitude: Double,
    val longitude: Double,
    val countryFolderName: String,
    val cityFolderName: String,
)