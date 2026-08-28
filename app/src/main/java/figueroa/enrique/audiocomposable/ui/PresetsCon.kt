package figueroa.enrique.audiocomposable.ui

import android.content.Intent
import androidx.activity.ComponentActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.remember
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.room3.Room
import figueroa.enrique.audiocomposable.data.base.AppDatabase
import figueroa.enrique.audiocomposable.data.model.Preset
import figueroa.enrique.audiocomposable.iconos.DeleteF
import figueroa.enrique.audiocomposable.iconos.History
import figueroa.enrique.audiocomposable.iconos.Home
import figueroa.enrique.audiocomposable.iconos.SaveAs
import figueroa.enrique.audiocomposable.iconos.Settings
import figueroa.enrique.audiocomposable.iconos.Tune

class PresetsCon : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeState.cargar(this)
        setContent {
            val modoOscuro by ThemeState.modoOscuro

            MaterialTheme(
                colorScheme = if (modoOscuro) darkColorScheme() else lightColorScheme()
            ) {
                PresetsScreen()
            }
        }

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "mi_base_datos.db"
        ).build()

        val presetdao = db.presetsDao()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PresetsScreen() {

    var name by remember {
        mutableStateOf("")
    }

    var gener by remember {
        mutableStateOf("")
    }

    var place by remember {
        mutableStateOf("")
    }

    var parameters by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Audio Compressor")
                },
                actions = {
                    IconButton(onClick = {
                        context.startActivity(Intent(context, SettingsCon::class.java))
                    }) {
                        Icon(
                            imageVector = Settings,
                            contentDescription = "Configuración"
                        )
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = false,
                    onClick = {
                        context.startActivity(Intent(context, MainActivity::class.java)) },
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
                    selected = true,
                    onClick = {},
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
                    selected = false,
                    onClick = {
                        context.startActivity(Intent(context, HistorialCon::class.java)) },
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
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = {
                        name = it
                    },
                    label = {
                        Text("Nombre Del Preset")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                OutlinedTextField(
                    value = gener,
                    onValueChange = {
                        gener = it
                    },
                    label = {
                        Text("Genero")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                OutlinedTextField(
                    value = place,
                    onValueChange = {
                        place = it
                    },
                    label = {
                        Text("Lugar")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                OutlinedTextField(
                    value = parameters,
                    onValueChange = {
                        parameters = it
                    },
                    label = {
                        Text("Parametros")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                FloatingActionButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = SaveAs,
                        contentDescription = "Agregar"
                    )
                }
            }
            Row(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                FloatingActionButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = DeleteF,
                        contentDescription = "Eliminar"
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PresetsScreenPreview() {
    PresetsScreen()
}