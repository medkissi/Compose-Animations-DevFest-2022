package com.medkissi.composeanimationsdevfest2022.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.medkissi.composeanimationsdevfest2022.ui.DevFestApp
import com.medkissi.composeanimationsdevfest2022.ui.theme.ComposeAnimationsDevFest2022Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAnimationsDevFest2022Theme {
             DevFestApp()

            }
        }
    }
}



