package com.example.workout_application_android

import android.content.ContentValues.TAG
import android.util.Log
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@OptIn(ExperimentalSerializationApi::class)
class WorkoutRepository {
    fun getWorkouts(localStorage: LocalStorage): List<Workout> {


        var listItem: MutableList<Workout> = mutableListOf()

        val list1 = listOf("pushups", "Bench Press", "Situps")
//        val list2 = listOf("Squats", "Jumps", "Jumping Jacks")
//        val list3 = listOf("Situps", "Abs", "Crunches")
//        val list4 = listOf("Lunges", "Jumping Jacks", "Cardio")

        listItem.add(Workout(R.drawable.work1,"Heavy Arms", "20", list1))
//        listItem.add(Workout(R.drawable.work2,"Heeey", "30",list2))
//        listItem.add(Workout(R.drawable.work3,"yessir h", "40",list3))
//        listItem.add(Workout(R.drawable.work4,"HUmmm", "50",list4))

        val workout = localStorage.retrieve<String>("workouts")

        var json: String

        val regex = Regex("(?<=[@])|(?=[@])")

        val works = workout?.split(regex)?.toTypedArray()
        //val decodedJSON = Json.decodeFromString<Workout>(workout)
        works?.forEach {
            //Log.d(TAG,"${it}\n")
            if(!it.equals("@") && it.length>0)
            {

                //json = Json.encodeToString(workout)
                var string = Json.decodeFromString<Workout>(it)
                Log.d(TAG,string.title)

                listItem.add(string)
            }

        }


        return listItem

    }
}