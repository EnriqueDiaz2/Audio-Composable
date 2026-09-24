package figueroa.enrique.audiocomposable.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room3.Room
import figueroa.enrique.audiocomposable.data.PresetsViewModel
import figueroa.enrique.audiocomposable.data.PresetsViewModelFactory
import figueroa.enrique.audiocomposable.data.base.AppDatabase
import figueroa.enrique.audiocomposable.data.repository.PresetRepository
import figueroa.enrique.audiocomposable.data.model.Preset
import figueroa.enrique.audiocomposable.iconos.DeleteF
import figueroa.enrique.audiocomposable.iconos.Edit
import figueroa.enrique.audiocomposable.iconos.History
import figueroa.enrique.audiocomposable.iconos.Home
import figueroa.enrique.audiocomposable.iconos.SaveAs
import figueroa.enrique.audiocomposable.iconos.Settings
import figueroa.enrique.audiocomposable.iconos.Tune
import figueroa.enrique.audiocomposable.ui.AudioState
import figueroa.enrique.audiocomposable.ui.ThemeState
import figueroa.enrique.audiocomposable.ui.theme.AudioComposableTheme
import figueroa.enrique.audiocomposable.ui.theme.DarkAccentRed
import figueroa.enrique.audiocomposable.ui.theme.DarkBackground
import figueroa.enrique.audiocomposable.ui.theme.DarkBottomNavBackground
import figueroa.enrique.audiocomposable.ui.theme.DarkKnobBackground
import figueroa.enrique.audiocomposable.ui.theme.DarkPrimary
import figueroa.enrique.audiocomposable.ui.theme.DarkTextPrimary
import figueroa.enrique.audiocomposable.ui.theme.DarkTextSecondary
import figueroa.enrique.audiocomposable.ui.theme.LightAccentRed
import figueroa.enrique.audiocomposable.ui.theme.LightBackground
import figueroa.enrique.audiocomposable.ui.theme.LightBottomNavBackground
import figueroa.enrique.audiocomposable.ui.theme.LightKnobBackground
import figueroa.enrique.audiocomposable.ui.theme.LightPrimary
import figueroa.enrique.audiocomposable.ui.theme.LightTextPrimary
import figueroa.enrique.audiocomposable.ui.theme.LightTextSecondary

