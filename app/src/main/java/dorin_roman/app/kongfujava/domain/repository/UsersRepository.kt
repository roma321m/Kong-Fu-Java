package dorin_roman.app.kongfujava.domain.repository

import dorin_roman.app.kongfujava.data.models.RequestState
import dorin_roman.app.kongfujava.domain.models.users.Child
import dorin_roman.app.kongfujava.domain.models.users.Parent
import dorin_roman.app.kongfujava.domain.models.users.Teacher

interface UsersRepository {

    suspend fun createTeacher(
        teacher: Teacher
    ): RequestState<Boolean>

    suspend fun deleteTeacher(
        id: String
    ): RequestState<Boolean>

    suspend fun getTeacher(
        id: String
    ): RequestState<Teacher>

    suspend fun createParent(
        parent: Parent
    ): RequestState<Boolean>

    suspend fun deleteParent(
        id: String
    ): RequestState<Boolean>

    suspend fun getParent(
        id: String
    ): RequestState<Parent>

    suspend fun createChild(
        child: Child
    ): RequestState<Boolean>

    suspend fun deleteChild(
        id: String
    ): RequestState<Boolean>

    suspend fun getChild(
        id: String
    ): RequestState<Child>
}