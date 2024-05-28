package com.example.mpproject.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mpproject.data.Survey
import com.example.mpproject.repository.FirebaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SurveyViewModel(private val repository: FirebaseRepository) : ViewModel() {
    private val _surveys = MutableLiveData<List<Survey>?>()
    val surveys: LiveData<List<Survey>?> = _surveys

    init {
        loadSurveys()
    }

    private fun loadSurveys() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getSurveys { surveyList ->
                _surveys.postValue(surveyList)
            }
        }
    }

    fun sortSurveys(sortType: SortType) {
        val sortedList = _surveys.value?.let {
            when (sortType) {
                SortType.LATEST -> it.sortedBy { survey -> survey.createdDate }
                SortType.MOST_LIKED -> it.sortedByDescending { survey -> survey.likes }
                SortType.MOST_RESPONDED -> it.sortedByDescending { survey -> survey.responses }
            }
        }
        _surveys.value = sortedList
    }
}

enum class SortType {
    LATEST, MOST_LIKED, MOST_RESPONDED
}