package com.utynote.components.map

import android.content.Context
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.utynote.R

class MapFragment : Fragment(), OnMapReadyCallback {

    private var map: GoogleMap? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, bundle: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.map_container_view, container, false)

        (view.findViewById(R.id.fab) as FloatingActionButton)
                .setOnClickListener { v -> Snackbar
                    .make(v, "Searching your location..", Snackbar.LENGTH_SHORT)
                    .setAction("Action", null)
                    .show()
        }
        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        val mapFragment = com.google.android.gms.maps.SupportMapFragment()
        mapFragment.getMapAsync(this)
        childFragmentManager
                .beginTransaction()
                .add(R.id.map_container, mapFragment)
                .commit()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        val berlin = LatLng(52.53174, 13.38771)
        googleMap.addMarker(MarkerOptions().position(berlin).title("Marker"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(berlin, 18f))
    }

    companion object {
        val TAG = MapFragment::class.java.simpleName
    }
}
