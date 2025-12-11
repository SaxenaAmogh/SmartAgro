package com.example.smartagro.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.smartagro.data.dataClasses.KisanData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class KisanViewModel : ViewModel() {

    // Use a StateFlow to hold the data and make it observable by Compose
    private val _kisanData = MutableStateFlow(KisanData())
    val kisanData: StateFlow<KisanData> = _kisanData

    private val database = FirebaseDatabase.getInstance()
    // This is the root reference for all the data you want
    private val kisanRef = database.getReference("Kisan1")

    init {
        listenForDataChanges()
    }

    private fun listenForDataChanges() {
        kisanRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // Try to map the entire 'Kisan1' node to your KisanData model
                val data = snapshot.getValue(KisanData::class.java)

                if (data != null) {
                    _kisanData.value = data
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error (e.g., permission denied, network issues)
                Log.e("Firebase", "Failed to read value.", error.toException())
            }
        })
    }
}