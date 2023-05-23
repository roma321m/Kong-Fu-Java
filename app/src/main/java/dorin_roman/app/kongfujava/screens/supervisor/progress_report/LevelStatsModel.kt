package dorin_roman.app.kongfujava.screens.supervisor.progress_report

data class LevelStatsModel(
    val id: Int = -1,
    val world: String = "",
    val number: Int = 0,
    val stars: Int = 0,
    val timeInMinutes: Int = 0,
    val helps: Int = 0,
    val mistakes: Int = 0,
    val attempts: Int = 0
)
