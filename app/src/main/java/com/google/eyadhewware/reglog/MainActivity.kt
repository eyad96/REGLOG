package com.google.eyadhewware.reglog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var mAuth :FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


          onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    // in here you can do logic when backPress is clicked
                }
            })
        }

    }
