package com.example.rvbnb.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rvbnb.R

class RVOwnerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rvowner)
        title = "RV Owner"
    }
}
