/***
 * Author         : Dicky Zulkarnain
 * Date           : 01/11/22
 * Original File  : StarIcon
 ***/

package com.dickiez.movee.ui.views

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.dickiez.movee.R

@Composable
fun FullFilledStarIcon() {
  Icon(
    imageVector = ImageVector.vectorResource(id = R.drawable.ic_star),
    contentDescription = null,
    tint = Color(255, 204, 0),
    modifier = Modifier.size(20.dp, 20.dp)
  )
}

@Composable
fun HalfFilledStarIcon() {
  Icon(
    imageVector = ImageVector.vectorResource(id = R.drawable.ic_half_star),
    contentDescription = null,
    tint = Color(255, 204, 0),
    modifier = Modifier.size(20.dp, 20.dp)
  )
}

@Composable
fun UnfilledStar() {
  Icon(
    imageVector = ImageVector.vectorResource(id = R.drawable.ic_star),
    contentDescription = null,
    tint = Color(217, 217, 217),
    modifier = Modifier.size(20.dp, 20.dp)
  )
}