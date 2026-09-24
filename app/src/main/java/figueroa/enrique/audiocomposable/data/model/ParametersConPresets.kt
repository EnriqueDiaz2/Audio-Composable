package figueroa.enrique.audiocomposable.data.model

data class ParametersConPresets(
    val id: Int,
    val threshold: Float,
    val ratio: Float,
    val attack: Float,
    val releases: Float,
    val knee: Float,
    val makeup: Float,
    val bypass: Boolean,
    val preset_id: Int
)