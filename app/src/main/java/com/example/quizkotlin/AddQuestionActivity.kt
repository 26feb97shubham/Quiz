package com.example.quizkotlin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.quizkotlin.constants.QUESTION_SET_A
import com.example.quizkotlin.constants.QUESTION_SET_B
import com.example.quizkotlin.constants.QUESTION_SET_C
import com.example.quizkotlin.databinding.ActivityAddQuestionBinding
import com.example.quizkotlin.models.Question
import com.example.quizkotlin.viewmodels.QuestionViewModel
import com.example.quizkotlin.viewmodels.TeacherViewModel

class AddQuestionActivity : AppCompatActivity(), OnItemSelectedListener {
    private lateinit var binding : ActivityAddQuestionBinding
    private var questionSet : String = ""
    private var subjectName : String = ""
    private var isValidation : Boolean = false
    private lateinit var questionsList: List<Question>
    private lateinit var questionSetList : ArrayList<String>
    private lateinit var questionViewModel: QuestionViewModel
    private lateinit var questionSetAdapter : ArrayAdapter<String>
    var qid : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_question)
        val intent = intent.extras
        subjectName = intent!!.getString(constants.SUBJECT_NAME).toString()

        questionSetList = ArrayList<String>()
        questionSetList.add(QUESTION_SET_A)
        questionSetList.add(QUESTION_SET_B)
        questionSetList.add(QUESTION_SET_C)
        questionSetAdapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_item,
            questionSetList
        )
        questionSetAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.questionSet.adapter = questionSetAdapter
        binding.questionSet.onItemSelectedListener = this

        var question : String = binding.question.text.toString().trim()
        var optionA : String = binding.optionA.text.toString().trim()
        var optionB : String = binding.optionB.text.toString().trim()
        var optionC : String = binding.optionC.text.toString().trim()
        var optionD : String = binding.optionD.text.toString().trim()
        var correctAnswer = binding.correctAnswer.text.toString().trim()


        binding.submitQuestion.setOnClickListener {

            var question : String = binding.question.text.toString().trim()
            var optionA : String = binding.optionA.text.toString().trim()
            var optionB : String = binding.optionB.text.toString().trim()
            var optionC : String = binding.optionC.text.toString().trim()
            var optionD : String = binding.optionD.text.toString().trim()
            var correctAnswer = binding.correctAnswer.text.toString().trim()

            val q : String = question
            val op1 : String = optionA
            val op2 : String = optionB
            val op3 : String = optionC
            val op4 : String = optionD
            val answer : String = correctAnswer
            validations(q, questionSet, op1, op2, op3, op4, answer)
            if (isValidation == true){
                questionsList = ArrayList<Question>()
                (questionsList as ArrayList<Question>).add(Question(qid,
                    subjectName,
                    questionSet,
                q,
                op1,
                op2,
                op3,
                    op4,
                answer))
                qid++
                if (questionsList.size>9){
                    Toast.makeText(
                        this,
                        "One Set can have max 10 questions only",
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(Intent(applicationContext, TeacherHomeActivity::class.java))
                }else{
                    val builder = AlertDialog.Builder(this)
                    val viewGroup = findViewById<ViewGroup>(android.R.id.content)
                    val dialogView = LayoutInflater.from(it.getContext())
                        .inflate(
                            R.layout.custom_dialog_layout_1,
                            viewGroup,
                            false
                        )
                    builder.setView(dialogView)
                    val dialog = builder.create()
                    dialog.show()
                    val textView = dialogView.findViewById<TextView>(R.id.text)
                    textView.text =
                        "Question Added Successfully! Would you like to add more, if yes then continue else click anywhere on the screen to dismiss the dialog box"
                    val continueBtn: AppCompatButton = dialogView.findViewById(R.id.continue_dialog_1)
                    continueBtn.setOnClickListener {
                        Log.e("Click", "Click Pressed")
                        questionViewModel = ViewModelProviders.of(this)
                            .get(QuestionViewModel::class.java)
                        questionViewModel.insertQuestion(Question(0,
                        subjectName,
                        questionSet,
                        q,
                        op1,
                        op2,
                        op3,
                        op4,
                        answer))
                        emptyInputs()
                        dialog.dismiss()
                    }
                }
            }else{
                Toast.makeText(
                    this,
                    "Something is missing!!! Please check",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        questionSet = p0!!.getItemAtPosition(p2).toString()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    private fun validations(
        question: String,
        question_set: String,
        optionA: String,
        optionB: String,
        optionC: String,
        optionD: String,
        correctAnswer: String
    ) {
        if (question_set != "" && !question_set.isEmpty()) {
            if (question != "" && !question.isEmpty()) {
                if (optionA != "" && !optionA.isEmpty()) {
                    if (optionB != "" && !optionB.isEmpty()) {
                        if (optionC != "" && !optionC.isEmpty()) {
                            if (optionD != "" && !optionD.isEmpty()) {
                                if (correctAnswer != "" && !correctAnswer.isEmpty()) {
                                    isValidation = true
                                    return
                                } else {
                                    binding.correctAnswer.setError("Please enter correct answer")
                                    binding.correctAnswer.requestFocus()
                                    isValidation = false
                                    return
                                }
                            } else {
                                binding.optionD.setError("Please enter option D")
                                binding.optionD.requestFocus()
                                isValidation = false
                                return
                            }
                        } else {
                            binding.optionC.setError("Please enter option C")
                            binding.optionC.requestFocus()
                            isValidation = false
                            return
                        }
                    } else {
                        binding.optionB.setError("Please enter option B")
                        binding.optionB.requestFocus()
                        isValidation = false
                        return
                    }
                } else {
                    binding.optionA.setError("Please enter option A")
                    binding.optionA.requestFocus()
                    isValidation = false
                    return
                }
            } else {
                binding.question.setError("Please enter question")
                binding.question.requestFocus()
                isValidation = false
                return
            }
        } else {
            Toast.makeText(
                this,
                "Select exactly one option",
                Toast.LENGTH_LONG
            )
                .show()
            isValidation = false
            return
        }
    }

    private fun emptyInputs(){
        binding.question.text = null
        binding.optionD.text = null
        binding.optionA.text = null
        binding.optionB.text = null
        binding.optionC.text = null
        binding.correctAnswer.text = null
    }
}