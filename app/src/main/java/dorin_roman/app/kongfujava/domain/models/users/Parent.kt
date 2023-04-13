package dorin_roman.app.kongfujava.domain.models.users

data class Parent(
    override val id: String,
    val email: String? = null
) : User {
    constructor() : this("")
}