package com.example.myflexiblefragmentcompose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.myflexiblefragmentcompose.ui.AppShell
import com.example.myflexiblefragmentcompose.ui.theme.MyFlexibleFragmentComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MyFlexibleFragmentComposeTheme {
                AppShell(onNavigateProfile = ::navigateToProfile)
            }
        }
    }

    private fun navigateToProfile() {
        startActivity(Intent(this, ProfileActivity::class.java))
    }
}
