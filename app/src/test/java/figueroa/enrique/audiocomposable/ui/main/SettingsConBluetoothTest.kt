package figueroa.enrique.audiocomposable.ui.main

import org.junit.Assert.assertTrue
import org.junit.Test

class SettingsConBluetoothTest {
    @Test
    fun `debe reconocer nombres de modulos ESP32 y bluetooth`() {
        assertTrue(matchesEsp32DeviceName("ESP32-Audio"))
        assertTrue(matchesEsp32DeviceName("HC-05"))
        assertTrue(matchesEsp32DeviceName("BluetoothModule"))
    }
}
