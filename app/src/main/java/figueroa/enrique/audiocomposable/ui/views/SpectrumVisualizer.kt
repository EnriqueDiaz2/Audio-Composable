package figueroa.enrique.audiocomposable.ui.views

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color

@Composable
fun SpectrumVisualizer(
    values: List<Float>,
    modifier: Modifier = Modifier,
    color: Color
) {

    Canvas(
        modifier = modifier
    ) {

        if (values.isEmpty()) return@Canvas

        val count = values.size

        val spacing =
            size.width / count

        val barWidth =
            spacing * 0.55f

        val centerY =
            size.height / 2f

        val maxHeight =
            size.height * 0.42f

        values.forEachIndexed { index, rawValue ->

            val value =
                rawValue.coerceIn(0f, 1f)

            val height =
                maxHeight * value

            val x =
                index * spacing +
                        (spacing - barWidth) / 2f


            // Barra principal

            drawRoundRect(
                color = color,

                topLeft = Offset(
                    x,
                    centerY - height
                ),

                size = Size(
                    barWidth,
                    height
                ),

                cornerRadius = CornerRadius(
                    barWidth / 2f,
                    barWidth / 2f
                )
            )


            // Reflejo

            drawRoundRect(
                color = color.copy(alpha = 0.20f),

                topLeft = Offset(
                    x,
                    centerY
                ),

                size = Size(
                    barWidth,
                    height * 0.40f
                ),

                cornerRadius = CornerRadius(
                    barWidth / 2f,
                    barWidth / 2f
                )
            )
        }
    }
}