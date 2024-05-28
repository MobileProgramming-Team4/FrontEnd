package com.example.mpproject.dao

import com.example.mpproject.data.User
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.getValue

class UserDao {
    private val db = FirebaseDatabase.getInstance().getReference("users")

    fun createUser(user: User, callback: (Boolean) -> Unit) {
        db.child(user.userId).setValue(user)
            .addOnSuccessListener {
                // Callback with true when user is successfully created
                callback(true)
            }
            .addOnFailureListener { exception ->
                // Callback with false if there is an error
                println("Error creating user: ${exception.message}")
                callback(false)
            }
    }

    fun readUser(userId: String, callback: (User?) -> Unit) {
        db.child(userId).get().addOnSuccessListener { dataSnapshot ->
            val user = dataSnapshot.getValue<User>()
            if (user != null) {
                // Return the user if found
                callback(user)
            } else {
                // Return null if user is not found
                println("User not found")
                callback(null)
            }
        }.addOnFailureListener { exception ->
            // Log error and return null if there is an error
            println("Error fetching user: ${exception.message}")
            callback(null)
        }
    }

    fun updateUser(userId: String, updatedFields: Map<String, Any>, callback: (Boolean) -> Unit) {
        db.child(userId).updateChildren(updatedFields)
            .addOnSuccessListener {
                // Callback with true when user is successfully updated
                callback(true)
            }
            .addOnFailureListener { exception ->
                // Callback with false if there is an error
                println("Error updating user: ${exception.message}")
                callback(false)
            }
    }

    fun deleteUser(userId: String, callback: (Boolean) -> Unit) {
        db.child(userId).removeValue()
            .addOnSuccessListener {
                // Callback with true when user is successfully deleted
                callback(true)
            }
            .addOnFailureListener { exception ->
                // Callback with false if there is an error
                println("Error deleting user: ${exception.message}")
                callback(false)
            }
    }
}