package com.example.mpproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mpproject.dao.UserDao
import com.example.mpproject.data.User
import com.example.mpproject.ui.theme.MPProjectTheme
import com.google.firebase.Firebase
import com.google.firebase.database.database

class MainActivity : ComponentActivity() {
    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userDao = UserDao()  // Initialize the UserDao instance

        setContent {
            MPProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UserInterface()
                }
            }
        }
    }

    @Composable
    fun UserInterface() {
        var userData by remember { mutableStateOf<User?>(null) }
        var greetingMessage by remember { mutableStateOf("Loading...") }

        Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = {
                val newUser = User("user123", "user@example.com", 100, "2024-01-01T00:00:00Z", "2024-01-02T00:00:00Z", "Active", "https://example.com/profile.jpg")
                userDao.createUser(newUser) {
                    greetingMessage = "User Created Successfully"
                }
            }) {
                Text("Create User")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = {
                userDao.readUser("user123") { user ->
                    userData = user
                    greetingMessage = if (user != null) "Hello ${user.email}!" else "User not found."
                }
            }) {
                Text("Read User")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(greetingMessage)
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        MPProjectTheme {
            UserInterface()
        }
    }
}