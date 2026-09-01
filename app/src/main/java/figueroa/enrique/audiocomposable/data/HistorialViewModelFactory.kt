package figueroa.enrique.audiocomposable.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import figueroa.enrique.audiocomposable.data.db.HistorialDao

class HistorialViewModelFactory(
    private val historialDao: HistorialDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return HistorialViewModel(historialDao) as T
    }
}