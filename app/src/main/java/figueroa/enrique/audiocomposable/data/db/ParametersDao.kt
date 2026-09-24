package figueroa.enrique.audiocomposable.data.db

import androidx.room3.Dao
import androidx.room3.Delete
import androidx.room3.Insert
import androidx.room3.Query
import androidx.room3.Update
import figueroa.enrique.audiocomposable.data.model.Parameters
import kotlinx.coroutines.flow.Flow

@Dao
interface ParametersDao {
    @Query("SELECT * FROM parameters WHERE preset_id = :presetId")
    fun obtenerParametros(presetId: Int): Flow<List<Parameters>>

    @Query("SELECT * FROM parameters WHERE preset_id = :presetId LIMIT 1")
    fun obtenerParametroPorPresetId(presetId: Int): Flow<Parameters?>

    @Insert
    suspend fun insertParameter(parameters: Parameters): Long

    @Update
    suspend fun actualizarParameter(parameters: Parameters)

    @Delete
    suspend fun eliminarParameter(parameters: Parameters)
}