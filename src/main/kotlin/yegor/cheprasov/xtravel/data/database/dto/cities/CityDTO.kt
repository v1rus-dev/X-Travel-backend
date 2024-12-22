package yegor.cheprasov.xtravel.data.database.dto.cities

data class CityDTO(
    val id: Long,
    val nameRu: String,
    val nameEn: String,
    val descriptionEn: String,
    val descriptionRu: String,
    val population: Int? = null,
    val latitude: Double,
    val longitude: Double,
    val folderName: String,
    val countryId: Long,
)
