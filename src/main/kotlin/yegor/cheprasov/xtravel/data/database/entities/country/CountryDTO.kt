package yegor.cheprasov.xtravel.data.database.entities.country

data class CountryDTO(
    val countryId: Long,
    val countryNameEn: String,
    val countryNameRu: String,
    val countryDescriptionEn: String,
    val countryDescriptionRu: String,
    val capitalId: String,
    val population: Int,
    val mainFolderName: String
)
