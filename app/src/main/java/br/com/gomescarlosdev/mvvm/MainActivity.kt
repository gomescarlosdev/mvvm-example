package br.com.gomescarlosdev.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import br.com.gomescarlosdev.mvvm.databinding.ActivityMainBinding
import br.com.gomescarlosdev.mvvm.view_model.MainViewModel

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.welcome().observe(this) {
            binding.textWelcome.text = it
        }

        binding.buttonLogin.setOnClickListener(this)

        setObserver()
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.button_login -> {
                val email = binding.editEmail.text.toString()
                val password = binding.editPassword.text.toString()

                viewModel.doLogin(email, password)
            }
        }
    }

    private fun setObserver() {
        viewModel.welcome().observe(this) {
            binding.textWelcome.text = it
        }
        viewModel.login().observe(this) {
            if(it)
                Toast.makeText(applicationContext, "Success!", Toast.LENGTH_LONG).show()
            else
                Toast.makeText(applicationContext, "Failed!", Toast.LENGTH_LONG).show()
        }
    }

}