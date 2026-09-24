package figueroa.enrique.audiocomposable.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object AudioState {
    var threshold by mutableFloatStateOf(0.5f)
    var ratio by mutableFloatStateOf(0.3f)
    var attack by mutableFloatStateOf(0.4f)
    var release by mutableFloatStateOf(0.5f)
    var knee by mutableFloatStateOf(0.2f)
    var makeup by mutableFloatStateOf(0.5f)
    var bypass by mutableStateOf(false)
}
