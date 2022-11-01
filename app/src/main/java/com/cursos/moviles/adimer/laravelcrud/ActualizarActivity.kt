package com.cursos.moviles.adimer.laravelcrud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.cursos.moviles.adimer.laravelcrud.databinding.ActivityActualizarBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ActualizarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityActualizarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityActualizarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id=intent.getIntExtra("id",0)
        val name=intent.getStringExtra("name")
        val email=intent.getStringExtra("email")
        binding.etNameUpdate.setText(name)
        binding.etEmailUpdate.setText(email)
        binding.updateButton.setOnClickListener {
            val name=binding.etNameUpdate.text.toString()
            val email=binding.etEmailUpdate.text.toString()
            val user=User(id,name,email)
            CoroutineScope(Dispatchers.IO).launch {
                val call=getRetrofit().create(APIService::class.java).updateUser(id,user)
                runOnUiThread {
                    if (call.isSuccessful){
                        Toast.makeText(this@ActualizarActivity,"Usuario actualizado",Toast.LENGTH_SHORT).show()
                        finish()
                    }else{
                        Toast.makeText(this@ActualizarActivity,"Error al actualizar",Toast.LENGTH_SHORT).show()
                    }
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