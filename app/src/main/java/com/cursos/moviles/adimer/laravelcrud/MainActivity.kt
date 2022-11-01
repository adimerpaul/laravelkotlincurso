package com.cursos.moviles.adimer.laravelcrud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.cursos.moviles.adimer.laravelcrud.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private val TAG: String?="MYTAG"
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UserAdapter
    private val usersList = mutableListOf<User>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        users()
        binding.actualizar.setOnClickListener {
            users()
        }
        binding.crear.setOnClickListener {
            var intent=Intent(this,CrearActivity::class.java)
            startActivity(intent)
        }
    }

    private fun users() {
        CoroutineScope(Dispatchers.IO).launch {
            val call= getRetrofit().create(APIService::class.java).getUsers()
            val result=call.body()
            runOnUiThread {
                if (call.isSuccessful){
                    val users=result?: emptyList()
                    usersList.clear()
                    usersList.addAll(users)
                    adapter.notifyDataSetChanged()
                    Log.e(TAG, users.toString())
                }else{
                    Toast.makeText(this@MainActivity,"Error",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initRecyclerView() {
        adapter=UserAdapter(usersList)
        adapter.onItemClick={
            var intent=Intent(this,ActualizarActivity::class.java)
            intent.putExtra("id",it.id)
            intent.putExtra("name",it.name)
            intent.putExtra("email",it.email)
            startActivity(intent)
        }
        binding.rvUsers.layoutManager= LinearLayoutManager(this)
        binding.rvUsers.adapter=adapter
    }

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.1.2:8000/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}