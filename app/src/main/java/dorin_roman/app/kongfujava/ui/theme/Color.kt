package dorin_roman.app.kongfujava.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val purpleColor =  Color(0xff7d65f1)
val primaryColor =  Color(0xff9ff07a)
val primaryLightColor =  Color(0xFF8CFF9C)
val primaryDarkColor =  Color(0xFF006D21)
val primaryTextColor =  Color(0xFF000000)
val secondaryColor =  Color(0xFF8E24AA)
val secondaryLightColor =  Color(0xFFC158DC)
val secondaryDarkColor =  Color(0xFF5C007A)
val secondaryTextColor =  Color(0xFFFFFFFF)

val LightGray = Color(0xFFFCFCFC)
val MediumGray = Color(0xFF9C9C9C)
val DarkGray = Color(0xFF141414)

val Colors.splashScreenBackground: Color
    @Composable
    get() = if (isLight) primaryDarkColor else Color.Black