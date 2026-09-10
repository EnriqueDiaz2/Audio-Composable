package figueroa.enrique.audiocomposable.ui.views

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text

@Composable
fun BypassButton(
    activo: Boolean,
    onCambiar: (Boolean) -> Unit,
    onCambioFin: () -> Unit,
    modifier: Modifier = Modifier,
    accentColor: Color,
    backgroundColor: Color,
    borderColor: Color,
    textPrimary: Color,
    textSecondary: Color
) {
    // Siempre apunta al valor más reciente de "activo", aunque el pointerInput no se recree
    val activoActual by rememberUpdatedState(activo)

    Column(
        modifier = modifier,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {
        Canvas(
            modifier = Modifier
                .size(100.dp)
                .pointerInput(Unit) {
                    detectTapGestures {
                        onCambiar(!activoActual)
                        onCambioFin()
                    }
                }
        ) {
            val radius = size.minDimension * 0.40f

            drawCircle(
                color = backgroundColor,
                radius = radius,
                center = Offset(size.width / 2f, size.height / 2f)
            )
            drawCircle(
                color = if (activo) accentColor else borderColor,
                radius = radius,
                center = Offset(size.width / 2f, size.height / 2f),
                style = Stroke(width = 3.dp.toPx())
            )
            drawLine(
                color = if (activo) accentColor else textSecondary,
                start = Offset(size.width / 2f, size.height * 0.25f),
                end = Offset(size.width / 2f, size.height * 0.50f),
                strokeWidth = 5.dp.toPx(),
                cap = StrokeCap.Round
            )
            drawArc(
                color = if (activo) accentColor else textSecondary,
                startAngle = -50f,
                sweepAngle = 280f,
                useCenter = false,
                topLeft = Offset(size.width * 0.25f, size.height * 0.25f),
                size = androidx.compose.ui.geometry.Size(size.width * 0.50f, size.height * 0.50f),
                style = Stroke(width = 5.dp.toPx())
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = if (activo) "Sí" else "No",
            color = textSecondary,
            fontSize = 12.sp
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = "Bipass",
            color = textPrimary,
            fontSize = 14.sp
        )
    }
}