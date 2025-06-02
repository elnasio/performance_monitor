package com.mories.performance_monitor.presentation.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppToolbar(
    title: String,
    onBackClick: (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
    paddingStart: Int = 0,
    paddingEnd: Int = 0,
    paddingTop: Int = 0,
    paddingBottom: Int = 0
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                fontSize = 18.sp,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(start = paddingStart.dp, end = paddingEnd.dp),
            )
        },
        navigationIcon = {
            onBackClick?.let {
                IconButton(
                    onClick = it, modifier = Modifier.padding(start = paddingStart.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        }, actions = actions, modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = paddingStart.dp,
                end = paddingEnd.dp,
                top = paddingTop.dp,
                bottom = paddingBottom.dp
            )
    )
}