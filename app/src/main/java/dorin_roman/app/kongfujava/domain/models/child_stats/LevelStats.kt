package dorin_roman.app.kongfujava.domain.models.child_stats

data class LevelStats(
    val id: Int = -1,
    val world: String = "",
    val number: Int = 0,
    val stars: Int = 0,
    val timeInMinutes: Int = 0,
    val help: Int = 0,
    val attempts: Int = 0,
    val mistakes: Int = 0,
)