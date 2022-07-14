package com.example.umniygorodhach.presentation.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AppDropdownMenu(
	modifier: Modifier = Modifier,
	anchor: @Composable ( expandAction: () -> Unit ) -> Unit,
	content: @Composable ( collapseAction: () -> Unit ) -> Unit
) {
	var expanded by remember { mutableStateOf(false) }

	MaterialTheme(shapes = MaterialTheme.shapes.copy(medium = RoundedCornerShape(10.dp))) {
		Box(
			modifier = modifier
		) {
			anchor { expanded = true }
			DropdownMenu(
				expanded = expanded,
				onDismissRequest = { expanded = false }
			) {
				Column {
					content { expanded = false }
				}
			}
		}
	}
}