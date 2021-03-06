package com.example.whattoread.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.whattoread.*
import com.example.whattoread.adapter.ArticleAdapter
import com.example.whattoread.api.APIParameters
import com.example.whattoread.model.Article
import com.example.whattoread.viewmodel.ArticleViewModel

class ArticleActivity : AppCompatActivity() {
    private var articleList = mutableListOf<Article>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        val articleAdapter = ArticleAdapter(articleList)
        val rvArticle:RecyclerView = findViewById(R.id.rvArticleRecyclerView)

        rvArticle.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        rvArticle.adapter = articleAdapter

        /*val itemClickListener: ItemClickSupport = ItemClickSupport(rvArticle)

        val itemClickListenerOnClick: ItemClickSupport.OnItemClickListener = object: ItemClickSupport.OnItemClickListener{
            override fun onItemClicked(recyclerView: RecyclerView?, position: Int, v: View?) {
                val intent = Intent(this@ArticleActivity, WebViewArticle::class.java)
                intent.putExtra("article", articleList.get(position))
                startActivity(intent)
            }
        }
        ItemClickSupport.addTo(rvArticle).setOnItemClickListener(itemClickListenerOnClick)
*/
            val params: APIParameters? = intent.getParcelableExtra("settings")
            val queryString:String? = intent.getStringExtra("query")

            val articleViewModel = ViewModelProvider(this).get(ArticleViewModel::class.java)

            articleViewModel.fetchArticles(queryString,params!!.sortOrder,params.date, params.newsDeskValues)

            articleViewModel.getArticleRepository().getArticles().observe(this,{ articleSnapshot ->
                articleList.clear()
                articleList.addAll(articleSnapshot)
                articleAdapter.notifyItemRangeChanged(0,articleAdapter.itemCount)
                articleAdapter.notifyDataSetChanged()
            })
        }

    }
