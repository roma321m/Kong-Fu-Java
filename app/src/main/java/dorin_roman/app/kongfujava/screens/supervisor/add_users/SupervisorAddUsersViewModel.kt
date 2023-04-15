package dorin_roman.app.kongfujava.screens.supervisor.add_users

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dorin_roman.app.kongfujava.di.provider.CodeProvider
import dorin_roman.app.kongfujava.domain.repository.AuthRepository
import dorin_roman.app.kongfujava.domain.repository.CodeRepository
import dorin_roman.app.kongfujava.util.pad
import java.util.Timer
import javax.inject.Inject
import kotlin.concurrent.fixedRateTimer
import kotlin.time.Duration
import kotlin.time.Duration.Companion.ZERO
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds


@HiltViewModel
class SupervisorAddUsersViewModel @Inject constructor(
    private val codeProvider: CodeProvider,
    private val authRepository: AuthRepository,
    private val codeRepository: CodeRepository
) : ViewModel() {

    companion object {
        const val TAG = "SupervisorAddUsersViewModel"
    }

    private var duration: Duration = 5.minutes
    private lateinit var timer: Timer

    var seconds by mutableStateOf("00")
        private set

    var minutes by mutableStateOf("05")
        private set

    var code by mutableStateOf("")
        private set

    var hasActiveCode by mutableStateOf(false)
        private set

    private val userId get() = authRepository.currentUser?.uid

    fun handle(event: SupervisorAddUsersEvent) {
        when (event) {
            SupervisorAddUsersEvent.CreateCodeClicked -> startTimer()
        }
    }

    private fun createCode() {
        Log.d(TAG, "createCode")
        code = codeProvider.provide()
    }

    private fun getCurrentTime(): Long = System.currentTimeMillis()

    private fun startTimer() {
        Log.d(TAG, "startTimer")
        hasActiveCode = true
        createCode() // fixme - temp
        timer = fixedRateTimer(initialDelay = 1000L, period = 1000L) {
            duration = duration.minus(1.seconds)
            if (duration == ZERO) {
                dismissTimer()
            }
            updateTimeUnits()
        }
    }

    private fun dismissTimer() {
        Log.d(TAG, "dismissTimer")
        if (this::timer.isInitialized) {
            timer.cancel()
        }
        duration = 5.minutes
        hasActiveCode = false
        updateTimeUnits()
    }

    private fun updateTimeUnits() {
        duration.toComponents { _, m, s, _ ->
            minutes = m.pad()
            seconds = s.pad()
        }
    }
}