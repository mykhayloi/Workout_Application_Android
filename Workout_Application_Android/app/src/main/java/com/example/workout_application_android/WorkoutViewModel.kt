package com.example.workout_application_android

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WorkoutViewModel (
    private val repository: WorkoutRepository = WorkoutRepository()
): ViewModel() {
    val workoutData: MutableLiveData<List<Workout>> = MutableLiveData()

//    init {
//        getWorkouts()
//    }

    fun getWorkouts(localStorage: LocalStorage) {
        viewModelScope.launch(Dispatchers.IO) {
            val workouts = repository.getWorkouts(localStorage)


            workoutData.postValue(workouts)
        }
    }
}