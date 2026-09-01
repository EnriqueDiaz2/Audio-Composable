package figueroa.enrique.audiocomposable.ui

import android.content.Context
import androidx.compose.runtime.mutableStateOf

object ThemeState {
    private const val PREFS_NAME = "app_prefs"
    private const val KEY_MODO_OSCURO = "modo_oscuro"

    val modoOscuro = mutableStateOf(false)

    fun cargar(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        modoOscuro.value = prefs.getBoolean(KEY_MODO_OSCURO, false)
    }

    fun guardar(context: Context, valor: Boolean) {
        modoOscuro.value = valor
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putBoolean(KEY_MODO_OSCURO, valor).apply()
    }
}