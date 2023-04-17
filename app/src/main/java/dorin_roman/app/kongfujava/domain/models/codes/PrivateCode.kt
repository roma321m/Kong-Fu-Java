package dorin_roman.app.kongfujava.domain.models.codes

data class PrivateCode(
    override val code: String,
    override val supervisorId: String,
    val childId: String,
) : Code {
    constructor() : this("", "", "")
}