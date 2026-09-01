package figueroa.enrique.audiocomposable.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import figueroa.enrique.audiocomposable.data.db.HistorialDao
import figueroa.enrique.audiocomposable.data.model.HistorialConPreset
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class HistorialViewModel(
    historialDao: HistorialDao
) : ViewModel() {

    val historial: StateFlow<List<HistorialConPreset>> =
        historialDao.obtenerHistorial()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )
}