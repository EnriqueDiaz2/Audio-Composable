package figueroa.enrique.audiocomposable.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColors = darkColorScheme(

    primary = DarkPrimary,
    onPrimary = DarkTextPrimary,

    secondary = DarkTextSecondary,
    onSecondary = DarkTextPrimary,

    background = DarkBackground,
    onBackground = DarkTextPrimary,

    surface = DarkSpectrumBackground,
    onSurface = DarkTextPrimary,

    surfaceVariant = DarkKnobBackground,
    onSurfaceVariant = DarkTextSecondary,

    outline = DarkKnobBorder,

    error = DarkAccentRed,
    onError = DarkTextPrimary
)


private val LightColors = lightColorScheme(

    primary = LightPrimary,
    onPrimary = LightTextPrimary,

    secondary = LightTextSecondary,
    onSecondary = LightTextPrimary,

    background = LightBackground,
    onBackground = LightTextPrimary,

    surface = LightSpectrumBackground,
    onSurface = LightTextPrimary,

    surfaceVariant = LightKnobBackground,
    onSurfaceVariant = LightTextSecondary,

    outline = LightKnobBorder,

    error = LightAccentRed,
    onError = LightTextPrimary
)

@Composable
fun AudioComposableTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        content = content
    )
}