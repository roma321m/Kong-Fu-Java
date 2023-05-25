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


//color palate
val purpleColor1 = Color(0xFFC3B8F8)
val purpleColor2 = Color(0xff6955bc)
val greenColor1 = Color(0xff9cf37c)
val greenColor2 = Color(0xff6d9b58)
val greenColor3 = Color(0xff5d9a3c)
val greenColor4 = Color(0xff6cb451)
val greenColor5 = Color(0xffccfcb4)
val kongFuRed = Color(0xffa84552)
val kongFuYellow = Color(0xFFFFCA2B)


val LightGray = Color(0xFFFCFCFC)
val MediumGray = Color(0xFF9C9C9C)
val DarkGray = Color(0xFF141414)

val Colors.Star: Color
    @Composable
    get() = Color(0xFFFFCA2B)

val Colors.TextFieldCursor: Color
    @Composable
    get() = Color(0xFFC3B8F8)

val Colors.MediumGray: Color
    @Composable
    get() = Color(0xFF9C9C9C)

val Colors.Error: Color
    @Composable
    get() = Color(0xffa84552)

val Colors.LevelItemText: Color
    @Composable
    get() = Color.White

val Colors.systemUi: Color
    @Composable
    get() = if (isLight) secondaryLight else Color.Black

val Colors.onTopBar: Color
    @Composable
    get() = Color.Black