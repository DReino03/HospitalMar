package com.reinosa.hospitalmar.widgets.Drawer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.reinosa.hospitalmar.Model.AppDrawerItemInfo
import com.reinosa.hospitalmar.Model.DrawerParams


@Composable
fun DrawerItems(navController: NavHostController) {
    for (item in DrawerParams.drawerButtons) {
        DrawerItem(item, navController)
    }
}

@Composable
fun DrawerItem(item: AppDrawerItemInfo<*>, navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(48.dp)
            .clickable {
                navController.navigate(item.route)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = item.drawableId),
            contentDescription = stringResource(id = item.descriptionId),
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = stringResource(id = item.title),
            style = MaterialTheme.typography.body1
        )
    }
}



