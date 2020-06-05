package com.demos.maps.demos

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.view.KeyEvent
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.demos.maps.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.io.IOException

class GeocodingDemoActivity : AppCompatActivity() {

    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_geocoding_demo)

        (supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment).getMapAsync {
            map = it
        }

        findViewById<EditText>(R.id.query)?.let { editText ->
            editText.setOnKeyListener { _, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                    map.clear()
                    moveToAddress(editText.text.toString())
                    editText.text.clear()
                    return@setOnKeyListener true
                }
                false
            }
        }
    }

    private fun moveToAddress(address: String) {
        if (!Geocoder.isPresent()) {
            toast(R.string.no_geocoder)
            return
        }
        try {
            Geocoder(this).run {
                getFromLocationName(address, 1)
                        .getOrNull(0)
                        ?.setMarker()
                        ?.centerCamera()
            }
        } catch (e: IOException) {
            e.printStackTrace()
            toast(R.string.error)
        }
    }

    private fun Address.setMarker(): Address = apply {
        val position = LatLng(latitude, longitude)
        val marker = MarkerOptions().position(position)
        map.addMarker(marker)
    }

    private fun Address.centerCamera(): Address = apply {
        val position = LatLng(latitude, longitude)
        val cameraPosition = CameraUpdateFactory.newLatLng(position)
        map.moveCamera(cameraPosition)
    }

    private fun toast(@StringRes id: Int) {
        Toast.makeText(this, id, Toast.LENGTH_LONG).show()
    }
}
