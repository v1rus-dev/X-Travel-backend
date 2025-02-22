package yegor.cheprasov.xtravel.data.database.dto.cities

import yegor.cheprasov.xtravel.data.database.dto.utils.LocalizationText

data class FullCityDTO(
    val cityId: Long,
    val countryId: Long,
    val folderName: String,
    val internalName: String,
    val isCapital: Boolean,
    val latitude: Double,
    val longitude: Double,
    val descriptions: List<LocalizationText>,
    val names: List<LocalizationText>
)