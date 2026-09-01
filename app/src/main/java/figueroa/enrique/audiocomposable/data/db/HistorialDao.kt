package figueroa.enrique.audiocomposable.data.db

import androidx.room3.Dao
import androidx.room3.Delete
import androidx.room3.Insert
import androidx.room3.Query
import figueroa.enrique.audiocomposable.data.model.Historial
import figueroa.enrique.audiocomposable.data.model.HistorialConPreset
import kotlinx.coroutines.flow.Flow

@Dao
interface HistorialDao {
    @Insert
    suspend fun insertHistorial(historial: Historial)

    @Query(
        """
        SELECT h.id AS id,
               p.name AS presetName,
               h.fechaHora AS fechaHora
        FROM historial h
        INNER JOIN presets p ON p.id = h.preset_id
        ORDER BY h.fechaHora DESC
        """
    )
    fun obtenerHistorial(): Flow<List<HistorialConPreset>>

    @Delete
    suspend fun deleteHistorial(historial: Historial)
}
