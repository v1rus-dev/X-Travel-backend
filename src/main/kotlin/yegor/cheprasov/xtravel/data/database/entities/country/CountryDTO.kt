package yegor.cheprasov.xtravel.data.database.entities.country

data class CountryDTO(
    val countryId: Int,
    val countryNameEn: String,
    val countryNameRu: String,
    val countryDescriptionEn: String,
    val countryDescriptionRu: String,
    val flagUrl: String? = null,
    val capitalId: String,
    val population: Int,
    val mainFolderName: String
)
