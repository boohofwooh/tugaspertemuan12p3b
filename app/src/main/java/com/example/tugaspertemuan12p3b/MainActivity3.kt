package com.example.tugaspertemuan12p3b

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.tugaspertemuan12p3b.databinding.ActivityMain3Binding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity3 : AppCompatActivity() {
    private lateinit var mClazzDao: ClazzDao
    private lateinit var executorService: ExecutorService
    private lateinit var binding: ActivityMain3Binding
    private var updateId : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        executorService = Executors.newSingleThreadExecutor()
        val db = ClazzRoomDatabase.getInstance(this)
        mClazzDao = ClazzRoomDatabase.getInstance(this).clazzDao()
        val studentID = intent.getIntExtra("studentID", 0)
        val studentName = intent.getStringExtra("studentName")
        val studentMajor = intent.getStringExtra("studentMajor")
        val studentGpa = intent.getStringExtra("studentGpa")
        val studentClassOf = intent.getStringExtra("studentClassOf")

        Log.d("MainActivity3", "Student ID: $studentID")
        Log.d("MainActivity3", "Student Name: $studentName")
        Log.d("MainActivity3", "Student Major: $studentMajor")
        Log.d("MainActivity3", "Student GPA: $studentGpa")
        Log.d("MainActivity3", "Student Class Of: $studentClassOf")
        with(binding) {
            updateId = studentID
            edtName.setText(studentName)
            edtMajor.setText(studentMajor)
            edtGpa.setText(studentGpa)
            edtClassOf.setText(studentClassOf)
            btnSave.setOnClickListener {
                update(Clazz(
                    id = updateId,
                    name = edtName.text.toString(),
                    major = edtMajor.text.toString(),
                    gpa = edtGpa.text.toString(),
                    class_of = edtClassOf.text.toString()
                ))
                updateId = 0
                finish()
            }
        }
    }

    private fun update(clazz: Clazz){
        executorService.execute {
            mClazzDao.update(clazz) }
    }

}