package com.example.news.components.detail

import android.view.ViewGroup
import android.webkit.WebView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun DetailScreen(
    url: String,
    modifier: Modifier = Modifier,//primer parametro por default
) {
    Column(modifier = modifier) {
        AndroidView(
            factory = { context ->
                WebView(context).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                }
            },
            modifier = Modifier.fillMaxSize()
        ) { webView ->
            webView.loadUrl(url)
        }
    }
}


@Preview
@Composable
fun DetailScreenPreview() {//fix it 
    DetailScreen(
        url = "https://www.theguardian.com/environment/2023/may/04" +
                "/federal-trade-commission-industry-recycle-regulation"
    )
}