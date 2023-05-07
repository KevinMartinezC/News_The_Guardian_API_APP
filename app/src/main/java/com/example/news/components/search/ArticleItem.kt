package com.example.news.components.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.news.theme.MyApplicationTheme
import com.example.news.R
import com.example.news.data.network.Article
import com.example.news.data.network.Fields
import java.net.URLEncoder

private const val WEIGHT = 1f

@Composable
fun NewsItem(
    article: Article,
    favoriteArticle: Set<String>,
    onToggleFavorite: (Article) -> Unit,
    navController: NavHostController

) {
    val isFavorite = favoriteArticle.contains(article.id)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.wight_8dp))
            .clickable {
                navController.navigate(
                    "detail/${
                        URLEncoder.encode(
                            article.webUrl,
                            "UTF-8"
                        )
                    }"
                )
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        val painter = rememberAsyncImagePainter(article.fields.thumbnail)
        Image(
            painter = painter,
            contentDescription = stringResource(R.string.new_image),
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.height_100dp))
                .width(dimensionResource(id = R.dimen.wight_100dp))
                .padding(end = dimensionResource(id = R.dimen.padding_16dp)),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.weight(WEIGHT)
        ) {
            Text(
                text = article.webTitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        Icon(
            imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
            contentDescription = stringResource(R.string.favorite_article_icon),
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.size_24))
                .clickable(onClick = {
                    onToggleFavorite(article)
                }
                )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NewsItemPreview() {
    MyApplicationTheme {
        val sampleArticle = Article(
            id = "sample-id",
            type = "sample-type",
            sectionId = "sample-sectionId",
            sectionName = "sample-sectionName",
            webPublicationDate = "2021-09-01T00:00:00Z",
            webTitle = "Sample News Title",
            webUrl = "https://www.example.com",
            apiUrl = "https://www.example.com/sample_api_url",
            fields = Fields(thumbnail = "https://www.example.com/sample_thumbnail.jpg")
        )
        val favoriteArticle = setOf<String>()
        val onToggleFavorite: (Article) -> Unit = {}
        val navController = rememberNavController()

        NewsItem(
            article = sampleArticle,
            favoriteArticle = favoriteArticle,
            onToggleFavorite = onToggleFavorite,
            navController = navController
        )
    }
}