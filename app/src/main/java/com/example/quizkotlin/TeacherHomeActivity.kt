package com.example.quizkotlin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.databinding.DataBindingUtil
import com.example.quizkotlin.constants.SUBJECT_NAME
import com.example.quizkotlin.databinding.ActivityTeacherHomeBinding

class TeacherHomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityTeacherHomeBinding
    private var subjectName = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_teacher_home)

        binding.addQuiz.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val viewGroup = findViewById<ViewGroup>(android.R.id.content)
            val dialogView = LayoutInflater.from(it.getContext())
                .inflate(
                    R.layout.custom_add_subject_dialog_box,
                    viewGroup,
                    false
                )
            builder.setView(dialogView)
            val dialog = builder.create()
            dialog.show()
            var subject_name = dialogView.findViewById<EditText>(R.id.subject_name)
            var continue_button = dialogView.findViewById<AppCompatButton>(R.id.continue_dialog)
            continue_button.setOnClickListener(View.OnClickListener {
                subjectName = subject_name.getText().toString().trim { it <= ' ' }
                if (subjectName == "" || subjectName.isEmpty()) {
                    subject_name.setError("Please enter Subject Name to continue")
                    subject_name.requestFocus()
                    return@OnClickListener
                } else {
                    val intent = Intent(applicationContext, AddQuestionActivity::class.java)
                    intent.putExtra(SUBJECT_NAME, subjectName)
                    startActivity(intent)
                }
            })
        }

        binding.marksOfQuiz.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val viewGroup = findViewById<ViewGroup>(android.R.id.content)
            val dialogView = LayoutInflater.from(it.getContext())
                .inflate(
                    R.layout.custom_dialog_layout_2,
                    viewGroup,
                    false
                )
            builder.setView(dialogView)
            val dialog = builder.create()
            dialog.show()
            val continueBtn: AppCompatButton = dialogView.findViewById(R.id.continue_dialog)
            continueBtn.setOnClickListener { dialog.dismiss() }
        }

        binding.logOut.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val viewGroup = findViewById<ViewGroup>(android.R.id.content)
            val dialogView = LayoutInflater.from(it.getContext())
                .inflate(
                    R.layout.custom_dialog_layout_3,
                    viewGroup,
                    false
                )
            builder.setView(dialogView)
            val dialog = builder.create()
            dialog.show()
            val log_out: AppCompatButton = dialogView.findViewById(R.id.log_out)
            log_out.setOnClickListener {
                dialog.dismiss()
                startActivity(Intent(applicationContext, LoginActivity::class.java))
            }
        }
    }
}