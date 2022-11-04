/***
 * Author         : Dicky Zulkarnain
 * Date           : 01/11/22
 * Original File  : CastView
 ***/

package com.dickiez.movee.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage


@Composable
fun CastView(imageUrl: String, name: String, modifier: Modifier = Modifier) {
  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = modifier.width(60.dp)
  ) {
    AsyncImage(
      model = imageUrl,
      contentDescription = null,
      contentScale = ContentScale.Crop,
      modifier = Modifier.clip(shape = CircleShape)
        .size(50.dp)
    )
    Text(text = name, color = Color.White, textAlign = TextAlign.Center)
  }
}

@Preview
@Composable
fun CastViewPreview() {
  CastView(imageUrl = "https://image.tmdb.org/t/p/w500/jwd0XXuc4ibXAXjOxmhsFP0fQEO.jpg", name = "Amantha Srikandi")
}