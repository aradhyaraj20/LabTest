package com.example.labexam

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ArticleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_article)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }.let { /* to avoid unused warning if any */ }

        val tvTitle = findViewById<TextView>(R.id.tvArticleTitle)
        val tvContent = findViewById<TextView>(R.id.tvFullContent)
        val ivArticle = findViewById<ImageView>(R.id.ivArticleImage)
        val btnBack = findViewById<Button>(R.id.btnBack)

        val title = intent.getStringExtra("EXTRA_TITLE") ?: "Article Title"
        val content = intent.getStringExtra("EXTRA_CONTENT") ?: "Full article content goes here..."
        val imageResId = intent.getIntExtra("EXTRA_IMAGE", R.drawable.ic_logo)

        tvTitle.text = title
        tvContent.text = content
        ivArticle.setImageResource(imageResId)

        btnBack.setOnClickListener {
            finish()
        }
    }
}
