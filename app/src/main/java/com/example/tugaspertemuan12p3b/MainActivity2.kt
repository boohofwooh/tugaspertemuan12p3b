package com.example.tugaspertemuan12p3b

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tugaspertemuan12p3b.databinding.ActivityMain2Binding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity2 : AppCompatActivity() {
    private lateinit var mClazzDao: ClazzDao
    private lateinit var executorService: ExecutorService
    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        executorService = Executors.newSingleThreadExecutor()
        val db = ClazzRoomDatabase.getInstance(this)
        mClazzDao = ClazzRoomDatabase.getInstance(this).clazzDao()

        with(binding) {
            btnAdd.setOnClickListener {
                insert(Clazz(name = edtName.text.toString(), major = edtMajor.text.toString(), gpa = edtGpa.text.toString(), class_of = edtClassOf.text.toString()))
                finish()
            }
        }
    }

    private fun insert(clazz: Clazz){
        executorService.execute {
            mClazzDao.insert(clazz)
        }
    }

}

