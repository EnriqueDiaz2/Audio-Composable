package figueroa.enrique.audiocomposable.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room3.Room
import figueroa.enrique.audiocomposable.data.HistorialViewModel
import figueroa.enrique.audiocomposable.data.HistorialViewModelFactory
import figueroa.enrique.audiocomposable.data.base.AppDatabase
import figueroa.enrique.audiocomposable.data.model.HistorialConPreset
import figueroa.enrique.audiocomposable.iconos.History
import figueroa.enrique.audiocomposable.iconos.Home
import figueroa.enrique.audiocomposable.iconos.Settings
import figueroa.enrique.audiocomposable.iconos.Tune
import figueroa.enrique.audiocomposable.ui.ThemeState
import figueroa.enrique.audiocomposable.ui.theme.AudioComposableTheme
import figueroa.enrique.audiocomposable.ui.theme.DarkBackground
import figueroa.enrique.audiocomposable.ui.theme.DarkBottomNavBackground
import figueroa.enrique.audiocomposable.ui.theme.DarkKnobBackground
import figueroa.enrique.audiocomposable.ui.theme.DarkPrimary
import figueroa.enrique.audiocomposable.ui.theme.DarkTextPrimary
import figueroa.enrique.audiocomposable.ui.theme.LightBackground
import figueroa.enrique.audiocomposable.ui.theme.LightBottomNavBackground
import figueroa.enrique.audiocomposable.ui.theme.LightKnobBackground
import figueroa.enrique.audiocomposable.ui.theme.LightPrimary
import figueroa.enrique.audiocomposable.ui.theme.LightTextPrimary
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HistorialCon : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeState.cargar(this)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "app_database"
        ).build()

        val viewModel = HistorialViewModelFactory(db.historialDao())
            .create(HistorialViewModel::class.java)

        setContent {
            AudioComposableTheme(
                darkTheme = ThemeState.modoOscuro.value
            ) {
                HistorialScreen(viewModel = viewModel)
            }
        }
    }
}

fun formatearFecha(millis: Long): String {
    val formato = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    return formato.format(Date(millis))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistorialScreen(viewModel: HistorialViewModel) {
    val context = LocalContext.current

    val historial by viewModel.historial.collectAsState()

    val darkTheme = ThemeState.modoOscuro.value

    val background =
        if (darkTheme)
            DarkBackground
        else
            LightBackground

    val textPrimary =
        if (darkTheme)
            DarkTextPrimary
        else
            LightTextPrimary

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

    val knobBackground =
        if (darkTheme)
            DarkKnobBackground
        else
            LightKnobBackground

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Audio Compressor",
                        color = LightBottomNavBackground,
                        fontSize = 20.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = primary,
                    titleContentColor = LightBottomNavBackground
                ),
                actions = {
                    IconButton(onClick = {
                        context.startActivity(Intent(context, SettingsCon::class.java)) })
                    {
                        Icon(
                            imageVector = Settings,
                            contentDescription = "Configuración",
                            tint = LightBottomNavBackground
                        )
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = bottomNav,
                contentColor = textPrimary
            ) {
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
                    selected = false,
                    onClick = {
                        context.startActivity(Intent(context, PresetsCon::class.java)) },
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
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(background)
        ) {
            LazyColumn {
                items(historial) { item: HistorialConPreset ->
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(12.dp)
                                .background(knobBackground)
                                .fillMaxSize()
                        ) {
                            Text(item.presetName)
                            Text(formatearFecha(item.fechaHora))
                        }
                    }
                }
            }
        }
    }
}

/*@Preview(showBackground = true)
@Composable
fun HistorialScreenPreview() {
    HistorialScreen()
}*/