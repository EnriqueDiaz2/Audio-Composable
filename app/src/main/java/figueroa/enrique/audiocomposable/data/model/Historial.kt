package figueroa.enrique.audiocomposable.data.model

import androidx.room3.Entity
import androidx.room3.ForeignKey
import androidx.room3.Index
import androidx.room3.PrimaryKey

@Entity(
    tableName = "historial",
    foreignKeys = [
        ForeignKey(
            entity = Preset::class,
            parentColumns = ["id"],
            childColumns = ["preset_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Parameters::class,
            parentColumns = ["id"],
            childColumns = ["parameters_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["preset_id"]),
        Index(value = ["parameters_id"])
    ]
)
data class Historial(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val preset_id: Int,
    val parameters_id: Int,
    val fechaHora: Long = System.currentTimeMillis()
)