package com.example.workout_application_android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.workout_application_android.databinding.FragmentWorkoutDetailBinding


class WorkoutDetailFragment : Fragment(R.layout.fragment_workout_detail) {
    private lateinit var binding: FragmentWorkoutDetailBinding
    private val args: WorkoutDetailFragmentArgs by navArgs()

    private val viewModel: WorkoutViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWorkoutDetailBinding.bind(view)

        binding.titleDetail.text = args.workout.title
        binding.exercisesDetail.text = "${args.workout.detail} min"

        val array: Array<String> = args.workout.exercises.toTypedArray()

        val arrayAdapter: ArrayAdapter<String>

        val localStorage = LocalStorage(requireContext())

        arrayAdapter = ArrayAdapter(this.requireContext(),android.R.layout.simple_list_item_1, array)

        binding.workoutExercises.adapter = arrayAdapter

        binding.workoutImageDetail.setImageResource(args.workout.icon)

        binding.btnComplete.setOnClickListener{ workoutCompleted(localStorage,args.workout.detail.toInt())

        }

    }

    fun workoutCompleted(localStorage: LocalStorage, time: Int){

        var timeWorked = localStorage.retrieve<Int>("Time_WorkedOut")
        var workOuts = localStorage.retrieve<Int>("Workout_Completed")

        workOuts = workOuts?.plus(1)
        timeWorked = timeWorked?.plus(time)

        localStorage.save("Time_WorkedOut",timeWorked)
        localStorage.save("Workout_Completed",workOuts)

        activity?.onBackPressed()
    }


}
