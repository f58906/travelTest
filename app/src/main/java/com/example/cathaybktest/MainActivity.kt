package com.example.cathaybktest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cathaybktest.adapter.AttractionAdapter

import com.example.cathaybktest.viewmodels.AttractionViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var attractionViewModel: AttractionViewModel
    private lateinit var attractionAdapter: AttractionAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val languageButton = findViewById<TextView>(R.id.language)
        // 初始化 ViewModel
        attractionViewModel = ViewModelProvider(this).get(AttractionViewModel::class.java)



        languageButton.setOnClickListener {
            showLanguageDialog()
        }
        // 初始化 RecyclerView
        attractionAdapter = AttractionAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = attractionAdapter

        // 觀察 ViewModel 中的 LiveData
        attractionViewModel.attractionsLiveData.observe(this, Observer { attractions ->
            // 更新 RecyclerView 數據
            attractionAdapter.submitList(attractions)
            attractionAdapter.notifyDataSetChanged()
        })
        //抓取API
        attractionViewModel.getAttractions(1, "zh-tw")
    }


    private fun showLanguageDialog() {
        val dialogView = layoutInflater.inflate(R.layout.language_selection_dialog, null)
        val languageRadioGroup = dialogView.findViewById<RadioGroup>(R.id.languageRadioGroup)
        val selectLanguageButton = dialogView.findViewById<Button>(R.id.selectLanguageButton)

        val builder = AlertDialog.Builder(this)
        builder.setView(dialogView)
        val dialog = builder.create()
        var languageText = "zh-tw"

        selectLanguageButton.setOnClickListener {
            val checkedRadioButtonId = languageRadioGroup.checkedRadioButtonId
            Log.d("RadioButtonTest", "checkedRadioButtonId: $checkedRadioButtonId")

            when (checkedRadioButtonId) {
                R.id.radioButtonZhTw -> {
                    languageText = "zh-tw"
                }
                R.id.radioButtonZhCn -> {
                    languageText = "zh-cn"
                }
                R.id.radioButtonEn -> {
                    languageText = "en"
                }
                R.id.radioButtonJa -> {
                    languageText = "ja"
                }
                R.id.radioButtonKo -> {
                    languageText = "ko"
                }
                R.id.radioButtonEs -> {
                    languageText = "es"
                }
                R.id.radioButtonId-> {
                    languageText = "id"
                }
                R.id.radioButtonTh-> {
                    languageText = "th"
                }
                R.id.radioButtonVi-> {
                    languageText = "vi"
                }
            }
            attractionViewModel.getAttractions(1,languageText)
            dialog.dismiss()
        }

        dialog.show()
    }
}