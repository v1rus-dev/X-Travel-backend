package yegor.cheprasov.xtravel.data.database.entities.country

data class ShortCountryDTO(
    val countryId: Long,
    val countryNameEn: String,
    val countryNameRu: String,
    val flagUrl: String? = null,
    val capitalId: String,
    val capitalNameEn: String,
    val capitalNameRu: String,
    val mainFolderName: String
)
