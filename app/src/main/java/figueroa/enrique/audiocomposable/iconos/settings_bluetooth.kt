package figueroa.enrique.audiocomposable.iconos

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

@Suppress("CheckReturnValue")
val settings_bluetooth: ImageVector
    get() {
        if (_settings_bluetooth != null) {
            return _settings_bluetooth!!
        }
        _settings_bluetooth =
            ImageVector.Builder(
                name = "settings_bluetooth",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f,
            )
                .apply {
                    path(
                        fill = SolidColor(Color.Black),
                        fillAlpha = 1f,
                        stroke = null,
                        strokeAlpha = 1f,
                        strokeLineWidth = 1f,
                        strokeLineCap = StrokeCap.Butt,
                        strokeLineJoin = StrokeJoin.Bevel,
                        strokeLineMiter = 1f,
                        pathFillType = PathFillType.Companion.NonZero,
                    ) {
                        moveTo(7.29f, 23.71f)
                        quadTo(7f, 23.43f, 7f, 23f)
                        reflectiveQuadTo(7.29f, 22.29f)
                        reflectiveQuadTo(8f, 22f)
                        reflectiveQuadToRelative(0.71f, 0.29f)
                        reflectiveQuadTo(9f, 23f)
                        reflectiveQuadTo(8.71f, 23.71f)
                        reflectiveQuadTo(8f, 24f)
                        quadTo(7.58f, 24f, 7.29f, 23.71f)
                        close()
                        moveToRelative(4f, 0f)
                        quadTo(11f, 23.43f, 11f, 23f)
                        reflectiveQuadToRelative(0.29f, -0.71f)
                        reflectiveQuadTo(12f, 22f)
                        reflectiveQuadToRelative(0.71f, 0.29f)
                        reflectiveQuadTo(13f, 23f)
                        reflectiveQuadToRelative(-0.29f, 0.71f)
                        reflectiveQuadTo(12f, 24f)
                        reflectiveQuadTo(11.29f, 23.71f)
                        close()
                        moveToRelative(4f, 0f)
                        quadTo(15f, 23.43f, 15f, 23f)
                        reflectiveQuadToRelative(0.29f, -0.71f)
                        reflectiveQuadTo(16f, 22f)
                        quadToRelative(0.43f, 0f, 0.71f, 0.29f)
                        reflectiveQuadTo(17f, 23f)
                        reflectiveQuadToRelative(-0.29f, 0.71f)
                        reflectiveQuadTo(16f, 24f)
                        reflectiveQuadTo(15.29f, 23.71f)
                        close()
                        moveTo(11f, 20f)
                        verticalLineTo(12.4f)
                        lineTo(6.4f, 17f)
                        lineTo(5f, 15.6f)
                        lineTo(10.6f, 10f)
                        lineTo(5f, 4.4f)
                        lineTo(6.4f, 3f)
                        lineTo(11f, 7.6f)
                        verticalLineTo(0f)
                        horizontalLineToRelative(1f)
                        lineToRelative(5.7f, 5.7f)
                        lineTo(13.4f, 10f)
                        lineToRelative(4.3f, 4.3f)
                        lineTo(12f, 20f)
                        horizontalLineTo(11f)
                        close()
                        moveToRelative(2f, -3.85f)
                        lineTo(14.9f, 14.3f)
                        lineTo(13f, 12.4f)
                        verticalLineToRelative(3.75f)
                        close()
                        moveTo(13f, 7.6f)
                        lineTo(14.9f, 5.7f)
                        lineTo(13f, 3.85f)
                        verticalLineTo(7.6f)
                        close()
                    }
                }
                .build()
        return _settings_bluetooth!!
    }

private var _settings_bluetooth: ImageVector? = null