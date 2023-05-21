package dorin_roman.app.kongfujava.domain.models.users

data class Child(
    override val id: String,
    override val imageUrl: String = "",
    val privateCode: String,
    val name: String? = null,
    val age: Int? = null
) : User {
    constructor() : this(id = "", privateCode = "")
}