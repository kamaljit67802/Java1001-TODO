package com.example.todolist

import android.os.Bundle

import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button

import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var editTextItem: EditText
    private lateinit var buttonAdd: Button
    private lateinit var listViewItems: ListView
    private lateinit var buttonClear: Button

    private lateinit var itemsAdapter: ArrayAdapter<String>
    private val itemsList: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        editTextItem = findViewById(R.id.editTextItem)
        buttonAdd = findViewById(R.id.buttonAdd)
        listViewItems = findViewById(R.id.listViewItems)
        buttonClear = findViewById(R.id.buttonClear)

        // Initialize adapter
        itemsAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, itemsList)
        listViewItems.adapter = itemsAdapter

        // Add button click listener
        buttonAdd.setOnClickListener {
            val newItem = editTextItem.text.toString()
            if (newItem.isNotEmpty()) {
                itemsList.add(newItem)
                itemsAdapter.notifyDataSetChanged()
                editTextItem.text.clear()
            }
        }

        // Delete button click listener
        buttonClear.setOnClickListener {
            val checkedItemPositions = listViewItems.checkedItemPositions
            for (i in itemsList.size - 1 downTo 0) {
                if (checkedItemPositions.get(i)) {
                    itemsList.removeAt(i)
                }
            }
            listViewItems.clearChoices()
            itemsAdapter.notifyDataSetChanged()
        }

        // List item click listener
        listViewItems.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                listViewItems.setItemChecked(position, true)
            }
    }
}
