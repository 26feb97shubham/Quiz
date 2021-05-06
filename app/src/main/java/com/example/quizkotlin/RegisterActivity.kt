package com.example.quizkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.quizkotlin.databinding.ActivityRegisterBinding
import com.example.quizkotlin.models.Student
import com.example.quizkotlin.models.Teacher
import com.example.quizkotlin.viewmodels.StudentViewModel
import com.example.quizkotlin.viewmodels.TeacherViewModel

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding
    private var emailAddress : String = ""
    private var password : String = ""
    private var name : String = ""
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    private lateinit var studentViewModel: StudentViewModel
    private lateinit var teacherViewModel: TeacherViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_register)
        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel::class.java)
        teacherViewModel = ViewModelProviders.of(this).get(TeacherViewModel::class.java)
        binding.signIn.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }
        binding.register.setOnClickListener {
            emailAddress = binding.emailAddress.text.toString()
            password = binding.password.text.toString()
            name = binding.name.text.toString()
            if (binding.student.isChecked){
                if (!emailAddress.isEmpty() && emailAddress.matches(emailPattern.toRegex())){
                    if (!studentViewModel.checkStudent(emailAddress)) {
                        studentViewModel.insertStudent(Student(0,name,emailAddress,password))
                        emptyInputs()
                        val intent: Intent = Intent(this, StudentHomeActivity::class.java)
                        startActivity(intent)
                    } else {
                        emptyInputs()
                        Toast.makeText(
                            this,
                            "Email already exists!! Please Login to continue",
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }
                }else{
                    emptyInputs()
                    Toast.makeText(this,
                    "Invalid Email",
                    Toast.LENGTH_LONG)
                        .show()
                }
            }else if (binding.teacher.isChecked){
                teacherViewModel = ViewModelProviders.of(this)
                    .get(TeacherViewModel::class.java)
                if (emailAddress.matches(emailPattern.toRegex())){
                    if (!teacherViewModel.checkTeacher(emailAddress)){
                        teacherViewModel.insertTeacher(Teacher(0,name,emailAddress,password))
                        emptyInputs()
                        startActivity(Intent(this,TeacherHomeActivity::class.java))
                    }else{
                        emptyInputs()
                        Toast.makeText(
                            this,
                            "Email already exists!! Please Login to continue",
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }
                }else{
                    emptyInputs()
                    Toast.makeText(this,
                        "Invalid Email",
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
    }
    private fun emptyInputs(){
        binding.emailAddress.text = null
        binding.password.text = null
        binding.name.text = null
    }
}