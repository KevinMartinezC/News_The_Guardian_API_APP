package com.example.news

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.example.news.components.MainScreen
import com.example.news.model.testSearchArticles
import com.example.news.ui.theme.NewsTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        lifecycleScope.launch {
            testSearchArticles()
        }
        setContent {
            NewsTheme {
                MainScreen()
            }
        }
    }
}

