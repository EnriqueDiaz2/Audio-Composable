package figueroa.enrique.audiocomposable.data.model

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(tableName="historial")
data class Historial(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String
)