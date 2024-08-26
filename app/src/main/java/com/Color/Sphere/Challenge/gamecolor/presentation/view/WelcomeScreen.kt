package com.Color.Sphere.Challenge.gamecolor.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Color.Sphere.Challenge.gamecolor.R


@Composable
fun WelcomeScreen(onOkClicked: () -> Unit, onPolicyClicked: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Фоновое изображение
        Image(
            painter = painterResource(id = R.drawable.background),  // Убедитесь, что у вас есть background в ресурсах
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Контент поверх изображения
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Box для основного контента
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
                                .offset(y = (-14).dp) // Shift the image upwards by 50dp
                        )

                        Image(
                            painter = painterResource(id = R.drawable.welcome),  // Используем welcome.xml
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.Center)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Текст "Click 'Ok' to accept our Privacy Policy."
                    Image(
                        painter = painterResource(id = R.drawable.welcome_text),  // Используем изображение с текстом и тенью
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(vertical = 16.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Кнопка Policy
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.police_rec),  // Используем police_rec.xml
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .clickable { onPolicyClicked() }
                        )

                        Image(
                            painter = painterResource(id = R.drawable.policy),  // Используем policy.xml
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.Center)

                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(49.dp))

            // Кнопка Ok
            Box(
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ok_rec),  // Используем ok_rec.xml
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clickable { onOkClicked() }
                )

                Image(
                    painter = painterResource(id = R.drawable.ok),  // Используем ok.xml
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.Center)

                )
            }
        }
    }
}






