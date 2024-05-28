package com.example.mpproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.mpproject.navigation.Route
import com.example.mpproject.navigation.NavGraph
import com.example.mpproject.ui.theme.MPProjectTheme
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MPProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val auth = FirebaseAuth.getInstance()

                    if (auth.currentUser == null) {
                        NavGraph(navController = navController, startDestination = Route.Start)

                    } else {
                        NavGraph(navController = navController, startDestination = Route.Home)
                    }

                }
            }
        }
    }
}