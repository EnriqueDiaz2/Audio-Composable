package figueroa.enrique.audiocomposable.ui

import android.content.Intent
import androidx.activity.ComponentActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.room3.Room
import figueroa.enrique.audiocomposable.data.base.AppDatabase
import figueroa.enrique.audiocomposable.iconos.History
import figueroa.enrique.audiocomposable.iconos.Home
import figueroa.enrique.audiocomposable.iconos.Settings
import figueroa.enrique.audiocomposable.iconos.Tune

class HistorialCon : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeState.cargar(this)
        setContent {
            val modoOscuro by ThemeState.modoOscuro
            MaterialTheme(
                colorScheme = if (modoOscuro) darkColorScheme() else lightColorScheme()
            ) {
                HistorialScreen()
            }
        }

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "mi_base_datos.db"
        ).build()

        val historialdao = db.historialDao()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistorialScreen() {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Audio Compressor")
                },
                actions = {
                    IconButton(onClick = { context.startActivity(Intent(context, SettingsCon::class.java)) })
                    {
                        Icon(imageVector = Settings, contentDescription = "Configuración")
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = false,
                    onClick = { context.startActivity(Intent(context, MainActivity::class.java)) },
                    icon = {
                        Icon(
                            imageVector = Home,
                            contentDescription = "Inicio"
                        )
                    },
                    label = {
                        Text("Inicio")
                    }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { context.startActivity(Intent(context, PresetsCon::class.java)) },
                    icon = {
                        Icon(
                            imageVector = Tune,
                            contentDescription = "Presets"
                        )
                    },
                    label = {
                        Text("Presets")
                    }
                )
                NavigationBarItem(
                    selected = true,
                    onClick = {},
                    icon = {
                        Icon(
                            imageVector = History,
                            contentDescription = "Historial"
                        )
                    },
                    label = {
                        Text("Historial")
                    }
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
        ) {

            Text("Contenido de la tarjeta")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HistorialScreenPreview() {
    HistorialScreen()
}