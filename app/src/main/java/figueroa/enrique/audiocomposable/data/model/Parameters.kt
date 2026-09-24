package figueroa.enrique.audiocomposable.data.model

import androidx.room3.Entity
import androidx.room3.ForeignKey
import androidx.room3.Index
import androidx.room3.PrimaryKey

@Entity(
    tableName = "parameters",
    foreignKeys = [
        ForeignKey(
            entity = Preset::class,
            parentColumns = ["id"],
            childColumns = ["preset_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["preset_id"])]
)
data class Parameters(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val preset_id: Int,
    val threshold: Float,
    val ratio: Float,
    val attack: Float,
    val releases: Float,
    val knee: Float,
    val makeup: Float,
    val bypass: Boolean
)