package com.cursos.moviles.adimer.laravelcrud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.cursos.moviles.adimer.laravelcrud.databinding.ActivityCrearBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CrearActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCrearBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCrearBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.createButton.setOnClickListener {
            val name=binding.etName.text.toString()
            val email=binding.etEmail.text.toString()
            val user=User(1,name,email)
            createUser(user)
        }
    }

    private fun createUser(user: User) {
        CoroutineScope(Dispatchers.IO).launch {
            val call= getRetrofit().create(APIService::class.java).createUser(user)
            val result=call.body()
            runOnUiThread {
                if (call.isSuccessful){
                    Toast.makeText(this@CrearActivity,"Usuario creado",Toast.LENGTH_SHORT).show()
                    finish()
                }else{
                    Toast.makeText(this@CrearActivity,"Error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.1.2:8000/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}