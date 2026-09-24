package figueroa.enrique.audiocomposable.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import figueroa.enrique.audiocomposable.iconos.GraphicEq
import figueroa.enrique.audiocomposable.ui.views.BassBoostKnob
import figueroa.enrique.audiocomposable.iconos.History
import figueroa.enrique.audiocomposable.iconos.Home
import figueroa.enrique.audiocomposable.iconos.RadioButtonChecked
import figueroa.enrique.audiocomposable.iconos.Settings
import figueroa.enrique.audiocomposable.iconos.Tune
import figueroa.enrique.audiocomposable.ui.HomeScreenViewModel
import figueroa.enrique.audiocomposable.ui.ThemeState
import figueroa.enrique.audiocomposable.ui.theme.AudioComposableTheme
import figueroa.enrique.audiocomposable.ui.theme.DarkAccentRed
import figueroa.enrique.audiocomposable.ui.theme.DarkBackground
import figueroa.enrique.audiocomposable.ui.theme.DarkBottomNavBackground
import figueroa.enrique.audiocomposable.ui.theme.DarkKnobBackground
import figueroa.enrique.audiocomposable.ui.theme.DarkKnobBorder
import figueroa.enrique.audiocomposable.ui.theme.DarkPrimary
import figueroa.enrique.audiocomposable.ui.theme.DarkSpectrumBackground
import figueroa.enrique.audiocomposable.ui.theme.DarkTextPrimary
import figueroa.enrique.audiocomposable.ui.theme.DarkTextSecondary
import figueroa.enrique.audiocomposable.ui.theme.LightAccentRed
import figueroa.enrique.audiocomposable.ui.theme.LightBackground
import figueroa.enrique.audiocomposable.ui.theme.LightBottomNavBackground
import figueroa.enrique.audiocomposable.ui.theme.LightKnobBackground
import figueroa.enrique.audiocomposable.ui.theme.LightKnobBorder
import figueroa.enrique.audiocomposable.ui.theme.LightPrimary
import figueroa.enrique.audiocomposable.ui.theme.LightSpectrumBackground
import figueroa.enrique.audiocomposable.ui.theme.LightTextPrimary
import figueroa.enrique.audiocomposable.ui.theme.LightTextSecondary
import figueroa.enrique.audiocomposable.ui.views.BypassButton
import figueroa.enrique.audiocomposable.ui.views.SpectrumVisualizer
import figueroa.enrique.audiocomposable.ui.views.VuMeter
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        ThemeState.cargar(this)
        setContent {
            AudioComposableTheme(
                darkTheme = ThemeState.modoOscuro.value
            ) {
                PantallaPrincipal()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaPrincipal(viewModel: HomeScreenViewModel = viewModel()) {
    val darkTheme = ThemeState.modoOscuro.value

    val background =
        if (darkTheme)
            DarkBackground
        else
            LightBackground

    val spectrumBackground =
        if (darkTheme)
            DarkSpectrumBackground
        else
            LightSpectrumBackground

    val knobBackground =
        if (darkTheme)
            DarkKnobBackground
        else
            LightKnobBackground

    val knobBorder =
        if (darkTheme)
            DarkKnobBorder
        else
            LightKnobBorder

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

    /*var threshold by remember { mutableFloatStateOf(0.5f) }
    var ratio by remember { mutableFloatStateOf(0.3f) }
    var attack by remember { mutableFloatStateOf(0.4f) }
    var release by remember { mutableFloatStateOf(0.5f) }
    var knee by remember { mutableFloatStateOf(0.2f) }
    var makeup by remember { mutableFloatStateOf(0.5f) }
    var bypassActivo by remember { mutableStateOf(false) }*/

    val spectrumValues = remember {
        List(32) {
            Random.nextFloat()
        }
    }

    var circularMode by rememberSaveable { mutableStateOf(false) }

    val context = LocalContext.current

    Scaffold(
        containerColor = background,
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
                    IconButton(
                        onClick = {
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
                    selected = true,
                    onClick = {},
                    icon = {
                        Icon(
                            imageVector = Home,
                            contentDescription = "Inicio")
                           },
                    label = {
                        Text("Inicio")
                    }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {
                        context.startActivity(Intent(context, PresetsCon::class.java))
                    },
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
                .padding(top = 8.dp)
                .fillMaxSize()
                .background(background)
        ) {
            // ============================================
            // SPECTRUM
            // ============================================
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .padding(horizontal = 16.dp)
                    .clip(
                        RoundedCornerShape(12.dp)
                    )
                    .background(
                        spectrumBackground
                    )
            ) {
                // Solo se crea el visualizador seleccionado
                if (circularMode) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        VuMeter(
                            valorDb = 1f, // dato de prueba por ahora
                            modifier = Modifier
                                .weight(1f)
                        )
                        VuMeter(
                            valorDb = -3f, // dato de prueba por ahora
                            modifier = Modifier
                                .weight(1f)
                        )
                    }
                } else {
                    SpectrumVisualizer(
                        values = spectrumValues,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        color = accentRed
                    )
                }
            }
            // ============================================
            // CAMBIAR SPECTRUM
            // ============================================
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(knobBackground)
                    .clickable {
                        circularMode = !circularMode
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector =
                        if (circularMode)
                            RadioButtonChecked
                        else
                            GraphicEq,
                    contentDescription = null,
                    tint = accentRed
                )
            }
            // ======================================
            // CONTENEDOR DE PERILLAS
            // ======================================
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(16.dp))
                    .background(knobBackground)
                    .horizontalScroll(state = rememberScrollState())
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BassBoostKnob(
                    label = "Treashol",
                    value = viewModel.threshold,
                    onValueChange = { viewModel.onThresholdChange(it) },
                    onValueChangeFinished = {},
                    valueText = { "${(it * 100 - 60).toInt()} dB" }, // ejemplo: rango -60dB a 0dB
                    accentColor = accentRed,
                    backgroundColor = knobBackground,
                    borderColor = knobBorder,
                    textPrimary = textPrimary,
                    textSecondary = textSecondary
                )
                Spacer(modifier = Modifier
                    .width(24.dp))

                BassBoostKnob(
                    label = "Radio",
                    value = viewModel.ratio,
                    onValueChange = { viewModel.onRatioChange(it) },
                    onValueChangeFinished = { },
                    valueText = { "${(1 + it * 9).toInt()}:1" }, // ejemplo: rango 1:1 a 10:1
                    accentColor = accentRed,
                    backgroundColor = knobBackground,
                    borderColor = knobBorder,
                    textPrimary = textPrimary,
                    textSecondary = textSecondary
                )
                Spacer(modifier = Modifier
                    .width(24.dp))

                BassBoostKnob(
                    label = "Ataque",
                    value = viewModel.attack,
                    onValueChange = { viewModel.onAttackChange(it) },
                    onValueChangeFinished = { },
                    valueText = { "${(it * 100).toInt()} ms" },
                    accentColor = accentRed,
                    backgroundColor = knobBackground,
                    borderColor = knobBorder,
                    textPrimary = textPrimary,
                    textSecondary = textSecondary
                )
                Spacer(modifier = Modifier
                    .width(24.dp))

                BassBoostKnob(
                    label = "Release",
                    value = viewModel.release,
                    onValueChange = { viewModel.onReleaseChange(it) },
                    onValueChangeFinished = { },
                    valueText = { "${(it * 500).toInt()} ms" },
                    accentColor = accentRed,
                    backgroundColor = knobBackground,
                    borderColor = knobBorder,
                    textPrimary = textPrimary,
                    textSecondary = textSecondary
                )
                Spacer(modifier = Modifier
                    .width(24.dp))

                BassBoostKnob(
                    label = "Knee",
                    value = viewModel.knee,
                    onValueChange = { viewModel.onKneeChange(it) },
                    onValueChangeFinished = { },
                    valueText = { "${(it * 10).toInt()} dB" },
                    accentColor = accentRed,
                    backgroundColor = knobBackground,
                    borderColor = knobBorder,
                    textPrimary = textPrimary,
                    textSecondary = textSecondary
                )
                Spacer(modifier = Modifier
                    .width(24.dp))

                BassBoostKnob(
                    label = "Makeup",
                    value = viewModel.makeup,
                    onValueChange = { viewModel.onMakeupChange(it) },
                    onValueChangeFinished = { },
                    valueText = { "${(it * 40).toInt()} dB" },
                    accentColor = accentRed,
                    backgroundColor = knobBackground,
                    borderColor = knobBorder,
                    textPrimary = textPrimary,
                    textSecondary = textSecondary
                )
                Spacer(modifier = Modifier
                    .width(24.dp))

                BypassButton(
                    activo = viewModel.bypass,
                    onCambiar = { viewModel.toggleBypass(it) },
                    onCambioFin = { },
                    accentColor = accentRed,
                    backgroundColor = knobBackground,
                    borderColor = knobBorder,
                    textPrimary = textPrimary,
                    textSecondary = textSecondary
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PantallaPrincipal()
}