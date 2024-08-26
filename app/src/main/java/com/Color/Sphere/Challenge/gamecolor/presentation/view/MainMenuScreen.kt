package com.Color.Sphere.Challenge.gamecolor.presentation.view
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.Color.Sphere.Challenge.gamecolor.R

@Composable
fun MainMenuScreen(onStartClicked:()-> Unit,onPolicyClicked: () -> Unit,onOptionClicked:()->Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB6C1)) // Используйте нужный цвет или фон
    ) {
        // Фоновое изображение
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
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Кнопка "Start"
            Box(
                modifier = Modifier

                    .padding(vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.welcome_rec), // Замените на ваш ресурс для кнопки
                    contentDescription = null,

                    modifier = Modifier.fillMaxWidth()
                        .clickable { onStartClicked() }
                )
                Image(
                    painter = painterResource(id = R.drawable.start),  // Используем policy.xml
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }

            // Кнопка "Options"
            Box(
                modifier = Modifier

                    .padding(vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.welcome_rec), // Замените на ваш ресурс для кнопки
                    contentDescription = null,

                    modifier = Modifier.fillMaxWidth()
                        .clickable { onOptionClicked() }
                )
                Image(
                    painter = painterResource(id = R.drawable.options),  // Используем policy.xml
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }

            // Кнопка "Policy"
            Box(
                modifier = Modifier


                    .padding(vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.police_rec), // Замените на ваш ресурс для кнопки
                    contentDescription = null,

                    modifier = Modifier.fillMaxWidth()
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
}