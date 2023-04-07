package dorin_roman.app.kongfujava.data.repository

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue
import dorin_roman.app.kongfujava.domain.models.FirebaseRequestState
import dorin_roman.app.kongfujava.domain.models.FirebaseRequestState.Failure
import dorin_roman.app.kongfujava.domain.models.FirebaseRequestState.Success
import dorin_roman.app.kongfujava.domain.models.users.Child
import dorin_roman.app.kongfujava.domain.models.users.Parent
import dorin_roman.app.kongfujava.domain.models.users.Teacher
import dorin_roman.app.kongfujava.domain.repository.UsersRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsersRepositoryImpl @Inject constructor(
    database: FirebaseDatabase
) : UsersRepository {

    companion object {
        private const val USERS = "Users"
        private const val TEACHER = "Teachers"
        private const val PARENT = "Parent"
        private const val CHILD = "Child"
    }

    private val teacherRef = database.getReference(USERS).child(TEACHER)
    private val parentRef = database.getReference(USERS).child(PARENT)
    private val childRef = database.getReference(USERS).child(CHILD)

    override suspend fun createTeacher(
        id: String,
        teacher: Teacher
    ): FirebaseRequestState<Boolean> {
        return try {
            teacherRef.child(id).setValue(teacher).await()
            Success(true)
        } catch (exception: Exception) {
            Failure(exception)
        }
    }

    override suspend fun deleteTeacher(id: String): FirebaseRequestState<Boolean> {
        return try {
            teacherRef.child(id).removeValue().await()
            Success(true)
        } catch (exception: Exception) {
            Failure(exception)
        }
    }

    override suspend fun getTeacher(id: String): FirebaseRequestState<Teacher> {
        // TODO - not tested yet
        return try {
            var response: FirebaseRequestState<Teacher> = Success(Teacher())
            teacherRef.child(id).get()
                .addOnSuccessListener { dataSnapshot ->
                    dataSnapshot.getValue<Teacher>()?.let { teacher ->
                        response = Success(teacher)
                    }
                }.addOnFailureListener { exception ->
                    response = Failure(exception)
                }.await()
            response
        } catch (exception: Exception) {
            Failure(exception)
        }
    }

    override suspend fun createParent(id: String, parent: Parent): FirebaseRequestState<Boolean> {
        return try {
            parentRef.child(id).setValue(parent).await()
            Success(true)
        } catch (exception: Exception) {
            Failure(exception)
        }
    }

    override suspend fun deleteParent(id: String): FirebaseRequestState<Boolean> {
        return try {
            parentRef.child(id).removeValue().await()
            Success(true)
        } catch (exception: Exception) {
            Failure(exception)
        }
    }

    override suspend fun getParent(id: String): FirebaseRequestState<Parent> {
        TODO("Not yet implemented")
    }

    override suspend fun createChild(id: String, child: Child): FirebaseRequestState<Boolean> {
        return try {
            childRef.child(id).setValue(child).await()
            Success(true)
        } catch (exception: Exception) {
            Failure(exception)
        }
    }

    override suspend fun deleteChild(id: String): FirebaseRequestState<Boolean> {
        return try {
            childRef.child(id).removeValue().await()
            Success(true)
        } catch (exception: Exception) {
            Failure(exception)
        }
    }

    override suspend fun getChild(id: String): FirebaseRequestState<Child> {
        TODO("Not yet implemented")
    }

}