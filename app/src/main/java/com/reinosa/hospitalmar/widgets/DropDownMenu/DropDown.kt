import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.reinosa.hospitalmar.Model.DataClass.Competencia
import com.reinosa.hospitalmar.ViewModel.ViewModel
import com.reinosa.hospitalmar.widgets.Competencias.CompetenciasItem

@Composable
fun DropdownMenuWidgett(
    navController: NavController,
    viewModel: ViewModel,
    onItemSelected: (Competencia) -> Unit,
    modifier: Modifier = Modifier
) {
    val competenciaList = viewModel.competenciaList.value

    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf<Competencia?>(null) }
    viewModel.updateText(viewModel.competenciaSelected?.nombreCompetencia)
    viewModel.updateCompetenciaInfo(viewModel.competenciaSelected)
    Column(modifier) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = true }
            .padding(16.dp)
            .background(Color.White)
            .clickable { expanded = true } // Esta línea abre el menú desplegable

        ) {
            Text(text = selectedItem?.nombreCompetencia ?: "Selecciona una Competencia",
                style = MaterialTheme.typography.body1,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clip(RoundedCornerShape(16.dp))  // Redondea las esquinas con un radio de 16.dp
                    .background(Color.White)  // Establece el color de fondo
                    .border(2.dp, Color.Blue, RoundedCornerShape(16.dp))  // Agrega un borde azul
                    .padding(16.dp))
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            if (competenciaList != null) {
                competenciaList.forEach { item ->
                    DropdownMenuItem(onClick = {
                        selectedItem = item
                        expanded = false
                        onItemSelected(item)
                    }) {
                        Text(text = item.nombreCompetencia)
                    }
                }
            }
        }
    }
}