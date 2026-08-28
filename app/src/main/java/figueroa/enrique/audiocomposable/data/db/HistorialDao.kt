package figueroa.enrique.audiocomposable.data.db

import androidx.room3.Dao
import androidx.room3.Delete
import androidx.room3.Insert
import androidx.room3.Query
import figueroa.enrique.audiocomposable.data.model.Historial

@Dao
interface HistorialDao {
    @Query("SELECT * FROM historial")
    fun getAllHistorial(): List<Historial>

    @Insert
    fun insertHistorial(historial: Historial)

    @Delete
    fun deleteHistorial(historial: Historial)
}
