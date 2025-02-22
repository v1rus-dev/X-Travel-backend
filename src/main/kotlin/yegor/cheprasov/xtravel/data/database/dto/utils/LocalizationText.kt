package yegor.cheprasov.xtravel.data.database.dto.utils

import kotlinx.serialization.Serializable

@Serializable
data class LocalizationText(
    val lang: String,
    val text: String
)
