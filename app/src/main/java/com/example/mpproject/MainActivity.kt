package com.example.mpproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.mpproject.screen.SurveyListScreen
import com.example.mpproject.ui.theme.MPProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MPProjectTheme {
                SurveyListScreen()
            }
        }
    }
}
