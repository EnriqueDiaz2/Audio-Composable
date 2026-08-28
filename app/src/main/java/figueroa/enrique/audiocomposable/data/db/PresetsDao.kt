package figueroa.enrique.audiocomposable.data.db

import androidx.room3.Dao
import androidx.room3.Delete
import androidx.room3.Insert
import androidx.room3.Query
import figueroa.enrique.audiocomposable.data.model.Preset

@Dao
interface PresetsDao {
    @Query("SELECT * FROM presets")
    fun getAllPresets(): List<Preset>

    @Insert
    fun insertPreset(preset: Preset)

    @Delete
    fun deletePreset(preset: Preset)
}