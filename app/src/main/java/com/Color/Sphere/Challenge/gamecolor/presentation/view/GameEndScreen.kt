package com.Color.Sphere.Challenge.gamecolor.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Color.Sphere.Challenge.gamecolor.R

@Composable
fun GameEndScreen(
    isWin: Boolean,
    score: Int,
    targetScore: Int,
    timeRemaining: Int,
    onLevelSelect: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.background), // Замените на ваше фоновое изображение
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)
        ) {
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .weight(1f)
                ,  // Настроенная высота
                contentAlignment = Alignment.Center
            ) {
                // Фон для всего контента
                Image(
                    painter = painterResource(id = R.drawable.main_rec),  // Используем main_rec.xml
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxWidth()
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier.padding(top = 0.dp, start = 0.dp, end = 0.dp, bottom = 0.dp)
                    // Отступ сверху для корректного расположения
                ) {
                    // Box для текста "Welcome"
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.welcome_rec),  // Используем welcome_rec.xml
                            contentDescription = null,
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .fillMaxWidth()
                                .offset(y = (-50).dp) // Shift the image upwards by 50dp
                        )

                        Image(
                            painter = painterResource(id = if (isWin) R.drawable.you_win else R.drawable.you_lose),  // Используем welcome.xml
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .offset(y = (-50).dp) // Shift the image upwards by 50dp
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Текст "Score"
                    Image(
                        painter = painterResource(id = R.drawable.score),  // Используем policy.xml
                        contentDescription = null,

                        )
                    Text(
                        text = "%02d/%d".format(score, targetScore),
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Текст "Time"
                    Image(
                        painter = painterResource(id = R.drawable.time),  // Используем policy.xml
                        contentDescription = null,

                        )
                    val minutes = timeRemaining / 60
                    val seconds = timeRemaining % 60
                    Text(
                        text = "%02d:%02d".format(minutes, seconds),
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )


                }
            }


            Spacer(modifier = Modifier.height(32.dp))

            Box(
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ok_rec),  // Используем ok_rec.xml
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clickable { onLevelSelect() }
                )

                Image(
                    painter = painterResource(id = R.drawable.levels),  // Используем ok.xml
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.Center)

                )
            }
        }
    }
}

