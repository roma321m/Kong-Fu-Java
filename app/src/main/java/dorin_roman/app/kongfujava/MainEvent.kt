package dorin_roman.app.kongfujava

sealed class MainEvent {
    object Child : MainEvent()
    class IsBindCodeService(val isBind: Boolean) : MainEvent()
}