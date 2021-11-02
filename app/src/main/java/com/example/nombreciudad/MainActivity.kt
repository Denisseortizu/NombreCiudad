package com.example.nombreciudad

import android.Manifest
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.location.Geocoder;
import android.location.Location;
import java.util.*
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class MainActivity : AppCompatActivity() {

    lateinit var mFusedLocationClient: FusedLocationProviderClient

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var lat : Double = 19.24523420000002
        var lng : Double = -103.7240868
        val geoCoder = Geocoder(this, Locale.getDefault())
        val textv = findViewById<TextView>(R.id.textv)

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mFusedLocationClient.lastLocation.addOnCompleteListener(this) { task ->
            var location: Location? = task.result

            Log.d("Mensaje", "Aver esto ${location.toString()}")
            if (location != null) {
                    lat  = location.latitude
                    lng  = location.longitude
                Log.d("Latitude ", "$lat")
                Log.d("Longitude ", "$lng")
            }
            val  address = (geoCoder.getFromLocation(lat, lng, 1))
            Log.d("Lista de completa", "Lista Completa --- $address")
            val listaAd = address[0].locality
            Log.d("Lista de dirección", "1 --- $listaAd")
            textv.text = listaAd.toString()

        }
    }
}