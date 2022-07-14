package com.example.umniygorodhach.presentation.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.*
import androidx.compose.ui.platform.*
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import com.example.umniygorodhach.presentation.ui.components.wheel_bottom_navigation.xCoordinate
import com.example.umniygorodhach.presentation.ui.components.wheel_bottom_navigation.yCoordinate
import kotlin.math.roundToInt

@Composable
fun CustomWheelNavigation(
	modifier: Modifier = Modifier,
	backgroundColor: Color = MaterialTheme.colors.primarySurface,
	contentColor: Color = contentColorFor(backgroundColor),
	//elevation: Dp = BottomNavigationDefaults.Elevation,
	content: @Composable BoxScope.() -> Unit,
) {



	Surface(
		color = Color.Transparent,
		contentColor = contentColor,
		modifier = modifier

		//elevation = elevation,
	) {
		Box(
			modifier = Modifier
				.fillMaxWidth()
				.height(BottomNavigationHeight *1)
				.selectableGroup(),
			content = content

		)

	}
}



@ExperimentalMaterialApi
@Composable
fun CustomWheelNavigationItem(
	stateDirection: Int,
	sizeAppTabs: Int,
	rotationPosition:Float,
	marginDown: MutableState<Dp>,
	sizeNavWidth: Dp,
	sizeNavHeight: Dp,
	indexOfTab: Int,
	selected: Boolean,
	onClick: () -> Unit,
	icon: @Composable () -> Unit,
	modifier: Modifier = Modifier,
	enabled: Boolean = true,
	label: @Composable (() -> Unit)? = null,
	alwaysShowLabel: Boolean = true,
	interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
	selectedContentColor: Color = LocalContentColor.current,
	unselectedContentColor: Color = selectedContentColor.copy(alpha = ContentAlpha.medium),


	) {
	val styledLabel: @Composable (() -> Unit)? = label?.let {
		@Composable {
			Box(
				modifier =Modifier
					.onSizeChanged {
						marginDown.value = (-10).dp
					}
			)
			val style = MaterialTheme.typography.caption.copy(textAlign = TextAlign.Center)
			ProvideTextStyle(style, content = label)
		}
	}

	// The color of the Ripple should always the selected color, as we want to show the color
	// before the item is considered selected, and hence before the new contentColor is
	// provided by BottomNavigationTransition.
	val ripple = rememberRipple(bounded = false, color = selectedContentColor)

	val density = LocalDensity.current

	val sizeItemWidth = remember { mutableStateOf(0.dp) }
	val sizeItemHeight = remember { mutableStateOf(0.dp) }

	var size=sizeAppTabs
	var index = indexOfTab
	if(sizeAppTabs < 4){
		size+=2
		index++
		if(sizeAppTabs == 1){
			size+=2
			index++
		}
	}

	val xCoordinate = xCoordinate(
		stateDirection,
		density,
		rotationPosition,
		sizeNavWidth,
		size,
		index,
		sizeItemWidth.value
	)
	val yCoordinate = yCoordinate(
		(sizeNavHeight - sizeItemHeight.value),
		sizeNavWidth,
		size,
		xCoordinate,
		sizeItemWidth.value,
		marginDown.value,
		sizeNavWidth,

	)

	Box(
		modifier =modifier
			.onSizeChanged {
				sizeItemWidth.value = with(density) {
					it.width.toDp()
				}
				sizeItemHeight.value = with(density) {
					it.height.toDp()
				}
			}
			.offset {
				IntOffset(with(density) {xCoordinate.toPx().roundToInt()}, with(density) {yCoordinate.toPx().roundToInt()})
			}
			.selectable(
				selected = selected,
				onClick = onClick,
				enabled = enabled,
				role = Role.Tab,
				interactionSource = interactionSource,
				indication = ripple
			)
			.wrapContentHeight(Alignment.Bottom)
		,
	) {
		BottomNavigationTransition(
			selectedContentColor,
			unselectedContentColor,
			selected
		) { progress ->
			val animationProgress = if (alwaysShowLabel) 1f else progress

			BottomNavigationItemBaselineLayout(
				icon = icon,
				label = styledLabel,
				iconPositionAnimationProgress = animationProgress
			)
		}
	}
}


