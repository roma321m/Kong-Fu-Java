package dorin_roman.app.kongfujava.domain.models.codes

data class PublicCode(
    override val code: String,
    override val supervisorId: String,
    val timeMilli: Long,
) : Code {
    constructor() : this("", "", 0L)
}