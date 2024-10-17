package com.example.todoapp

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var dbHelper: SQLiteHelper
    private lateinit var taskAdapter: ArrayAdapter<String>
    private lateinit var taskList: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = SQLiteHelper(this)
        taskList = mutableListOf()

        val etTask = findViewById<EditText>(R.id.etTask)
        val btnAddTask = findViewById<Button>(R.id.btnAddTask)
        val lvTasks = findViewById<ListView>(R.id.lvTasks)

        // Initialize the adapter and set it to the ListView
        taskAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, taskList)
        lvTasks.adapter = taskAdapter

        // Add task button click listener
        btnAddTask.setOnClickListener {
            val taskName = etTask.text.toString()
            if (taskName.isNotEmpty()) {
                dbHelper.addTask(taskName) // Save task name as a plain string
                taskList.clear()
                taskList.addAll(dbHelper.getAllTasks()) // Load tasks
                taskAdapter.notifyDataSetChanged()
                etTask.text.clear()
            } else {
                Toast.makeText(this, "Please enter a task", Toast.LENGTH_SHORT).show()
            }
        }

        // Load tasks when the activity is created
        loadTasks()
    }

    private fun loadTasks() {
        taskList.addAll(dbHelper.getAllTasks())
        taskAdapter.notifyDataSetChanged()
    }
}
