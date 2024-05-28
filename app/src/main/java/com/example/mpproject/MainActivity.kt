package com.example.mpproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.mpproject.myPage.MyPage
import com.example.mpproject.setting.MyTheme
import com.example.mpproject.ui.theme.MPProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MPProjectTheme {
                // A surface container using the 'background' color from the theme
                var isDarkTheme by remember { mutableStateOf(false) }
                MyTheme(darkTheme = isDarkTheme) {
                    Surface(color = MaterialTheme.colors.background) {
                        //Setting(isDarkTheme) { isDarkTheme = it }
                        MyPage()
                    }
                }
            }
        }
    }
}

