package com.example.mpproject.data


data class Survey(
    val surveyId: String = "",
    val creatorId: String = "",
    val title: String = "",
    val content: String = "",
    val createdDate: String = "",
    val modifiedDate: String = "",
    val status: String = "",
    val questions: Map<String, Question> = emptyMap()
) {
    constructor() : this("", "", "", "", "", "", "", emptyMap())
}