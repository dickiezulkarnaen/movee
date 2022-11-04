/***
 * Author         : Dicky Zulkarnain
 * Date           : 21/10/22
 * Original File  : MovieCard
 ***/

package com.dickiez.movee.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dickiez.core.constants.MovieCategory
import com.dickiez.movee.ui.theme.MoveeTheme


@Composable
fun MovieCard(posterPath : String, onClick: () -> Unit = {}) {
  Card(modifier = Modifier.padding(5.dp)) {
    AsyncImage(
      model = posterPath,
      contentDescription = "Card",
      modifier = Modifier
        .background(color = Color.Black)
        .size(width = 130.dp, height = 180.dp)
        .clickable {
          onClick.invoke()
        },
      contentScale = ContentScale.Crop,
    )
  }
}

@Composable
fun SeeAllCard(onClick: () -> Unit) {
  Card(modifier = Modifier.padding(5.dp)) {
    Box(modifier = Modifier
      .background(color = Color.Black)
      .size(width = 130.dp, height = 180.dp),
      contentAlignment = Alignment.Center
    ) {
      Text(
        "See All",
        color = Color.White,
        textAlign = TextAlign.Center,
        modifier = Modifier
          .clickable {
            onClick.invoke()
          },
      )
    }
  }
}

@Preview(showBackground = true)
@Composable
fun MovieCardPreview() {
  MoveeTheme {
    MovieCard("https://image.tmdb.org/t/p/w300/ffX0TL3uKerLXACkuZGWhAPMbAq.jpg") {

    }
  }
}