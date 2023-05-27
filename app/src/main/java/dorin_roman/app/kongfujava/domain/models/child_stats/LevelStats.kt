package dorin_roman.app.kongfujava.domain.models.child_stats

data class LevelStats(
    val id: Int = -1,
    var worldId: Int = -1,
    var number: Int = 0,
    var stars: Int = 0,
    var timeInMinutes: Int = 0,
    var help: Int = 0,
    var attempts: Int = 0,
    var mistakes: Int = 0,
)