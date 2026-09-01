package figueroa.enrique.audiocomposable.data.repository

import figueroa.enrique.audiocomposable.data.db.HistorialDao
import figueroa.enrique.audiocomposable.data.db.PresetsDao
import figueroa.enrique.audiocomposable.data.model.Historial
import figueroa.enrique.audiocomposable.data.model.Preset
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PresetRepository(
    private val presetDao: PresetsDao,
    private val historialDao: HistorialDao
) {
    suspend fun guardarPresetConHistorial(preset: Preset) = withContext(Dispatchers.IO) {
        val idGenerado = presetDao.insertPreset(preset)
        historialDao.insertHistorial(Historial(preset_id = idGenerado.toInt()))
    }

    suspend fun actualizarPresets(preset: Preset) = withContext(Dispatchers.IO) {
        presetDao.actualizarPreset(preset)
    }

    suspend fun eliminarPresets(preset: Preset) = withContext(Dispatchers.IO) {
        presetDao.eliminarPreset(preset)
    }

    fun obtenerPresets() = presetDao.obtenerPresets()
}
