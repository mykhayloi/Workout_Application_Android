package com.example.workout_application_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.workout_application_android.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val bodyArt = LatLng(45.81361938283895, 15.962442163225612)
        val sportingGym = LatLng(45.81619173527595, 15.985444787737944)
        val odlandoFit = LatLng(45.80591537760846, 15.984950246039705)
        val formaGym = LatLng(45.80997002786676, 15.97308516859699)
        val oneOnOne = LatLng(45.80365787411791, 15.976003412005278)
        val dkMetalac = LatLng(45.8094315782019, 15.951627496477272)
        val playFiness = LatLng(45.81140586819169, 15.939267877336318)
        val quantum = LatLng(45.80679908265416, 15.940898660417322)
        val gTeam = LatLng(45.79734500495595, 15.940726999040203)
        val forAll = LatLng(45.816012272533854, 15.930770639176657)
        val basicGym = LatLng(45.779449670079046, 15.970939401384763)



        mMap.addMarker(MarkerOptions().position(bodyArt).title("Body Art"))
        mMap.addMarker(MarkerOptions().position(sportingGym).title("Sporting Gym"))
        mMap.addMarker(MarkerOptions().position(odlandoFit).title("Orlando Fit"))
        mMap.addMarker(MarkerOptions().position(formaGym).title("Forma Gym"))
        mMap.addMarker(MarkerOptions().position(oneOnOne).title("One on One"))
        mMap.addMarker(MarkerOptions().position(dkMetalac).title("DK Metalac"))
        mMap.addMarker(MarkerOptions().position(playFiness).title("Play Fitness"))
        mMap.addMarker(MarkerOptions().position(quantum).title("Quantum Gym"))
        mMap.addMarker(MarkerOptions().position(gTeam).title("G team"))
        mMap.addMarker(MarkerOptions().position(forAll).title("For All Gym"))
        mMap.addMarker(MarkerOptions().position(basicGym).title("Basic Gym One"))


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bodyArt, 15f))
    }
}