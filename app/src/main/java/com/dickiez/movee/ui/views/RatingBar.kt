/***
 * Author         : Dicky Zulkarnain
 * Date           : 01/11/22
 * Original File  : RatingBar
 ***/

package com.dickiez.movee.ui.views

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlin.math.ceil
import kotlin.math.floor

@Composable
fun RatingBar(
  modifier: Modifier = Modifier,
  rating: Double = 0.0,
  stars: Int = 5,
) {

  val filledStars = floor(rating).toInt()
  val unfilledStars = (stars - ceil(rating)).toInt()
  val halfStar = !(rating.rem(1).equals(0.0))

  Row(modifier = modifier) {
    repeat(filledStars) {
      FullFilledStarIcon()
    }

    if (halfStar) {
      HalfFilledStarIcon()
    }

    repeat(unfilledStars) {
      UnfilledStar()
    }
  }
}


@Preview
@Composable
fun RatingPreview() {
  RatingBar(rating = 2.5)
}

@Preview
@Composable
fun TenStarsRatingPreview() {
  RatingBar(stars = 10, rating = 8.5)
}

@Preview
@Composable
fun RatingPreviewFull() {
  RatingBar(rating = 5.0)
}

@Preview
@Composable
fun RatingPreviewWorst() {
  RatingBar(rating = 1.0)
}

@Preview
@Composable
fun RatingPreviewDisabled() {
  RatingBar(rating = 0.0)
}