package dorin_roman.app.kongfujava.screens.supervisor.progress_report.components

data class LevelStatsModel(
    val number: Int,
    val stars: Int = 0,
    val timeInMinutes: Int = 0,
    val helps: Int = 0,
    val mistakes: Int = 0,
    val attempts: Int = 0
)
