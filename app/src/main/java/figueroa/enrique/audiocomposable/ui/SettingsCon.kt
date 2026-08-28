package figueroa.enrique.audiocomposable.ui

import androidx.activity.ComponentActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import figueroa.enrique.audiocomposable.iconos.ArrowBack

class SettingsCon : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeState.cargar(this)
        setContent {
            val modoOscuro by ThemeState.modoOscuro
            MaterialTheme(
                colorScheme = if (modoOscuro) darkColorScheme() else lightColorScheme()
            ) {
                SettingsScreen(
                    modoObscuro = modoOscuro,
                    onCambiarTema = { ThemeState.cambiar(this, it) }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    modoObscuro: Boolean,
    onCambiarTema: (Boolean) -> Unit
) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Configuracion")
                },
                actions = {
                    IconButton(
                        onClick = { (context as? ComponentActivity)?.finish() }
                    ) {
                        Icon(
                            imageVector = ArrowBack,
                            contentDescription = "ArrowBack"
                        )
                    }
                }
            )
        }
    ) {paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text("Modo Obscuro")
                Switch(
                    checked = modoObscuro,
                    onCheckedChange = onCambiarTema
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(
        modoObscuro = false,
        onCambiarTema = {}
    )
}