@Composable
private fun BottomNavigationTransition(
	activeColor: Color,
	inactiveColor: Color,
	selected: Boolean,
	content: @Composable (animationProgress: Float) -> Unit
) {
	val animationProgress by animateFloatAsState(
		targetValue = if (selected) 1f else 0f,
		animationSpec = BottomNavigationAnimationSpec
	)

	val color = lerp(inactiveColor, activeColor, animationProgress)

	CompositionLocalProvider(
		LocalContentColor provides color.copy(alpha = 1f),
		LocalContentAlpha provides color.alpha,
	) {
		content(animationProgress)
	}
}
@Composable
private fun BottomNavigationItemBaselineLayout(
	icon: @Composable () -> Unit,
	label: @Composable (() -> Unit)?,
	/*@FloatRange(from = 0.0, to = 1.0)*/
	iconPositionAnimationProgress: Float
) {
	Layout(
		{
			Box(Modifier.layoutId("icon")) { icon() }
			if (label != null) {
				Box(
					Modifier
						.layoutId("label")
						.alpha(iconPositionAnimationProgress)
						.padding(horizontal = BottomNavigationItemHorizontalPadding)
				) { label() }
			}
		}
	) { measurables, constraints ->
		val iconPlaceable = measurables.first { it.layoutId == "icon" }.measure(constraints)

		val labelPlaceable = label?.let {
			measurables.first { it.layoutId == "label" }.measure(
				// Measure with loose constraints for height as we don't want the label to take up more
				// space than it needs
				constraints.copy(minHeight = 0)
			)
		}

		// If there is no label, just place the icon.
		if (label == null) {
			placeIcon(iconPlaceable, constraints)
		} else {
			placeLabelAndIcon(
				labelPlaceable!!,
				iconPlaceable,
				constraints,
				iconPositionAnimationProgress
			)
		}
	}
}

/**
 * Places the provided [iconPlaceable] in the vertical center of the provided [constraints]
 */
private fun MeasureScope.placeIcon(
	iconPlaceable: Placeable,
	constraints: Constraints
): MeasureResult {
	val height = iconPlaceable.height
	return layout(iconPlaceable.width, height) {
		iconPlaceable.placeRelative(0, 0)
	}
}
private fun MeasureScope.placeLabelAndIcon(
	labelPlaceable: Placeable,
	iconPlaceable: Placeable,
	constraints: Constraints,
	/*@FloatRange(from = 0.0, to = 1.0)*/
	iconPositionAnimationProgress: Float
): MeasureResult {
	val height = iconPlaceable.height+labelPlaceable.height

	// TODO: consider multiple lines of text here, not really supported by spec but we should
	// have a better strategy than overlapping the icon and label
	val baseline = labelPlaceable[LastBaseline]

	val baselineOffset = CombinedItemTextBaseline.roundToPx()

	// Label should be [baselineOffset] from the bottom
	val labelY =  iconPlaceable.height

	val unselectedIconY = (height - iconPlaceable.height) / 2

	// Icon should be [baselineOffset] from the text baseline, which is itself
	// [baselineOffset] from the bottom
	val selectedIconY = height - (baselineOffset * 2) - iconPlaceable.height

	val containerWidth = kotlin.math.max(labelPlaceable.width, iconPlaceable.width)

	val labelX = (containerWidth - labelPlaceable.width) / 2
	val iconX = (containerWidth - iconPlaceable.width) / 2

	// How far the icon needs to move between unselected and selected states
	val iconDistance = unselectedIconY - selectedIconY

	// When selected the icon is above the unselected position, so we will animate moving
	// downwards from the selected state, so when progress is 1, the total distance is 0, and we
	// are at the selected state.
	val offset = (iconDistance * (1 - iconPositionAnimationProgress)).roundToInt()

	return layout(containerWidth, height) {
		if (iconPositionAnimationProgress != 0f) {
			labelPlaceable.placeRelative(labelX, labelY)
		}
		iconPlaceable.placeRelative(iconX,0)
	}
}

private val BottomNavigationAnimationSpec = TweenSpec<Float>(
	durationMillis = 300,
	easing = FastOutSlowInEasing
)
private val BottomNavigationItemHorizontalPadding = 0.dp

private val BottomNavigationHeight = 94.dp
private val CombinedItemTextBaseline = 12.dp