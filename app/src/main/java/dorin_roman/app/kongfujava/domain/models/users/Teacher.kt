package dorin_roman.app.kongfujava.domain.models.users

data class Teacher(
    override val id: String,
    override val imageUrl: String = "",
    val email: String? = null,
    val className: String? = null,
    val schoolName: String? = null,
) : User {
    constructor() : this(id = "")
}