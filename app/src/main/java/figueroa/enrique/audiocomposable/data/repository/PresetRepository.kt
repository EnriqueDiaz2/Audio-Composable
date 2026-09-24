package figueroa.enrique.audiocomposable.data.repository

import figueroa.enrique.audiocomposable.data.db.HistorialDao
import figueroa.enrique.audiocomposable.data.db.ParametersDao
import figueroa.enrique.audiocomposable.data.db.PresetsDao
import figueroa.enrique.audiocomposable.data.model.Historial
import figueroa.enrique.audiocomposable.data.model.Parameters
import figueroa.enrique.audiocomposable.data.model.Preset
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PresetRepository(
    private val presetDao: PresetsDao,
    private val historialDao: HistorialDao,
    private val parametersDao: ParametersDao
) {
    suspend fun guardarPresetConHistorial(preset: Preset, parametros: Parameters) = withContext(Dispatchers.IO) {
        val idGenerado = presetDao.insertPreset(preset)
        val parametersId = parametersDao.insertParameter(parametros.copy(preset_id = idGenerado.toInt()))
        historialDao.insertHistorial(
            Historial(
                preset_id = idGenerado.toInt(),
                parameters_id = parametersId.toInt()
            )
        )
    }

    suspend fun guardarPresetsConParameters(
        preset: Preset,
        parameters: Parameters
    ) = withContext(Dispatchers.IO) {
        val idGenerado = presetDao.insertPreset(preset)
        parametersDao.insertParameter(parameters.copy(preset_id = idGenerado.toInt()))
    }

    suspend fun actualizarPresets(preset: Preset, parameters: Parameters) = withContext(Dispatchers.IO) {
        presetDao.actualizarPreset(preset)
        parametersDao.actualizarParameter(parameters.copy(preset_id = preset.id))
    }

    suspend fun eliminarPresets(preset: Preset) = withContext(Dispatchers.IO) {
        presetDao.eliminarPreset(preset)
    }

    fun obtenerPresets() = presetDao.obtenerPresets()

    fun obtenerParametros(presetId: Int) = parametersDao.obtenerParametros(presetId)

    fun obtenerParametroPorPresetId(presetId: Int) = parametersDao.obtenerParametroPorPresetId(presetId)
}
