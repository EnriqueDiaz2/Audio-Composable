package figueroa.enrique.audiocomposable.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import figueroa.enrique.audiocomposable.data.repository.PresetRepository
import figueroa.enrique.audiocomposable.data.model.Preset
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PresetsViewModel(
    private val repository: PresetRepository
) : ViewModel() {

    val presets: StateFlow<List<Preset>> =
        repository.obtenerPresets()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    // Guarda si estamos editando un preset existente (su id) o creando uno nuevo (null)
    var presetEnEdicion: Preset? = null
        private set

    fun actualizarPreset(preset: Preset) {
        presetEnEdicion = preset
    }

    fun guardarPreset(name: String, gener: String, place: String, parameters: String) {
        viewModelScope.launch {
            val enEdicion = presetEnEdicion
            if (enEdicion == null) {
                val preset = Preset(name = name, gener = gener, place = place, parameters = parameters)
                repository.guardarPresetConHistorial(preset)
            } else {
                val preset = enEdicion.copy(name = name, gener = gener, place = place, parameters = parameters)
                repository.actualizarPresets(preset)
            }
            presetEnEdicion = null
        }
    }

    fun eliminarPreset(preset: Preset) {
        viewModelScope.launch {
            repository.eliminarPresets(preset)
        }
    }
}