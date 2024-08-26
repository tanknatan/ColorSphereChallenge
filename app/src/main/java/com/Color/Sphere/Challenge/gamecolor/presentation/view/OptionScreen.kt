package com.Color.Sphere.Challenge.gamecolor.presentation.view

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.Color.Sphere.Challenge.gamecolor.R
import com.Color.Sphere.Challenge.gamecolor.data.Prefs
import com.Color.Sphere.Challenge.gamecolor.data.SoundManager

@Composable
fun OptionsScreen(onBackClicked: () -> Unit) {
    val context = LocalContext.current
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("settings", Context.MODE_PRIVATE)

    var musicVolume by remember { mutableFloatStateOf(Prefs.music) }
    var soundVolume by remember { mutableFloatStateOf(Prefs.sound) }

    // Обновление значений громкости при изменении
    LaunchedEffect(musicVolume) {
        Prefs.music = musicVolume
        SoundManager.setVolumeMusic()
    }

    LaunchedEffect(soundVolume) {
        Prefs.sound = soundVolume
        SoundManager.setVolumeSound()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB6C1)) // Используйте нужный цвет или фон
    ) {
        Image(
            painter = painterResource(id = R.drawable.background), // Замените на ваше фоновое изображение
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, top = 100.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Box с фоном main_rec для всего экрана опций
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 0.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.main_rec), // Замените на ваше изображение main_rec
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxWidth()
                )

                Column(
                    modifier = Modifier
                        .padding(0.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Заголовок "Options"
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                            .offset(y = -56.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.rec_p), // Фон для заголовка
                            contentDescription = null,
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Image(
                            painter = painterResource(id = R.drawable.options),  // Используем welcome.xml
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.Center)
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.music),  // Используем welcome.xml
                            contentDescription = null,
                            modifier = Modifier

                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Slider(
                            value = musicVolume,
                            onValueChange = { newValue ->
                                musicVolume = newValue
                                sharedPreferences.edit().putFloat("musicVolume", newValue).apply()
                            },
                            colors = SliderDefaults.colors(
                                thumbColor = Color(0xFF00FFFF), // Цвет ползунка
                                activeTrackColor = Color(0xFF00FFFF), // Цвет активного трека
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )

                    }

// Sound Volume
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.sound),  // Используем welcome.xml
                            contentDescription = null,
                            modifier = Modifier

                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Slider(
                            value = soundVolume,
                            onValueChange = { newValue ->
                                soundVolume = newValue
                                sharedPreferences.edit().putFloat("soundVolume", newValue).apply()
                            },
                            colors = SliderDefaults.colors(
                                thumbColor = Color(0xFFFFA07A),
                                activeTrackColor = Color(0xFFFFA07A)
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )

                    }


                    Spacer(modifier = Modifier.height(16.dp))

                    // Exit Game Button
                }
            }

            // Spacer, чтобы "Back" был снизу
            Spacer(modifier = Modifier.weight(1f))

            // Back Button
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


