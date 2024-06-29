package yegor.cheprasov.xtravel.data.database.entities.cities

data class CityDTO(
    val cityId: String,
    val nameEn: String,
    val nameRu: String,
    val descriptionEn: String,
    val descriptionRu: String,
    val imagesUrls: List<String>,
    val population: Int,
    val latitude: Double,
    val longitude: Double,
)
