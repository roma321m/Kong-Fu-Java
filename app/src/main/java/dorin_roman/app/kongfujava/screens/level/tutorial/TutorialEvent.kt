package dorin_roman.app.kongfujava.screens.level.tutorial

sealed class TutorialEvent {
    class InitLevel(val levelId: Int) : TutorialEvent()
}