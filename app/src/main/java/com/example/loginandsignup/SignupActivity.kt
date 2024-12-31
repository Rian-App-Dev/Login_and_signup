package com.example.loginandsignup

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginandsignup.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {
    private val binding: ActivitySignupBinding by lazy{
        ActivitySignupBinding.inflate(layoutInflater)
    }
    
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.signinBtn.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.registerBtn.setOnClickListener{
            val name = binding.yourName.text.toString()
            val email = binding.yourGmail.text.toString()
            val password = binding.yourPassword.text.toString()
            val re_password = binding.reEnterPassword.text.toString()

            if(name.isEmpty()){
                binding.yourName.error = "Please enter all the fields"
                binding.yourName.requestFocus()
                return@setOnClickListener
            }
            else if(email.isEmpty()){
                binding.yourGmail.error = "Please enter all the fields"
                binding.yourGmail.requestFocus()
                return@setOnClickListener
            }
            else if(password.isEmpty()){
                binding.yourPassword.error = "Please enter all the fields"
                binding.yourPassword.requestFocus()
                return@setOnClickListener
            }
            else if(re_password.isEmpty()){
                binding.reEnterPassword.error = "Please enter all the fields"
                binding.reEnterPassword.requestFocus()
                return@setOnClickListener
            }

            else{
                if(password.length < 6){
                    binding.yourPassword.error = "Password should be at least 6 characters"
                    binding.yourPassword.requestFocus()
                    return@setOnClickListener
                }
                else if(password != re_password){
                    binding.yourPassword.error = "Password does not match"
                    binding.reEnterPassword.error = "Password does not match"
                    return@setOnClickListener
                }
                else{
                    auth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(this){
                            if(it.isSuccessful){
                                Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this, LoginActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                            else{
                                Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                            }
                        }
                }
            }
        }
    }
}