package com.mobile.venky.kmm.android.poc.ui.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mobile.venky.kmm.android.poc.R
import com.skydoves.landscapist.coil.CoilImage


/**
 * @author Venky Maganahalli updated on 08/29/2021
 */

@Composable
fun ToolbarWidget(title: String, icon: ImageVector, iconClickAction: () -> Unit) {
    TopAppBar(
        // in below line we are
        // adding title to our top bar.
        title = {
            // inside title we are
            // adding text to our toolbar.
            Text(
                text = title,
                // below line is use
                // to give text color.
                color = Color.White
            )
        },
        navigationIcon = {
            // navigation icon is use
            // for drawer icon.
            IconButton(onClick = { iconClickAction.invoke() }) {
                // below line is use to
                // specify navigation icon.
                Icon(icon, "content description")
            }
        },
        // below line is use to give background color
        backgroundColor = colorResource(id = R.color.purple_200),

        // content color is use to give
        // color to our content in our toolbar.
        contentColor = Color.White,

        // below line is use to give
        // elevation to our toolbar.
        elevation = 12.dp
    )
}



@Composable
fun CategoryPicture(resourceId: String, imageSize: Dp) {

    Card(
        shape = CircleShape,
        border = BorderStroke(
            width = 4.dp,
            color = Color.Green
        ),
        modifier = Modifier
            .size(imageSize)
            .padding(8.dp),
        elevation = 4.dp
    ) {

        CoilImage(
            imageModel = resourceId,

            // Crop, Fit, Inside, FillHeight, FillWidth, None
            contentScale = ContentScale.Crop,
            // shows a placeholder ImageBitmap when loading.
            placeHolder = ImageVector.vectorResource(id = R.drawable.ic_launcher_background),
            // shows an error ImageBitmap when the request failed.
            error = ImageVector.vectorResource(id = R.drawable.ic_launcher_background)

        )


    }

}

@Composable
fun CategoryContent(name: String, alignment: Alignment.Horizontal) {
    Column(
        modifier = Modifier
            .padding(8.dp),
        horizontalAlignment = alignment
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.h5
        )

    }

}

