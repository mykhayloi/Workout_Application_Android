package com.example.workout_application_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.workout_application_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    private val navBarConfiguration = AppBarConfiguration(setOf(
        R.id.workoutListFragment,
        R.id.mapFragment,
        R.id.congratulationsFragment
    ))

    override fun onSupportNavigateUp(): Boolean = navController.navigateUp()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        navController = (supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController

        binding.bottomNav.setupWithNavController(navController)
        setupActionBarWithNavController(navController,navBarConfiguration)

        navController.addOnDestinationChangedListener { controller,destination,bundle ->
            when(destination.id){
                R.id.workoutListFragment,
                R.id.mapFragment -> binding.bottomNav.visibility = View.VISIBLE
                R.id.congratulationsFragment -> binding.bottomNav.visibility = View.VISIBLE
                R.id.workoutDetailFragment -> binding.bottomNav.visibility = View.GONE
            }

        }
    }
}