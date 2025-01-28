package yegor.cheprasov.xtravel.data.database.dto.country

data class CountryInfoDTO(
    val countryId: Long,
    val name: String,
    val description: String,
    val shortName: String,
    val folderName: String,
    val phoneCode: Int,
    val isoCode: String,
    val areaKm2: Double,
    val currencyCode: String,
    val currencyFontCode: String,
)
