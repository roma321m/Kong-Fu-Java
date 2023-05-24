package dorin_roman.app.kongfujava.screens.level.tutorial

sealed class TutorialEvent {
    class InitTutorial(val levelId: Int) : TutorialEvent()
}