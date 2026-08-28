package figueroa.enrique.audiocomposable.data.model

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(tableName="presets")
data class Preset(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val gener: String,
    val place: String,
    val parameters: String
)