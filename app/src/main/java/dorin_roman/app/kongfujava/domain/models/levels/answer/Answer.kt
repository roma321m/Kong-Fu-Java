package dorin_roman.app.kongfujava.domain.models.levels.answer

interface Answer {
    val id: Int
    val levelId: Int
    var answer1: String
    var answer2: String
    var answer3: String
    var answer4: String
    var right: String
}