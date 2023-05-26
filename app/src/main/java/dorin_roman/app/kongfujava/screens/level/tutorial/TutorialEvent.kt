package dorin_roman.app.kongfujava.screens.level.tutorial

sealed class TutorialEvent {
    class InitTutorial(val levelId: Int) : TutorialEvent()
    class UpdateNextClicked(val isNextClicked: Boolean) : TutorialEvent()

    object FinishLevel : TutorialEvent()

    object  VideoWatched : TutorialEvent()

}