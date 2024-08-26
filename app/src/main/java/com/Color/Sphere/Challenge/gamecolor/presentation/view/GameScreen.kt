package com.Color.Sphere.Challenge.gamecolor.presentation.view


// Функция генерации позиций правильных кругов


import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Color.Sphere.Challenge.gamecolor.R
import com.Color.Sphere.Challenge.gamecolor.data.Levels
import kotlinx.coroutines.delay

@Composable
fun GameScreen(level: Levels, onLevelSelect: () -> Unit,onLevelClick: () -> Unit) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("LevelPreferences", Context.MODE_PRIVATE)
    val levelManager = LevelManager(sharedPreferences)

    var score by remember { mutableStateOf(0) }
    val targetScore = level.T * 10 // Устанавливаем целевой счет, например, 10 очков за каждый правильный круг
    var timeRemaining by remember { mutableStateOf(level.time ) } // начальное время в секундах
    var circles by remember { mutableStateOf(generateCirclesGrid(level.N, level.T)) }
    var levelCompleted by remember { mutableStateOf(false) }
    val minutes = timeRemaining / 60
    val seconds = timeRemaining % 60

    // Таймер для отсчета времени
    LaunchedEffect(timeRemaining) {
        while (timeRemaining > 0 && score < targetScore && !levelCompleted) {
            delay(1000L)
            timeRemaining -= 1
        }
        if (timeRemaining <= 0 || score >= targetScore) {
            levelCompleted = true
            if (score >= targetScore) {
                levelManager.unlockNextLevel(level.ordinal + 1)
            }
        }
    }

    if (levelCompleted) {
        // Определяем, выиграл ли игрок
        val isWin = score >= targetScore
        GameEndScreen(
            isWin = isWin,
            score = score,
            targetScore = targetScore,
            timeRemaining = timeRemaining,
            onLevelSelect = onLevelSelect
        )
    } else {
        // Отображение игрового экрана
        Box(modifier = Modifier.fillMaxSize()) {
            // Установка фонового изображения
            Image(
                painter = painterResource(id = R.drawable.background), // Замените на ваш ресурс фонового изображения
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                // Верхняя панель с информацией об уровне, очках и времени
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Кнопка уровня
                    Button(
                        onClick = { /* Действие нажатия на кнопку уровня */ },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFFFF99FF),
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .size(100.dp, 40.dp)
                            .shadow(5.dp)
                    ) {
                        Text(
                            text = "Level ${level.ordinal + 1}",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    // Кнопка меню (иконка)
                    IconButton(
                        onClick = { onLevelClick() },
                        modifier = Modifier.size(48.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_menu), // Замените на ваш ресурс фонового изображения
                            contentDescription = "Menu",

                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Панель со счетом и временем
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Счет
                    Column(
                        horizontalAlignment = Alignment.Start
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.score),  // Используем policy.xml
                            contentDescription = null,

                        )
                        Text(
                            text = "%02d/%d".format(score, targetScore),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    // Время
                    Column(
                        horizontalAlignment = Alignment.End
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.time),  // Используем policy.xml
                            contentDescription = null,

                            )
                        Text(
                            text = "%02d:%02d".format(minutes, seconds),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Генерация уровня
                GenerateLevel(
                    circles = circles,
                    onCircleClick = { isCorrect, i, j ->
                        if (isCorrect) {
                            score += 1 // Например, 10 очков за правильный круг
                            circles = circles.toMutableList().apply {
                                val row = this[i].toMutableList()
                                row[j] = null
                                this[i] = row
                            }

                            // Проверяем, нажаты ли все правильные круги
                            if (circles.flatten().filterNotNull().all { !it.isCorrect }) {
                                timeRemaining += level.plusTime // Добавляем время при завершении уровня
                                circles = generateCirclesGrid(level.N, level.T) // Генерируем новый уровень
                            }
                        } else {
                            timeRemaining -= level.outTime // Отнимаем время при неправильном клике
                        }
                    }
                )
            }
        }
    }
}


@Composable
fun GenerateLevel(
    circles: List<List<Circle?>>, // Двумерный массив кругов
    onCircleClick: (Boolean, Int, Int) -> Unit // Функция обратного вызова при клике на круг (true - правильный круг, false - неправильный)
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Создаем квадрат с кругами в зависимости от N x N
        circles.forEachIndexed { i, row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                row.forEachIndexed { j, circle ->
                    if (circle != null) {
                        CircleButton(circle.isCorrect) {
                            onCircleClick(circle.isCorrect, i, j)
                        }
                    } else {
                        Spacer(modifier = Modifier.size(75.dp).padding(1.dp))
                    }
                }
            }
        }
    }
}

// Функция генерации двумерного массива кругов
fun generateCirclesGrid(N: Int, T: Int): List<List<Circle?>> {
    val correctCircles = mutableSetOf<Pair<Int, Int>>()
    while (correctCircles.size < T) {
        val i = (0 until N).random()
        val j = (0 until N).random()
        correctCircles.add(Pair(i, j))
    }

    return List(N) { i ->
        List(N) { j ->
            if (correctCircles.contains(Pair(i, j))) {
                Circle(true)
            } else {
                Circle(false)
            }
        }
    }
}

data class Circle(val isCorrect: Boolean)

@Composable
fun CircleButton(isCorrect: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(1.dp)
            .size(75.dp), // Размер круга
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (isCorrect) Color(0xFFFF99FF) else Color(0xFFDD82EE)
        )
    ) {}
}


