package figueroa.enrique.audiocomposable.data.model

data class HistorialConPreset(
    val id: Int,
    val presetName: String,
    val parametersId: Int,
    val threshold: Float,
    val ratio: Float,
    val attack: Float,
    val releases: Float,
    val knee: Float,
    val makeup: Float,
    val bypass: Boolean,
    val fechaHora: Long
)