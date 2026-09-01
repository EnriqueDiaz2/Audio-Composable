package figueroa.enrique.audiocomposable.ui.views

import android.graphics.Paint
import android.graphics.Typeface
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun VuMeter(
    valorDb: Float, // rango esperado: -20f a 3f
    modifier: Modifier = Modifier
) {
    val valorAnimado by animateFloatAsState(
        targetValue = valorDb.coerceIn(-20f, 3f),
        animationSpec = tween(durationMillis = 150),
        label = "aguja"
    )

    Canvas(
        modifier = modifier
            .aspectRatio(1.1f) // un poco más ancho que alto
            .background(Color.Black)
    ) {
        val anchoTotal = size.width
        val altoTotal = size.height

        drawRoundRect(
            color = Color.White,
            topLeft = Offset(anchoTotal * 0.04f, altoTotal * 0.06f),
            size = Size(anchoTotal * 0.92f, altoTotal * 0.88f),
            cornerRadius = CornerRadius(24f, 24f)
        )

        val pivote = Offset(anchoTotal * 0.5f, altoTotal * 0.85f)
        val radioArco = anchoTotal * 0.42f

        val anguloInicio = 200f
        val anguloFin = 340f
        val anguloCero = anguloInicio + (anguloFin - anguloInicio) * (0f - (-20f)) / (3f - (-20f))

        drawArc(
            color = Color.Black,
            startAngle = anguloInicio,
            sweepAngle = anguloCero - anguloInicio,
            useCenter = false,
            style = Stroke(width = 12f),
            topLeft = Offset(pivote.x - radioArco, pivote.y - radioArco),
            size = Size(radioArco * 2, radioArco * 2)
        )
        drawArc(
            color = Color.Red,
            startAngle = anguloCero,
            sweepAngle = anguloFin - anguloCero,
            useCenter = false,
            style = Stroke(width = 12f),
            topLeft = Offset(pivote.x - radioArco, pivote.y - radioArco),
            size = Size(radioArco * 2, radioArco * 2)
        )

        val marcas = listOf(-20f, -7f, -3f, -1f, 0f, 1f, 2f, 3f)
        val paintTexto = Paint().apply {
            textSize = anchoTotal * 0.05f
            typeface = Typeface.DEFAULT
            textAlign = Paint.Align.CENTER
        }

        marcas.forEach { valor ->
            val angulo = anguloInicio + (anguloFin - anguloInicio) * (valor - (-20f)) / (3f - (-20f))
            val anguloRad = angulo * PI / 180f

            val puntoInterno = Offset(
                x = pivote.x + (radioArco - 22f) * cos(anguloRad).toFloat(),
                y = pivote.y + (radioArco - 22f) * sin(anguloRad).toFloat()
            )
            val puntoTexto = Offset(
                x = pivote.x + (radioArco - 55f) * cos(anguloRad).toFloat(),
                y = pivote.y + (radioArco - 55f) * sin(anguloRad).toFloat()
            )

            val color = if (valor >= 0f) Color.Red else Color.Black
            drawLine(
                color = color,
                start = puntoInterno,
                end = Offset(
                    x = pivote.x + radioArco * cos(anguloRad).toFloat(),
                    y = pivote.y + radioArco * sin(anguloRad).toFloat()
                ),
                strokeWidth = 4f
            )

            paintTexto.color = if (color == Color.Red) android.graphics.Color.RED else android.graphics.Color.BLACK
            val texto = if (valor == 0f) "0" else if (valor > 0) "${valor.toInt()}" else "${-valor.toInt()}"
            drawContext.canvas.nativeCanvas.drawText(texto, puntoTexto.x, puntoTexto.y, paintTexto)
        }

        val paintNegro = Paint().apply {
            textSize = anchoTotal * 0.07f
            color = android.graphics.Color.BLACK
            textAlign = Paint.Align.CENTER
        }
        val paintRojo = Paint(paintNegro).apply { color = android.graphics.Color.RED }

        drawContext.canvas.nativeCanvas.drawText("-", anchoTotal * 0.13f, altoTotal * 0.22f, paintNegro)
        drawContext.canvas.nativeCanvas.drawText("+", anchoTotal * 0.87f, altoTotal * 0.22f, paintRojo)
        drawContext.canvas.nativeCanvas.drawText("dB", anchoTotal * 0.5f, altoTotal * 0.18f, paintNegro)

        val paintVU = Paint(paintNegro).apply { textSize = anchoTotal * 0.12f }
        drawContext.canvas.nativeCanvas.drawText("VU", anchoTotal * 0.5f, altoTotal * 0.5f, paintVU)

        val paintMarca = Paint(paintNegro).apply { textSize = anchoTotal * 0.035f }
        drawContext.canvas.nativeCanvas.drawText("DUB STUDIO CORP.", anchoTotal * 0.5f, altoTotal * 0.6f, paintMarca)

        val anguloAgujaRad = (anguloInicio + (anguloFin - anguloInicio) * (valorAnimado - (-20f)) / (3f - (-20f))) * PI / 180f
        val largoAguja = radioArco * 0.95f
        drawLine(
            color = Color.Black,
            start = pivote,
            end = Offset(
                x = pivote.x + largoAguja * cos(anguloAgujaRad).toFloat(),
                y = pivote.y + largoAguja * sin(anguloAgujaRad).toFloat()
            ),
            strokeWidth = 5f
        )

        drawArc(
            color = Color.Black,
            startAngle = 0f,
            sweepAngle = 180f,
            useCenter = true,
            topLeft = Offset(pivote.x - anchoTotal * 0.15f, pivote.y),
            size = Size(anchoTotal * 0.3f, anchoTotal * 0.3f)
        )
    }
}