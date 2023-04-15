package dorin_roman.app.kongfujava.util

fun formatTime(seconds: String, minutes: String, hours: String): String {
    return "$hours:$minutes:$seconds"
}

fun formatTime(seconds: String, minutes: String): String {
    return "$minutes:$seconds"
}

fun Int.pad(): String {
    return this.toString().padStart(2, '0')
}