package dorin_roman.app.kongfujava.screens.supervisor.progress_report

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.data.models.RequestState
import dorin_roman.app.kongfujava.domain.models.child_stats.ActiveTime
import dorin_roman.app.kongfujava.domain.models.child_stats.LevelStats
import dorin_roman.app.kongfujava.domain.repository.ChildStatsRepository
import dorin_roman.app.kongfujava.screens.supervisor.StudentModel
import dorin_roman.app.kongfujava.screens.supervisor.SupervisorToast
import dorin_roman.app.kongfujava.ui.toast.ToastLauncher
import dorin_roman.app.kongfujava.util.ComparatorDate
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import javax.inject.Inject
import kotlin.math.roundToInt


@HiltViewModel
class SupervisorProgressReportViewModel @Inject constructor(
    private val childStatsRepository: ChildStatsRepository,
    private val toastLauncher: ToastLauncher
) : ViewModel() {

    companion object {
        const val TAG = "SupervisorAddUsersViewModel"
    }

    var selectedStudent by mutableStateOf(StudentModel())
        private set

    var isActiveTimeLoading by mutableStateOf(false)
        private set

    var isLevelsStatsLoading by mutableStateOf(false)
        private set

    var currentLevelNumber by mutableStateOf(0)
        private set

    var currentWorld by mutableStateOf("")
        private set

    var levelStatsModelList by mutableStateOf(emptyArray<LevelStatsModel>())
        private set

    var currentActivityStats by mutableStateOf(emptyMap<String, Int>())
        private set

    var currentLevelInfoStats by mutableStateOf(LevelStatsModel())
        private set

    var currentOverallStats by mutableStateOf(emptyMap<String, Int>())
        private set

    var selectedOverallStats by mutableStateOf(0)
        private set

    val items = arrayListOf(
        R.string.progress_report_stars,
        R.string.progress_report_time,
        R.string.progress_report_helps,
        R.string.progress_report_attempts,
        R.string.progress_report_mistakes
    )

    private var starsMap = emptyMap<String, Int>()
    private var timeMap = emptyMap<String, Int>()
    private var helpsMap = emptyMap<String, Int>()
    private var attemptsMap = emptyMap<String, Int>()
    private var mistakesMap = emptyMap<String, Int>()

    fun handle(event: SupervisorProgressReportEvent) {
        when (event) {
            is SupervisorProgressReportEvent.UpdateSelectedStudent -> updateSelectedStudent(event.student)
            is SupervisorProgressReportEvent.UpdateSelectedLevelInfo -> updateSelectedLevelInfo(
                event.levelId
            )

            is SupervisorProgressReportEvent.UpdateSelectedOverallStats -> updateSelectedOverallStats(
                event.index
            )
        }
    }

    private fun updateSelectedStudent(student: StudentModel) {
        Log.d(TAG, "updateSelectedStudent: $student")
        resetData()
        selectedStudent = student
        getActiveTime()
        getLevelStats()
    }

    private fun updateSelectedLevelInfo(levelId: Int) {
        currentLevelInfoStats = levelStatsModelList.firstOrNull {
            it.id == levelId
        } ?: currentLevelInfoStats
    }

    private fun updateSelectedOverallStats(index: Int) {
        selectedOverallStats = index
        currentOverallStats = when (index) {
            0 -> starsMap
            1 -> timeMap
            2 -> helpsMap
            3 -> attemptsMap
            4 -> mistakesMap
            else -> {
                selectedOverallStats = 0
                starsMap
            }
        }
    }

    private fun resetData() {
        currentActivityStats = mapOf()
        levelStatsModelList = emptyArray()
        currentLevelInfoStats = LevelStatsModel()
        currentLevelNumber = 0
        currentWorld = ""
        selectedOverallStats = 0
        currentOverallStats = emptyMap()
        starsMap = emptyMap()
        timeMap = emptyMap()
        helpsMap = emptyMap()
        attemptsMap = emptyMap()
        mistakesMap = emptyMap()
    }

    private fun getLevelStats() = viewModelScope.launch {
        Log.d(TAG, "getLevelStats")
        isLevelsStatsLoading = true
        childStatsRepository.getLevelStatsList(
            selectedStudent.id
        ).also { response ->
            if (response is RequestState.Success) {
                updateLevelStatsModel(response.data)
            } else if (response is RequestState.Error) {
                isLevelsStatsLoading = false
                response.apply {
                    toastLauncher.launch(SupervisorToast.SomethingWentWrong)
                    Log.e(TAG, "${error.message}")
                }
            }
        }
    }

    private fun getActiveTime() = viewModelScope.launch {
        Log.d(TAG, "getActiveTime")
        isActiveTimeLoading = true
        childStatsRepository.getActiveTimeList(
            selectedStudent.id
        ).also { response ->
            if (response is RequestState.Success) {
                updateActivityGraphData(response.data)
            } else if (response is RequestState.Error) {
                isActiveTimeLoading = false
                response.apply {
                    toastLauncher.launch(SupervisorToast.SomethingWentWrong)
                    Log.e(TAG, "${error.message}")
                }
            }
        }
    }

    private fun updateLevelStatsModel(levelStatsList: List<LevelStats>) {
        Log.d(TAG, "updateLevelStatsModel")
        val levels = mutableListOf<LevelStatsModel>()
        levelStatsList.forEach { levelStats ->
            levels.add(
                LevelStatsModel(
                    id = levelStats.id,
                    world = levelStats.worldId.toString(),
                    number = levelStats.number,
                    stars = levelStats.stars,
                    timeInMinutes = levelStats.timeInMinutes,
                    helps = levelStats.help,
                    mistakes = levelStats.mistakes,
                    attempts = levelStats.attempts,
                )
            )
        }
        levelStatsModelList = levels.toTypedArray()
        updateData()
        isLevelsStatsLoading = false
    }

    private fun updateData() {
        Log.d(TAG, "updateData")
        if (levelStatsModelList.isEmpty()) {
            return
        }
        currentLevelInfoStats = levelStatsModelList.first()
        currentLevelNumber = levelStatsModelList.last().number
        currentWorld = levelStatsModelList.last().world

        starsMap = levelStatsModelList.associate { level ->
            Pair(level.world + "-" + level.number, level.stars)
        }
        timeMap = levelStatsModelList.associate { level ->
            Pair(level.world + "-" + level.number, level.timeInMinutes)
        }
        helpsMap = levelStatsModelList.associate { level ->
            Pair(level.world + "-" + level.number, level.helps)
        }
        attemptsMap = levelStatsModelList.associate { level ->
            Pair(level.world + "-" + level.number, level.attempts)
        }
        mistakesMap = levelStatsModelList.associate { level ->
            Pair(level.world + "-" + level.number, level.mistakes)
        }
        currentOverallStats = starsMap
        selectedOverallStats = 0
    }

    private fun updateActivityGraphData(activeTimeList: List<ActiveTime>) {
        Log.d(TAG, "updateActivityGraphData")
        val map = mutableMapOf<String, Int>()
        activeTimeList.forEach { activeTime ->
            val date = getDate(activeTime.fromInMilli)
            val minutes = map[date] ?: 0
            map[date] = minutes + getMinutes(activeTime)
        }
        currentActivityStats = map.toSortedMap(
            ComparatorDate()
        )
        isActiveTimeLoading = false
    }

    private fun getMinutes(activeTime: ActiveTime): Int {
        val timeInMinutes =
            ((activeTime.toInMilli - activeTime.fromInMilli).toFloat() / 60_000f).roundToInt()
        return if (timeInMinutes != 0) {
            timeInMinutes
        } else {
            1
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun getDate(milli: Long): String {
        val simpleDateFormat = SimpleDateFormat("dd/MM")
        return simpleDateFormat.format(milli)
    }

}