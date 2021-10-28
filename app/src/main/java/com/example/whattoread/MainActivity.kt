package com.example.whattoread

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var parameters:APIParameters

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupSortOrder()
        val btnSearch = findViewById<Button>(R.id.btn_search)
        btnSearch.setOnClickListener {
            getSettingsParams()
            displayResultsFromApi()
        }
        // https://api.nytimes.com/svc/search/v2/
        // articlesearch.json?
        // begin_date=20160112&
        // sort=oldest&
        //  fq=news_desk:(%22Education%22%20%22Health%22)&
        //  api-key=VSqRE2UoTOiWA989w3qZzn0cki6TptBv
    }

    fun displayResultsFromApi(){
        val intent= Intent(this,ArticleActivity::class.java)
        intent.putExtra("settings",parameters)
        startActivity(intent)
    }

    fun getSettingsParams(){
        val etBeginDate = findViewById<EditText>(R.id.etBeginDate)
        val spSortOrder = findViewById<Spinner>(R.id.spSortOrder)
        val cbNewsDeskVal1 = findViewById<CheckBox>(R.id.cbNewsDeskValue1)
        val cbNewsDeskVal2 = findViewById<CheckBox>(R.id.cbNewsDeskValue2)
        val cbNewsDeskVal3 = findViewById<CheckBox>(R.id.cbNewsDeskValue3)
        var newsDeskValues = arrayOf<String>()
        if (cbNewsDeskVal1.isChecked)
            newsDeskValues.plus("Arts")
        if (cbNewsDeskVal2.isChecked)
            newsDeskValues.plus("Fashion & Style")
        if (cbNewsDeskVal3.isChecked)
            newsDeskValues.plus("Sports")

       // val selectedDate = SimpleDateFormat("yyyymmdd").format(etBeginDate.text)
        parameters= APIParameters("20211010",spSortOrder.selectedItem.toString(),newsDeskValues)
    }

    fun setupSortOrder()
    {
        val spSortOrder = findViewById<Spinner>(R.id.spSortOrder)
        val sortOrderArray = arrayOf("Oldest to Newest","Newest to Oldest")
        val sortOrderAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,sortOrderArray)
        sortOrderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spSortOrder.adapter = sortOrderAdapter
    }
}