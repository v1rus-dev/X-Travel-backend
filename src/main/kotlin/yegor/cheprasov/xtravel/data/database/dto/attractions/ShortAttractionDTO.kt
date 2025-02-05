package yegor.cheprasov.xtravel.data.database.dto.attractions

data class ShortAttractionDTO(
    val attractionId: Long,
    val name: String,
    val countryId: Long,
    val cityId: Long?,
    val cityName: String?,
    val countryFolderName: String,
    val folderName: String,
)
