package com.example.whattoread

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide




// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
class ArticleAdapter(val mArticles:List<Article>?): RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    lateinit var mContext:Context

    class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        val articleImageview = itemView.findViewById<ImageView>(R.id.ivArticleImage)
        val articleHeadline = itemView.findViewById<TextView>(R.id.tvHeadLine)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context

        var itemView = LayoutInflater.from(mContext).inflate(
                R.layout.article,
                parent,
                false,
            )

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get the data from the model based on position
        val article:Article? = mArticles?.get(position)

        //setup views with new data
        val ivArticleImage = holder.articleImageview
        if (article?.multimedia!=null && article?.multimedia.isNotEmpty())
            Glide.with(holder.itemView.context)
                .load("https://static01.nyt.com/"+article.multimedia[0].url)
                .override(400,500)
                .placeholder(R.drawable.ic_launcher_background)
                .into(ivArticleImage)
        else
            Glide.with(holder.itemView.context)
                .load(R.drawable.ic_launcher_background)
                .override(400,500)
                .placeholder(R.drawable.ic_launcher_background)
                .into(ivArticleImage)
        //show headline
        val tvHeadLineView = holder.articleHeadline
        tvHeadLineView.setText(article?.headline?.main)

        holder.itemView.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v:View){
                val intent = Intent(mContext, WebViewArticle::class.java)
                intent.putExtra("article", article)
                mContext.startActivity(intent)
            }
        })
    }

    override fun getItemCount(): Int {
        return mArticles!!.size
    }
}
