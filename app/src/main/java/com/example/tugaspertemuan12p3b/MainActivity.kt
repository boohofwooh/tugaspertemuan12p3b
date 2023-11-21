package com.example.tugaspertemuan12p3b

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tugaspertemuan12p3b.databinding.ActivityMainBinding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class MainActivity : AppCompatActivity() {
    private lateinit var mClazzDao: ClazzDao
    private lateinit var executorService: ExecutorService
    private lateinit var binding: ActivityMainBinding
    private lateinit var clazzAdapter: ClazzAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        executorService = Executors.newSingleThreadExecutor()

        val db = ClazzRoomDatabase.getInstance(this)
        mClazzDao = db.clazzDao()!!

        with(binding) {
            btnAdd.setOnClickListener {
                val intentToSecondActivity =
                    Intent(this@MainActivity,MainActivity2::class.java)
                startActivity(intentToSecondActivity)
            }
            clazzAdapter = ClazzAdapter(onClickClass = {

            }, onDelete = { clazz ->
                delete(clazz)
            }, onEdit = {clazz ->
                val intentToThirdActivity =
                    Intent(this@MainActivity,MainActivity3::class.java)
                intentToThirdActivity.putExtra("studentID", clazz.id)
                intentToThirdActivity.putExtra("studentName", clazz.name)
                intentToThirdActivity.putExtra("studentMajor", clazz.major)
                intentToThirdActivity.putExtra("studentGpa", clazz.gpa)
                intentToThirdActivity.putExtra("studentClassOf", clazz.class_of)

                startActivity(intentToThirdActivity)
            })

            rvClass.layoutManager = LinearLayoutManager(this@MainActivity)
            rvClass.adapter = clazzAdapter

        }
    }

    override fun onResume() {
        super.onResume()
        getAllClasses()
    }

    private fun getAllClasses() {
        mClazzDao.allClasses.observe(this) { clazzList ->
            clazzAdapter.submitList(clazzList)
        }
    }

    private fun update(clazz: Clazz){
        executorService.execute {
            mClazzDao.update(clazz) }
    }

    private fun delete(clazz: Clazz){
        executorService.execute {
            mClazzDao.delete(clazz)
        }
    }

}
