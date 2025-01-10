package yegor.cheprasov.xtravel.features.utils

import kotlinx.serialization.Serializable

@Serializable
data class CompressImageReceiveRemote(
    val folderName: String
)