package com.example.mpproject

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mpproject.survey.AnswerSurveyScreen
import com.example.mpproject.survey.CreateSurveyScreen
import com.example.mpproject.survey.QuestionItem
import com.example.mpproject.survey.SurveyResultScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "createSurvey") {
        composable("createSurvey") {
            CreateSurveyScreen { title, questions ->
                navController.navigate("surveyResult/$title") {
                    popUpTo("createSurvey") { inclusive = true }
                }
            }
        }
        composable("answerSurvey") {
            AnswerSurveyScreen("사용자 만족도 조사", sampleQuestions) { answers ->
                println("Submitted answers: $answers")
            }
        }
        composable("surveyResult/{title}") { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: "No Title"
            SurveyResultScreen(title, sampleQuestions)
        }
    }
}

val sampleQuestions = listOf(
    QuestionItem("질문1", mutableListOf("답변1-1", "답변1-2", "답변1-3"), "single"),
    QuestionItem("질문2", mutableListOf("답변2-1", "답변2-2", "답변2-3", "답변2-4", "답변2-5"), "multiple"),
    QuestionItem("질문3", mutableListOf("답변3-1", "답변3-2", "답변3-3", "답변3-4", "답변3-5", "답변3-6"), "single"),
    QuestionItem("질문4", mutableListOf("답변4-1", "답변4-2", "답변4-3", "답변4-4"), "multiple")
)