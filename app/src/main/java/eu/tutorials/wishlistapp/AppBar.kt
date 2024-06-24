package eu.tutorials.wishlistapp

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import eu.tutorials.wishlistapp.R

@Composable
fun AppBarView(
    title: String,
    onBackNavClicked: () -> Unit = {}
) {


    val navigationIcon:(@Composable () -> Unit )?={
        IconButton(onClick = { onBackNavClicked() }) {
            if (title.contains("WISHLIST")) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    tint = Color.White,
                    contentDescription = null
                )

            }
            else{
                null
            }
        }

    }
    TopAppBar(
        title = {
            Text(
                text = title,
                color = Color.Black,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .heightIn(max = 24.dp)
            )
        },
        backgroundColor = colorResource(id = R.color.app_bar_color),
        elevation = 3.dp,
        navigationIcon = navigationIcon
    )
}
