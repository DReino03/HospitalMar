package com.reinosa.hospitalmar.widgets.Login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LabeledCheckbox(
    label: String,
    onCheckChanged: () -> Unit,
    isChecked: Boolean,
    color: Color = Color.White
) {

    Row(

        Modifier
            .clickable(
                onClick = onCheckChanged
            )
            .padding(5.dp),
            horizontalArrangement = Arrangement.Center

    ) {
        Checkbox(checked = isChecked, onCheckedChange = { onCheckChanged() })
        Text(
            label,
            color = color)
    }
}