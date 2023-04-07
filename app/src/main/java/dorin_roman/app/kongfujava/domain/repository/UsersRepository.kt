package dorin_roman.app.kongfujava.domain.repository

import dorin_roman.app.kongfujava.domain.models.FirebaseRequestState
import dorin_roman.app.kongfujava.domain.models.users.Child
import dorin_roman.app.kongfujava.domain.models.users.Parent
import dorin_roman.app.kongfujava.domain.models.users.Teacher

interface UsersRepository {

    suspend fun createTeacher(
        id: String,
        teacher: Teacher
    ): FirebaseRequestState<Boolean>

    suspend fun deleteTeacher(
        id: String
    ): FirebaseRequestState<Boolean>

    suspend fun getTeacher(
        id: String
    ): FirebaseRequestState<Teacher>

    suspend fun createParent(
        id: String,
        parent: Parent
    ): FirebaseRequestState<Boolean>

    suspend fun deleteParent(
        id: String
    ): FirebaseRequestState<Boolean>

    suspend fun getParent(
        id: String
    ): FirebaseRequestState<Parent>

    suspend fun createChild(
        id: String,
        child: Child
    ): FirebaseRequestState<Boolean>

    suspend fun deleteChild(
        id: String
    ): FirebaseRequestState<Boolean>

    suspend fun getChild(
        id: String
    ): FirebaseRequestState<Child>
}