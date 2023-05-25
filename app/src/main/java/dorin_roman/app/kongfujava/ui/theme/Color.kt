package dorin_roman.app.kongfujava.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

//Dark
val primaryDark: Color = Color(0xff7d65f1)
val primaryVariantDark: Color = Color(0xff6955bc)
val secondaryDark: Color = Color(0xff9ff07a)
val secondaryVariantDark: Color = Color(0xff6cb451)
val backgroundDark: Color = Color(0xFF000000)
val surfaceDark: Color = Color(0xFF000000)
val onPrimaryDark: Color = Color(0xFFFFFFFF)
val onSecondaryDark: Color = Color(0xFF000000)
val onBackgroundDark: Color = Color(0xFFFFFFFF)
val onSurfaceDark: Color = Color(0xFFFFFFFF)

//Light
val primaryLight: Color = Color(0xFFAA9CEB)
val primaryVariantLight: Color = Color(0xff6955bc)
val secondaryLight: Color = Color(0xff9ff07a)
val secondaryVariantLight: Color = Color(0xff6cb451)
val backgroundLight: Color = Color(0xFFFFFFFF)
val surfaceLight: Color = Color(0xFFFFFFFF)
val onPrimaryLight: Color = Color(0xFF000000)
val onSecondaryLight: Color = Color(0xFFFFFFFF)
val onBackgroundLight: Color = Color(0xFF000000)
val onSurfaceLight: Color = Color(0xFF000000)

val Colors.textYellow: Color
    @Composable
    get() = if (isLight) Color(0xFF009595) else Color(0xFFFEEA3B)

val Colors.textOrange: Color
    @Composable
    get() = if (isLight) Color(0xFFD46F1A) else Color(0xFFCB7832)

val Colors.textPurple: Color
    @Composable
    get() = if (isLight) Color(0xFF980F88) else Color(0xFF9B6FB2)

val Colors.textBlue: Color
    @Composable
    get() = if (isLight) Color(0xFF185CEF) else Color(0xFF5995B9)

val Colors.textGray: Color
    @Composable
    get() = if (isLight) Color(0xFF6C7279) else Color(0xFF6C7279)

val Colors.Star: Color
    @Composable
    get() = if (isLight) Color(0xFFFFCA2B) else Color(0xFFFFCA2B)

val Colors.TextFieldCursor: Color
    @Composable
    get() = if (isLight) Color(0xFFC3B8F8) else Color(0xFFC3B8F8)

val Colors.MediumGray: Color
    @Composable
    get() = Color(0xFF9C9C9C)

val Colors.Error: Color
    @Composable
    get() = if (isLight) Color(0xFFA84552) else Color(0xFFA84552)

val Colors.LevelItemText: Color
    @Composable
    get() = if (isLight) Color.White else Color.White

val Colors.videoIcon: Color
    @Composable
    get() = if (isLight) Color.LightGray else Color.LightGray

val Colors.systemUi: Color
    @Composable
    get() = if (isLight) secondaryLight else Color.Black

val Colors.onTopBar: Color
    @Composable
    get() = if (isLight) Color.Black else Color.Black