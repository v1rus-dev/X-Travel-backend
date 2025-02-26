package yegor.cheprasov.xtravel.entities.enums

enum class UserRole(val id: String) {
    Default("default"),
    Admin("admin"),
    Expert("expert");

    companion object {
        fun getById(id: String) = entries.firstOrNull { it.id == id }
    }
}