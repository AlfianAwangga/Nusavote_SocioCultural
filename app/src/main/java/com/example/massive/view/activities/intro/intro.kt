package com.example.massive.view.activities.intro

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import com.example.massive.R
import com.example.massive.databinding.ActivityIntroBinding
import com.example.massive.view.activities.HomeActivity

private lateinit var binding: ActivityIntroBinding

private val question = arrayOf("Apa Bahasa Jawanya Makan?", "Apa Bahasa Jawanya Tidur?", "Apa Bahasa Jawanya Minum?")

private val option = arrayOf(arrayOf("Mangin","Mangan" ,"Mangun"), arrayOf("Tilem","Modom", "Sleep"),
    arrayOf("minum","drink", "Ngunjuk" ))

private val correct_answer = arrayOf(1,0,2)

private var currentQuestonIndex = 0
private var score = 0

private var benar = 0
private var salah = 0

private var currentProgressBar = 0

class intro : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showQuestion()

        binding.rgAnswer.setOnCheckedChangeListener{ _,_ ->
            binding.btnSelanjutnya1.isEnabled = binding.rbAnswer1.isChecked || binding.rbAnswer2.isChecked
                    || binding.rbAnswer3.isChecked
        }

        binding.rbAnswer1.setOnClickListener(this)
        binding.rbAnswer2.setOnClickListener(this)
        binding.rbAnswer3.setOnClickListener(this)
        binding.btnSelanjutnya1.setOnClickListener(this)
    }

    private fun showQuestion(){
        binding.tvQuestion.text = question[currentQuestonIndex]
        binding.rbAnswer1.text = option[currentQuestonIndex][0]
        binding.rbAnswer2.text = option[currentQuestonIndex][1]
        binding.rbAnswer3.text = option[currentQuestonIndex][2]
    }

    private fun checkAnswer(selectedAnswer : Int){
        val correctAnswerIndex = correct_answer[currentQuestonIndex]

        if (selectedAnswer == correctAnswerIndex){
            score += 33
            binding.result.text = "Selamat Jawaban Anda Benar"
            benar++
            binding.result.setTextColor(Color.GREEN)
        }else{
            binding.result.text = "Yah, Jawaban Anda Salah"
            binding.result.setTextColor(Color.RED)
            salah++
        }

    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.rb_answer_1 -> {
                val selectedOption = binding.rgAnswer.checkedRadioButtonId
                val selectedOptionIndex = binding.rgAnswer.indexOfChild(findViewById(selectedOption))
                checkAnswer(selectedOptionIndex)
                binding.rbAnswer1.isEnabled = false
                binding.rbAnswer2.isEnabled = false
                binding.rbAnswer3.isEnabled = false

            }

            R.id.rb_answer_2 -> {
                val selectedOption = binding.rgAnswer.checkedRadioButtonId
                val selectedOptionIndex = binding.rgAnswer.indexOfChild(findViewById(selectedOption))
                checkAnswer(selectedOptionIndex)
                binding.rbAnswer1.isEnabled = false
                binding.rbAnswer2.isEnabled = false
                binding.rbAnswer3.isEnabled = false
            }

            R.id.rb_answer_3 -> {
                val selectedOption = binding.rgAnswer.checkedRadioButtonId
                val selectedOptionIndex = binding.rgAnswer.indexOfChild(findViewById(selectedOption))
                checkAnswer(selectedOptionIndex)
                binding.rbAnswer1.isEnabled = false
                binding.rbAnswer2.isEnabled = false
                binding.rbAnswer3.isEnabled = false
            }

            R.id.btn_selanjutnya1 -> {

                currentProgressBar += 34
                binding.progressBar.setProgress(currentProgressBar)
                binding.progressBar.max = 100

                currentQuestonIndex++
                if (currentQuestonIndex < question.size){
                    showQuestion()
                }else{
                    val message = "Score Anda $score\nAnda benar $benar dan salah $salah dari 3 soal"
                    showCustomDialogBox(message)
                }

                binding.rbAnswer1.isEnabled = true
                binding.rbAnswer2.isEnabled = true
                binding.rbAnswer3.isEnabled = true
                binding.result.text = null
                binding.rgAnswer.clearCheck()
            }
        }
    }

    private fun showCustomDialogBox(message: String) {
        val dialog  = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_dialog_box)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val ToHome : Button = dialog.findViewById(R.id.toHome)
        val ScoreDesc : TextView = dialog.findViewById(R.id.scoreResult)

        ScoreDesc.text = message

        ToHome.setOnClickListener{
            val intent = Intent(this@intro, HomeActivity::class.java)
            startActivity(intent)
        }
        dialog.show()
    }
}