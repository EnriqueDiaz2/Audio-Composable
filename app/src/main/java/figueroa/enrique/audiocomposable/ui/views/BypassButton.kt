package figueroa.enrique.audiocomposable.ui.views

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.min

@Composable
fun BypassButton(
    activo: Boolean,
    onCambiar: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    accentColor: Color,
    backgroundColor: Color,
    borderColor: Color,
    textPrimary: Color,
    textSecondary: Color
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .pointerInput(Unit) {
                    detectTapGestures {
                        onCambiar(!activo)
                    }
                }
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val center = Offset(size.width / 2f, size.height / 2f)
                val radius = min(size.width, size.height) / 2f

                // Círculo relleno: color de acento si está activo, fondo normal si no
                drawCircle(
                    color = if (activo) accentColor else backgroundColor,
                    center = center,
                    radius = radius
                )

                // Borde del círculo
                drawCircle(
                    color = borderColor,
                    center = center,
                    radius = radius,
                    style = Stroke(width = 2.dp.toPx())
                )
            }
        }

        Spacer(modifier = Modifier.height(6.dp))
        Text(text = if (activo) "Sí" else "No", color = textSecondary, fontSize = 12.sp)
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = "Bypass", color = textPrimary, fontSize = 14.sp)
    }
}