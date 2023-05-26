package dorin_roman.app.kongfujava.screens.level.multi_choice

sealed class MultiEvent {
    class InitAnswers(val levelId: Int) : MultiEvent()
    object GetHint : MultiEvent()
    class CheckAnswer(val answer: String) : MultiEvent()
    object UpdateLevelHint : MultiEvent()
    object UpdateLevelMistakes : MultiEvent()

    object FinishLevel : MultiEvent()

}