package figueroa.enrique.audiocomposable.ui.main

import android.content.Intent
import android.os.Bundle
import figueroa.enrique.audiocomposable.R
import android.provider.Settings
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import figueroa.enrique.audiocomposable.iconos.ArrowBack
import figueroa.enrique.audiocomposable.ui.ThemeState
import figueroa.enrique.audiocomposable.ui.theme.AudioComposableTheme
import figueroa.enrique.audiocomposable.ui.theme.DarkBackground
import figueroa.enrique.audiocomposable.ui.theme.DarkBottomNavBackground
import figueroa.enrique.audiocomposable.ui.theme.DarkPrimary
import figueroa.enrique.audiocomposable.ui.theme.LightBackground
import figueroa.enrique.audiocomposable.ui.theme.LightBottomNavBackground
import figueroa.enrique.audiocomposable.ui.theme.LightPrimary

class SettingsCon : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeState.cargar(this)
        setContent {
            AudioComposableTheme(
                darkTheme = ThemeState.modoOscuro.value
            ) {
                SettingsScreen(
                    modoObscuro = ThemeState.modoOscuro.value,
                    onCambiarTema = { nuevoValor ->
                        ThemeState.guardar(this, nuevoValor)
                        recreate()
                    }
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
    val darkTheme = ThemeState.modoOscuro.value

    val background =
        if (darkTheme)
            DarkBackground
        else
            LightBackground

    val primary =
        if (darkTheme)
            DarkPrimary
        else
            LightPrimary

    val bottomNav =
        if (darkTheme)
            DarkBottomNavBackground
        else
            LightBottomNavBackground

    val context = LocalContext.current

    var tiempoRespuesta by remember {
        mutableStateOf(50f)
    }

    var mostrarDialogoTerminos by remember {
        mutableStateOf(false)
    }
    var mostrarDialogoAbout by remember {
        mutableStateOf(false)
    }

    val seleccionarCarpeta = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocumentTree()
    ) { uri ->
        if (uri != null) {
            Toast.makeText(context, "Carpeta seleccionada", Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text= "Configuracion",
                        color = LightBottomNavBackground,
                        fontSize = 20.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = primary,
                    titleContentColor = LightBottomNavBackground
                ),
                navigationIcon = {
                    IconButton(
                        onClick = {
                            context.startActivity(Intent(context, MainActivity::class.java))
                        }
                    ) {
                        Icon(
                            imageVector = ArrowBack,
                            contentDescription = "Regresar",
                            tint = LightBottomNavBackground
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxWidth()
                .background(background)
        ) {
            Column(
                modifier = Modifier
                    .padding(bottom = 24.dp)
            ) {
                Text(
                    text = "Modo Obscuro",
                    color = primary
                )
                Switch(
                    checked = modoObscuro,
                    onCheckedChange = onCambiarTema
                )
            }
            Column(
                modifier = Modifier
                    .padding(bottom = 24.dp)
            ) {
                Text(
                    text = "Comunicacion",
                    color = primary
                )
                Button(
                    onClick = {
                        val intent = Intent(Settings.ACTION_BLUETOOTH_SETTINGS)
                        context.startActivity(intent)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                        .background(background)
                ) {
                    Text(
                        text = "Activar Comunicacion Bluetooth",
                        color = bottomNav
                    )
                }
            }
            Column(
                modifier = Modifier
                    .padding(bottom = 24.dp)
            ) {
                Text(
                    text = "Guardar en una carpeta los presets",
                    color = primary
                )
                Button(
                    onClick = {
                        seleccionarCarpeta.launch(null)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                        .background(background)
                ) {
                    Text(
                        text = "Seleccionar Carpeta",
                        color = bottomNav
                    )
                }
            }
            Column(
                modifier = Modifier
                    .padding(bottom = 24.dp)
            ) {
                Text(
                    text = "Tiempo de respuesta",
                    color = primary
                )
                Slider(
                    value = tiempoRespuesta,
                    onValueChange = { tiempoRespuesta = it },
                    valueRange = 0f..100f,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "${tiempoRespuesta.toInt()} ms",
                    color = primary,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            Button(
                onClick = {
                    mostrarDialogoTerminos = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            ) {
                Text(
                    text = "Terminos y condiciones",
                    color = bottomNav
                )
            }
            Button(
                onClick = {
                    mostrarDialogoAbout = true
                },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Sobre de",
                    color = bottomNav
                )
            }
        }
    }

    if (mostrarDialogoTerminos) {
        AlertDialog(
            onDismissRequest = {
                mostrarDialogoTerminos = false
            },
            confirmButton = {
                Button(
                    onClick = {
                        mostrarDialogoTerminos = false
                    }) {
                    Text(
                        text = "Cerrar",
                        color = bottomNav
                    )
                }
            },
            title = {
                Text(
                    text = "Terminos y condiciones",
                    color = primary
                )
            },
            text = {
                Text("Aqui va el texto de terminos y condiciones.")
            }
        )
    }

    if (mostrarDialogoAbout) {
        AlertDialog(
            onDismissRequest = {
                mostrarDialogoAbout = false
            },
            confirmButton = {
                Button(
                    onClick = {
                        mostrarDialogoAbout = false
                    }) {
                    Text(
                        text = "Cerrar",
                        color = bottomNav
                    )
                }
            },
            title = {
                Text(
                    text = "Acerca de",
                    color = primary
                )
            },
            text = {
                Text(
                    text = "Audio Compressor " + "\n" +
                        "Version 1.0" + "\n" +
                        "Desarrollado por DevEDPATeam " + "\n\n" +
                        "Esta app comprime audio atraves de un ESP32 via bluetooth junto a DACS y IA entrenada para ello" + "\n\n" +
                        "Avisos Sobre Crashes o Bugs al correo:" +
                        "ejemplo@gmail.com" + "\n" +
                        "© 2026 DevEDPATeam",
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            },
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.app_icon),
                    contentDescription = "Logo de la aplicación",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(16.dp))
                )
            }
        )
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