package figueroa.enrique.audiocomposable.ui

import androidx.lifecycle.ViewModel

class HomeScreenViewModel : ViewModel() {
    var threshold: Float
        get() = AudioState.threshold
        set(value) { AudioState.threshold = value }

    var ratio: Float
        get() = AudioState.ratio
        set(value) { AudioState.ratio = value }

    var attack: Float
        get() = AudioState.attack
        set(value) { AudioState.attack = value }

    var release: Float
        get() = AudioState.release
        set(value) { AudioState.release = value }

    var knee: Float
        get() = AudioState.knee
        set(value) { AudioState.knee = value }

    var makeup: Float
        get() = AudioState.makeup
        set(value) { AudioState.makeup = value }

    var bypass: Boolean
        get() = AudioState.bypass
        set(value) { AudioState.bypass = value }

    fun onThresholdChange(value: Float) { threshold = value }
    fun onRatioChange(value: Float) { ratio = value }
    fun onAttackChange(value: Float) { attack = value }
    fun onReleaseChange(value: Float) { release = value }
    fun onKneeChange(value: Float) { knee = value }
    fun onMakeupChange(value: Float) { makeup = value }
    fun toggleBypass(bool: Boolean) { bypass = bool }
}
