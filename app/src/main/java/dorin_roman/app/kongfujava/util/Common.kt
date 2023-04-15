package dorin_roman.app.kongfujava.util

fun Int.pad(): String {
    return this.toString().padStart(2, '0')
}