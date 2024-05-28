package com.example.mpproject.survey

data class QuestionItem(
    var question: String,
    var answers: MutableList<String>,
    var questionType: String // radiobutton: "single", checkbox: "multiple"
)