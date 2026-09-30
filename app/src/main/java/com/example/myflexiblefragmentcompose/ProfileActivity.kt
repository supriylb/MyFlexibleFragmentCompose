package com.example.myflexiblefragmentcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.myflexiblefragmentcompose.ui.screens.ProfileScreen
import com.example.myflexiblefragmentcompose.ui.theme.MyFlexibleFragmentComposeTheme

class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MyFlexibleFragmentComposeTheme {
                ProfileScreen(onBack = ::finish)
            }
        }
    }
}
