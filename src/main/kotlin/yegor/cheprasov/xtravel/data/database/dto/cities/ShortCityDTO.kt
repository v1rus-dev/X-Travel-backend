package yegor.cheprasov.xtravel.data.database.dto.cities

data class ShortCityDTO(
    val cityId: Long,
    val countryId: Long,
    val cityName: String,
    val countryName: String,
    val cityFolderName: String,
    val parentCountryId: Long,
    val parentCountryFolderName: String
)
