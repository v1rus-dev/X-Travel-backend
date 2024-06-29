package yegor.cheprasov.xtravel.data.database.entities.country

data class CountryDTO(
    val countryId: Int,
    val mainPhotoUrl: String? = null,
    val countryNameEn: String,
    val countryNameRu: String,
    val countryDescriptionEn: String,
    val countryDescriptionRu: String,
    val flagUrl: String,
    val capitalId: String,
    val population: Int,
    val mainFolderName: String
)
