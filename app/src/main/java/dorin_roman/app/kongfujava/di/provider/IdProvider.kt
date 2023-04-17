package dorin_roman.app.kongfujava.di.provider

import kotlin.random.Random

object IdProvider {

    private const val STRING_LENGTH = 25

    fun provide(): String {
        val charPool: List<Char> = ('A'..'Z') + ('0'..'1') + ('a'..'z')
        return (1..STRING_LENGTH)
            .map {
                Random.nextInt(0, charPool.size).let {
                    charPool[it]
                }
            }
            .joinToString("")
    }
}