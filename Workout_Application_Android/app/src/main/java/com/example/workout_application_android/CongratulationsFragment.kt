package com.example.workout_application_android

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.workout_application_android.databinding.FragmentCongratulationsBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CongradulationsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CongradulationsFragment : Fragment(R.layout.fragment_congratulations) {

    private lateinit var binding: FragmentCongratulationsBinding

    override fun onViewCreated(view: View,savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentCongratulationsBinding.bind(view)


        binding.celebrationBtn.setOnClickListener{
            binding.viewKonfetti.build()
                .addColors(Color.CYAN,Color.MAGENTA,Color.YELLOW,Color.RED)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .setPosition(-50f, binding.viewKonfetti.width + 50f, -50f, -50f)
                .streamFor(300, 5000L)
        }

    }



}