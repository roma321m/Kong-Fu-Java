package dorin_roman.app.kongfujava.di.provider

import kotlin.random.Random

object CodeProvider {

    private const val STRING_LENGTH = 6

    fun provide(): String {
        val charPool: MutableList<Char> = ('A'..'Z').toMutableList()
        charPool.remove('I')
        return (1..STRING_LENGTH)
            .map {
                Random.nextInt(0, charPool.size).let {
                    charPool[it]
                }
            }
            .joinToString("")
    }

}