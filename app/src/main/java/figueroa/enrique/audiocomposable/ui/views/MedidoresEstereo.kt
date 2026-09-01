package figueroa.enrique.audiocomposable.ui.views

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MedidoresEstereo(valorIzquierdo: Float, valorDerecho: Float) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
    ) {
        VuMeter(
            valorDb = valorIzquierdo,
            modifier = Modifier
                .weight(1f)
                .padding(4.dp)
        )
        VuMeter(
            valorDb = valorDerecho,
            modifier = Modifier
                .weight(1f)
                .padding(4.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MedidoresEstereoPreview() {
    MedidoresEstereo(valorIzquierdo = 1f, valorDerecho = -3f)
}