package com.example.quizkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.quizkotlin.databinding.ActivityLoginBinding
import com.example.quizkotlin.models.Student
import com.example.quizkotlin.viewmodels.StudentViewModel
import com.example.quizkotlin.viewmodels.TeacherViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private var emailAddress : String = ""
    private var password : String = ""
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    private lateinit var studentViewModel: StudentViewModel
    private lateinit var teacherViewModel: TeacherViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)

        binding.signIn.setOnClickListener {
            emailAddress = binding.emailAddress.text.toString()
            password = binding.password.text.toString()
            if (binding.student.isChecked){
                studentViewModel = ViewModelProviders.of(this).get(StudentViewModel::class.java)
                if (emailAddress.matches(emailPattern.toRegex())) {
                    if (studentViewModel.checkStudent(emailAddress, password)) {
                        emptyInputs()
                        val intent: Intent = Intent(this, StudentHomeActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            this,
                            "Please register first!!",
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }
                }else{
                    Toast.makeText(this,
                    "Invalid email",
                    Toast.LENGTH_LONG)
                        .show()
                }
            }else if(binding.teacher.isChecked){
                teacherViewModel = ViewModelProviders.of(this)
                    .get(TeacherViewModel::class.java)
                if (emailAddress.matches(emailPattern.toRegex())) {
                    if (teacherViewModel.checkTeacher(emailAddress, password)) {
                        emptyInputs()
                        startActivity(Intent(this, TeacherHomeActivity::class.java))
                    } else {
                        Toast.makeText(
                            this,
                            "Please register first!!",
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }
                }else{
                    Toast.makeText(this,
                        "Invalid email",
                        Toast.LENGTH_LONG)
                        .show()
                }
            }else{
                Toast.makeText(this,
                "Select any one option please!!",
                Toast.LENGTH_LONG)
                    .show()
            }
        }

        binding.register.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }
    }

    private fun emptyInputs(){
        binding.emailAddress.text = null
        binding.password.text = null
    }
}