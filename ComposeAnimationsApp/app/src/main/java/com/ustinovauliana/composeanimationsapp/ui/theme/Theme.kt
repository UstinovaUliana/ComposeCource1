package com.ustinovauliana.composeanimationsapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext


@Composable
fun ComposeAnimationsAppTheme(
    content: @Composable () -> Unit
) {
    val colors = lightColorScheme(
        primary = Melon,
        primaryContainer = PaleDogwood,
        onPrimary = Color.Black,
        secondary = Peach,
        onSecondary = Color.Black
    )
    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}
