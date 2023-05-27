package dorin_roman.app.kongfujava.screens.supervisor.progress_report

import dorin_roman.app.kongfujava.R

fun getWorldName(worldId: Int): Int = when (worldId) {
    0 -> R.string.world_map_variables
    1 -> R.string.world_map_conditions_if_else
    2 -> R.string.world_map_loops
    3 -> R.string.world_map_arrays
    4 -> R.string.world_map_strings
    else -> R.string.world_map_unknown
}