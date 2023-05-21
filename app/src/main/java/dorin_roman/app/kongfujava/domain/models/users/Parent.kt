package dorin_roman.app.kongfujava.domain.models.users

data class Parent(
    override val id: String,
    override val imageUrl: String = "",
    val email: String? = null
) : User {
    constructor() : this(id = "")
}