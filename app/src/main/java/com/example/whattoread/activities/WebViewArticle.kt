package com.example.whattoread.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.whattoread.R
import com.example.whattoread.model.Article

class WebViewArticle : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view_article)

        val wvArticleContents = findViewById<WebView>(R.id.wvArticleContents)

        val article = intent.getParcelableExtra<Article>("article")

        wvArticleContents.apply {
            settings.loadsImagesAutomatically = true
            settings.javaScriptEnabled = true
            scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
            webViewClient = WebViewClient()
            loadUrl(article!!.web_url)
        }
        actionBar?.title  = article?.headline!!.toString()

    }
}