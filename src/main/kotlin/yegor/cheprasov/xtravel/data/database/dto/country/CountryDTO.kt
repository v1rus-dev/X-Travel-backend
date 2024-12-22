package yegor.cheprasov.xtravel.data.database.dto.country

data class CountryDTO(
    val countryId: Long,
    val countryNameRu: String,
    val countryNameEn: String,
    val countryDescriptionRu: String,
    val countryDescriptionEn: String,
    val population: Int? = null,
    val latitude: Double,
    val longitude: Double,
    val folderName: String,
    val shortName: String? = null,
)
