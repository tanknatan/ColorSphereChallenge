package com.Color.Sphere.Challenge.gamecolor.presentation.view

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Color.Sphere.Challenge.gamecolor.R
import com.Color.Sphere.Challenge.gamecolor.data.SoundManager

@Composable
fun LevelScreen(onLevelSelect: (Int) -> Unit, onBackClicked: () -> Unit) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("LevelPreferences", Context.MODE_PRIVATE)

    // Используем LevelManager с sharedPreferences
    val levelManager = remember { LevelManager(sharedPreferences) }

    Box {
        Image(
            painter = painterResource(id = R.drawable.background), // Замените на ваше фоновое изображение
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            (1..7).forEach { level ->
                val isUnlocked = levelManager.isLevelUnlocked(level)

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 0.dp)
                        .clickable(enabled = isUnlocked) {
                            if (isUnlocked) {
                                SoundManager.resumeMusic()
                                SoundManager.pauseSound()
                                onLevelSelect(level)
                            }
                        }
                ) {
                    val backgroundImage = if (isUnlocked) {
                        painterResource(id = R.drawable.rec_act)
                    } else {
                        painterResource(id = R.drawable.rec_no_act)
                    }

                    Image(
                        painter = backgroundImage,
                        contentDescription = "Level $level",
                        modifier = Modifier.fillMaxWidth()
                    )

                    Text(
                        text = "Level $level",
                        fontSize = 18.sp,
                        color = if (isUnlocked) Color.White else Color.Gray,
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ok_rec), // Фон для кнопки
                    contentDescription = null,

                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(onClick = onBackClicked)
                )
                Image(
                    painter = painterResource(id = R.drawable.back),  // Используем policy.xml
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }

    }
}



class LevelManager(private val sharedPreferences: SharedPreferences) {

    companion object {
        private const val UNLOCKED_LEVELS_KEY = "unlockedLevels"
    }

    fun getUnlockedLevels(): Int {
        return sharedPreferences.getInt(
            UNLOCKED_LEVELS_KEY,
            1
        ) // По умолчанию разблокирован только 1 уровень
    }

    fun unlockNextLevel(levelCompleted: Int) {
        val currentUnlockedLevels = getUnlockedLevels()
        if (levelCompleted >= currentUnlockedLevels) {
            sharedPreferences.edit().putInt(UNLOCKED_LEVELS_KEY, levelCompleted + 1).apply()
        }
    }

    fun isLevelUnlocked(level: Int): Boolean {
        return level <= getUnlockedLevels()
    }
}
