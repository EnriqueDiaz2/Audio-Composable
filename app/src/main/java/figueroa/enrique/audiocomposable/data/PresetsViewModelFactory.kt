package figueroa.enrique.audiocomposable.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import figueroa.enrique.audiocomposable.data.repository.PresetRepository

class PresetsViewModelFactory(
    private val repository: PresetRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return PresetsViewModel(repository) as T
    }
}