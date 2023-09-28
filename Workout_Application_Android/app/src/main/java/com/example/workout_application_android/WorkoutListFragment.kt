package com.example.workout_application_android

import android.app.AlertDialog
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workout_application_android.databinding.FragmentWorkoutListBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.json.JSONException
import java.util.*

class WorkoutListFragment : Fragment(R.layout.fragment_workout_list) {

    private val quoteViewModel: QuoteViewModel by activityViewModels()
    private val viewModel: WorkoutViewModel by activityViewModels()
    private lateinit var binding: FragmentWorkoutListBinding


    private val adapter = WorkoutAdapter{ workout ->
        val direction = WorkoutListFragmentDirections.actionGlobalWorkoutDetailFragment(workout)
        findNavController().navigate(direction)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWorkoutListBinding.bind(view)

        val localStorage = LocalStorage(requireContext())

        viewModel.getWorkouts(localStorage)

        binding.appInspirations.text = localStorage.retrieve<Int>("Inspirations").toString()
        binding.workoutsCompleted.text = localStorage.retrieve<Int>("Workout_Completed").toString()
        binding.timeWorkedout.text = localStorage.retrieve<Int>("Time_WorkedOut").toString()

        binding.btnInspire.setOnClickListener { getInspired(localStorage) }

        binding.createWorkout.setOnClickListener{ openCreateWorkout(localStorage) }

        binding.btnHelp.setOnClickListener{ helpButton(localStorage) }

        binding.rvWorkouts.layoutManager = LinearLayoutManager(requireContext())
        binding.rvWorkouts.adapter = adapter

        viewModel.workoutData.observe(viewLifecycleOwner) { workouts ->
            adapter.setData(workouts)

        }

        quoteViewModel.getData()




    }

    private fun toastName() {
        Log.d(ContentValues.TAG,"clicked")



    }

    private fun helpButton(localStorage: LocalStorage) {

        val string: String =""
        localStorage.save("workouts",string)
        localStorage.save("Workout_Completed",0)
        localStorage.save("Inspirations",0)
        localStorage.save("Time_WorkedOut",0)

        binding.appInspirations.text = "0"
        binding.workoutsCompleted.text = "0"
        binding.timeWorkedout.text = "0"

        viewModel.getWorkouts(localStorage)

        var builder = AlertDialog.Builder(context);
        builder.setTitle("Help")
        builder.setMessage("This progress section shows how often you've been inspired, how many time workouts you've completed, and total time worked out! \n In addition, it will reset all your saved data and launch the project :)")
        builder.setCancelable(true)
        builder.setPositiveButton("OK",{dialog, id ->
            dialog.cancel()
        })

        var alert = builder.create()
        alert.show()
    }

    private fun getInspired(localStorage: LocalStorage) {

        //val localStorage = LocalStorage(this.requireContext())

        var theQuote: Quote = Quote("You miss 100% of the shots you don't take - Wayne Gretzky","Michael Scott")

        for(lists in quoteViewModel.quotes) {

            theQuote = lists.get((0..30).random())
        }
        var builder = AlertDialog.Builder(context)
        builder.setTitle("Insipration")
        builder.setMessage("${theQuote.text} \n - ${theQuote.author}")
        builder.setCancelable(true)
        builder.setPositiveButton("Im inspired!", { dialog, id ->
            dialog.cancel()
        })

        var numInspirations = localStorage.retrieve<Int>("Inspirations")?.plus(1)
        Log.d(TAG,"${numInspirations}")
        localStorage.save("Inspirations", numInspirations)
        binding.appInspirations.text = localStorage.retrieve<Int>("Inspirations").toString()


        var alert = builder.create()
        alert.show()
    }

    private fun openCreateWorkout(localStorage: LocalStorage) {

        val mDialogView = LayoutInflater.from(this.requireContext()).inflate(R.layout.layout_create,null)
        var array: Array<String> = arrayOf()








        val builder = MaterialAlertDialogBuilder(this.requireContext())
            .setView(mDialogView)
            .setTitle("Create Workout")

            .setPositiveButton("Create") { dialog, id ->

                val title = mDialogView.findViewById<EditText>(R.id.et_title).getText().toString()

                val duration =
                    mDialogView.findViewById<EditText>(R.id.et_duration).getText().toString()
                var exercises: Array<String> = arrayOf()
                exercises = array

                if(title.isBlank() || duration.isBlank()){
                    var builder = AlertDialog.Builder(context)
                    builder.setTitle("Missing Info")
                    builder.setMessage("The workout was not created. Make sure when creating a workout you give it a name and a duration.")
                    builder.setCancelable(true)
                    builder.setPositiveButton("Ok", { dialog, id ->
                        dialog.cancel()
                    })

                    var alert = builder.create()
                    alert.show()
                }
                else{
                    var json = ""

                    try {

                        val res = arrayOf(R.drawable.work1,R.drawable.work2,R.drawable.work3,R.drawable.work4,R.drawable.work5,R.drawable.work6,R.drawable.work8)

                        val choice = (1..6).random()

                        val workout = Workout(res[choice], title, duration, exercises.toList())

                        json = Json.encodeToString(workout)

                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }

                    val currentWorkouts = localStorage.retrieve<String>("workouts")
                    val updatedWorkouts = currentWorkouts.plus("@").plus(json)

                    localStorage.save("workouts",updatedWorkouts)

                    Log.d(TAG, currentWorkouts.toString())
                    Log.d(TAG, updatedWorkouts.toString())

                    viewModel.getWorkouts(localStorage)

                }



            }

        val addBtn = mDialogView.findViewById<Button>(R.id.btnCreateWorkout)


        val arrayAdapter: ArrayAdapter<String>

        arrayAdapter = ArrayAdapter(this.requireContext(),android.R.layout.simple_list_item_1, array)

        val list = mDialogView.findViewById<ListView>(R.id.creatingExcerciseList)

        val excName = mDialogView.findViewById<EditText>(R.id.exerciseName)
        val excAmount = mDialogView.findViewById<EditText>(R.id.exerciseAmount)



        addBtn.setOnClickListener{
            val list1: MutableList<String> = array.toMutableList()
            val final = " ${excName.text} x + ${excAmount.text}"
            list1.add(final)

            array = list1.toTypedArray()

            list.adapter = ArrayAdapter(this.requireContext(),android.R.layout.simple_list_item_1,array)
        }

        var alert = builder.create()
        alert.show()

    }



}