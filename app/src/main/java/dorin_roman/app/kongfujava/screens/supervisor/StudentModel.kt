package dorin_roman.app.kongfujava.screens.supervisor

data class StudentModel(
    var id: String = "id",
    var name: String = "name",
    var age: Int = 0,
    var imageUrl: String = "",
    var privateCode: String = "AAAAAA",
    var selected: Boolean = false
)