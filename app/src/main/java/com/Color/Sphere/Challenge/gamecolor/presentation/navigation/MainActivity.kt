package com.Color.Sphere.Challenge.gamecolor.presentation.navigation

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.Color.Sphere.Challenge.gamecolor.data.Prefs
import com.Color.Sphere.Challenge.gamecolor.data.SoundManager

import com.Color.Sphere.Challenge.gamecolor.ui.theme.ColorSphereChallengeTheme



class MainActivity : ComponentActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE)
        Prefs.init(applicationContext)
        SoundManager.init(applicationContext)

        setContent {
            val navHostController = rememberNavController()
            ColorSphereChallengeTheme {
                NavigationScreen(navHostController)
            }
        }
        SoundManager.playSound()
        SoundManager.playMusic()
        SoundManager.pauseMusic()
    }
    override fun onResume() {
        super.onResume()
        SoundManager.resumeSound()
    }

    override fun onPause() {
        super.onPause()
        SoundManager.pauseSound()
        SoundManager.pauseMusic()
    }

    override fun onDestroy() {
        super.onDestroy()
        SoundManager.onDestroy()
    }
}