class PresetsCon : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeState.cargar(this)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "app_database"
        ).fallbackToDestructiveMigration().build()

        val repository = PresetRepository(db.presetDao(), db.historialDao(), db.parametersDao())
        val viewModel = PresetsViewModelFactory(repository)
            .create(PresetsViewModel::class.java)

        setContent {
            AudioComposableTheme(
                darkTheme = ThemeState.modoOscuro.value
            ) {
                PresetsScreen(viewModel = viewModel, repository = repository)
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PresetsScreen(viewModel: PresetsViewModel, repository: PresetRepository) {
    val darkTheme = ThemeState.modoOscuro.value

    val background =
        if (darkTheme)
            DarkBackground
        else
            LightBackground

    val knobBackground =
        if (darkTheme)
            DarkKnobBackground
        else
            LightKnobBackground

    val primary =
        if (darkTheme)
            DarkPrimary
        else
            LightPrimary

    val textPrimary =
        if (darkTheme)
            DarkTextPrimary
        else
            LightTextPrimary

    val textSecondary =
        if (darkTheme)
            DarkTextSecondary
        else
            LightTextSecondary

    val accentRed =
        if (darkTheme)
            DarkAccentRed
        else
            LightAccentRed

    val bottomNav =
        if (darkTheme)
            DarkBottomNavBackground
        else
            LightBottomNavBackground

    var name by rememberSaveable { mutableStateOf("") }
    var gener by rememberSaveable { mutableStateOf("") }
    var place by rememberSaveable { mutableStateOf("") }

    val context = LocalContext.current
    val presets by viewModel.presets.collectAsState()
    val editando = viewModel.presetEnEdicion != null

    fun cargarCampos(preset: Preset) {
        name = preset.name
        gener = preset.gener
        place = preset.place
    }

    fun limpiarCampos() {
        name = ""
        gener = ""
        place = ""
    }

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
                        context.startActivity(Intent(context, SettingsCon::class.java))
                    }) {
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
                        context.startActivity(Intent(context, MainActivity::class.java))
                    },
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
                        context.startActivity(Intent(context, HistorialCon::class.java))
                    },
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
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    singleLine = true,
                    maxLines = 10,
                    minLines = 1,
                    placeholder = { Text("Blue Jazz") },
                    keyboardOptions = KeyboardOptions( keyboardType = KeyboardType.Text),
                    shape = CircleShape,
                    label = {
                        Text(
                            text = "Nombre Del Preset",
                            color = textSecondary
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = gener,
                    onValueChange = { gener = it },
                    singleLine = true,
                    maxLines = 8,
                    minLines = 1,
                    placeholder = { Text("Rock") },
                    keyboardOptions = KeyboardOptions( keyboardType = KeyboardType.Text),
                    shape = CircleShape,
                    label = {
                        Text(
                            text = "Genero",
                            color = textSecondary
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = place,
                    onValueChange = { place = it },
                    singleLine = true,
                    maxLines = 10,
                    minLines = 1,
                    placeholder = { Text("Estudio") },
                    keyboardOptions = KeyboardOptions( keyboardType = KeyboardType.Text),
                    shape = CircleShape,
                    label = {
                        Text(
                            text = "Lugar",
                            color = textSecondary
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                FloatingActionButton(
                    onClick = {
                        viewModel.guardarPreset(
                            name.trim(),
                            gener.trim(),
                            place.trim(),
                            thresold = AudioState.threshold,
                            ratio = AudioState.ratio,
                            attack = AudioState.attack,
                            releases = AudioState.release,
                            knee = AudioState.knee,
                            makeup = AudioState.makeup,
                            bypass = AudioState.bypass
                        )
                        limpiarCampos()
                    },
                    containerColor = knobBackground,
                    contentColor = accentRed,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Icon(
                        imageVector = SaveAs,
                        contentDescription = if (editando) "Guardar cambios" else "Agregar",
                        tint = accentRed
                    )
                }
            }
            LazyColumn {
                items(presets) { preset: Preset ->
                    PresetItemCard(
                        preset = preset,
                        repository = repository,
                        viewModel = viewModel,
                        knobBackground = knobBackground,
                        accentRed = accentRed,
                        textSecondary = textSecondary,
                        cargarCampos = ::cargarCampos
                    )
                }
            }
        }
    }
}

@Composable
fun PresetItemCard(
    preset: Preset,
    repository: PresetRepository,
    viewModel: PresetsViewModel,
    knobBackground: Color,
    accentRed: Color,
    textSecondary: Color,
    cargarCampos: (Preset) -> Unit
) {
    val parameters by repository.obtenerParametroPorPresetId(preset.id).collectAsState(initial = null)

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = preset.name, fontSize = 16.sp)
                Text(text = preset.gener, fontSize = 14.sp, color = textSecondary)
                Text(text = preset.place, fontSize = 14.sp, color = textSecondary)

                parameters?.let { p ->
                    Spacer(modifier = Modifier.padding(top = 4.dp))
                    Text(text = "Threshold: ${p.threshold}", fontSize = 12.sp, color = textSecondary)
                    Text(text = "Ratio: ${p.ratio}", fontSize = 12.sp, color = textSecondary)
                    Text(text = "Attack: ${p.attack}", fontSize = 12.sp, color = textSecondary)
                    Text(text = "Release: ${p.releases}", fontSize = 12.sp, color = textSecondary)
                    Text(text = "Knee: ${p.knee}", fontSize = 12.sp, color = textSecondary)
                    Text(text = "Makeup: ${p.makeup}", fontSize = 12.sp, color = textSecondary)
                    Text(text = "Bypass: ${p.bypass}", fontSize = 12.sp, color = textSecondary)
                }
            }
            Row {
                IconButton(
                    onClick = {
                        viewModel.actualizarPreset(preset)
                        cargarCampos(preset)
                    },
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(knobBackground),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = knobBackground,
                        contentColor = accentRed
                    )
                ) {
                    Icon(
                        imageVector = Edit,
                        contentDescription = "Editar",
                        tint = accentRed
                    )
                }
                IconButton(
                    onClick = {
                        viewModel.eliminarPreset(preset)
                    },
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(knobBackground),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = knobBackground,
                        contentColor = accentRed
                    )
                ) {
                    Icon(
                        imageVector = DeleteF,
                        contentDescription = "Eliminar",
                        tint = accentRed
                    )
                }
            }
        }
    }
}
