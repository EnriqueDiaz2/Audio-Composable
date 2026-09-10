package figueroa.enrique.audiocomposable.ui.views

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

@Composable
fun BassBoostKnob(
    label: String,
    value: Float,                        // 0f..1f, ahora viene de afuera
    onValueChange: (Float) -> Unit,
    onValueChangeFinished: () -> Unit,       // avisa al padre cuando cambia
    valueText: (Float) -> String,         // formatea el valor como texto (ej. { "${(it*100).toInt()}" })
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
                .size(140.dp)
                .pointerInput(Unit) {
                    detectDragGestures { change, _ ->
                        val centerX = size.width / 2f
                        val centerY = size.height / 2f
                        val dx = change.position.x - centerX
                        val dy = change.position.y - centerY

                        var angle = Math.toDegrees(atan2(dy.toDouble(), dx.toDouble())).toFloat()
                        if (angle < 0) angle += 360f

                        val normalizedAngle = when {
                            angle >= 135f -> angle - 135f
                            else -> angle + 225f
                        }

                        val nuevoValor = (normalizedAngle / 270f).coerceIn(0f, 1f)
                        onValueChange(nuevoValor)
                        change.consume()

                        onValueChangeFinished()
                    }
                }
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val center = Offset(size.width / 2f, size.height / 2f)
                val radius = min(size.width, size.height) * 0.32f

                drawCircle(color = backgroundColor, center = center, radius = radius * 1.20f)
                drawCircle(
                    color = borderColor, center = center, radius = radius * 1.20f,
                    style = Stroke(width = 1.dp.toPx())
                )

                val tickCount = 40
                repeat(tickCount + 1) { index ->
                    val progress = index / tickCount.toFloat()
                    val angle = Math.toRadians(135.0 + 270.0 * progress)
                    val innerRadius = radius * 1.10f
                    val outerRadius = radius * 1.25f

                    val start = Offset(
                        center.x + cos(angle).toFloat() * innerRadius,
                        center.y + sin(angle).toFloat() * innerRadius
                    )
                    val end = Offset(
                        center.x + cos(angle).toFloat() * outerRadius,
                        center.y + sin(angle).toFloat() * outerRadius
                    )

                    drawLine(
                        color = if (progress <= value) accentColor else borderColor,
                        start = start, end = end,
                        strokeWidth = 2.5.dp.toPx(),
                        cap = StrokeCap.Round
                    )
                }

                drawArc(
                    color = borderColor,
                    startAngle = 135f, sweepAngle = 270f, useCenter = false,
                    topLeft = Offset(center.x - radius, center.y - radius),
                    size = Size(radius * 2f, radius * 2f),
                    style = Stroke(width = 3.dp.toPx())
                )

                drawArc(
                    color = accentColor,
                    startAngle = 135f, sweepAngle = 270f * value, useCenter = false,
                    topLeft = Offset(center.x - radius * 0.78f, center.y - radius * 0.78f),
                    size = Size(radius * 1.56f, radius * 1.56f),
                    style = Stroke(width = 3.dp.toPx())
                )

                val indicatorAngle = Math.toRadians(135.0 + 270.0 * value)
                val startRadius = radius * 0.25f
                val endRadius = radius * 0.70f

                val start = Offset(
                    center.x + cos(indicatorAngle).toFloat() * startRadius,
                    center.y + sin(indicatorAngle).toFloat() * startRadius
                )
                val end = Offset(
                    center.x + cos(indicatorAngle).toFloat() * endRadius,
                    center.y + sin(indicatorAngle).toFloat() * endRadius
                )

                drawLine(
                    color = accentColor, start = start, end = end,
                    strokeWidth = 4.dp.toPx(), cap = StrokeCap.Round
                )
            }
        }

        Text(text = valueText(value), color = textSecondary, fontSize = 12.sp)
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = label, color = textPrimary, fontSize = 14.sp)
    }
}