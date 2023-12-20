package com.example.massive.view.activities

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import com.example.massive.R
import com.example.massive.databinding.ActivityQuizBinding
import com.example.massive.view.fragments.BottomSheetScoreFragment

class QuizActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityQuizBinding

    //array soal, pilihan, dan jawaban
    private val question = arrayOf(
        "Dhahar / Mangan",
        "Sampeyan",
        "Ngunjuk / Ngombe",
        "Omah",
        "Turu / Tilem",
        "Mudhun",
        "Lunga",
        "Lali / Kelalen",
        "Munggah",
        "Kula"
    )
    private val options = arrayOf(
        arrayOf("Makan", "Minum", "Main", "Mandi"),
        arrayOf("Aku", "Dia", "Kamu", "Kita"),
        arrayOf("Mandi", "Minum", "Makan", "Main"),
        arrayOf("Sekolah", "Rumah", "Pulang", "Pukul"),
        arrayOf("Tidur", "Berpikir", "Ingat", "Minta"),
        arrayOf("Naik", "Jatuh", "Sakit", "Turun"),
        arrayOf("Bukan", "Datang", "Pulang", "Pergi"),
        arrayOf("Dengar", "Lupa", "Jauh", "Dekat"),
        arrayOf("Turun", "Balik", "Naik", "Depan"),
        arrayOf("Lihat", "Saya", "Ucap", "Masak")
    )
    private val answer = arrayOf(0, 2, 1, 1, 0, 3, 3, 1, 2, 1)

    //index soal saat ini & skor
    private var currentQuestionIndex = 0
    private var score = 0
    private var correct = 0
    private var wrong = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showQuestion()
        binding.ivQuizBack.setOnClickListener(this)
        binding.btnPeriksa.setOnClickListener(this)
        binding.btnLanjut.setOnClickListener(this)
        binding.rgOptions.setOnCheckedChangeListener { _, _ ->
            binding.btnPeriksa.isEnabled =
                binding.rbOption1.isChecked || binding.rbOption2.isChecked
                        || binding.rbOption3.isChecked || binding.rbOption4.isChecked
        }

    }

    private fun showQuestion() {
        binding.tvQuestionKata.text = question[currentQuestionIndex]
        binding.rbOption1.text = options[currentQuestionIndex][0]
        binding.rbOption2.text = options[currentQuestionIndex][1]
        binding.rbOption3.text = options[currentQuestionIndex][2]
        binding.rbOption4.text = options[currentQuestionIndex][3]
    }

    private fun correctOptionColor(index: Int) {
        when (index) {
            0 -> binding.rbOption1.setBackgroundResource(R.drawable.outlined_green)
            1 -> binding.rbOption2.setBackgroundResource(R.drawable.outlined_green)
            2 -> binding.rbOption3.setBackgroundResource(R.drawable.outlined_green)
            3 -> binding.rbOption4.setBackgroundResource(R.drawable.outlined_green)
        }
    }

    private fun wrongOptionColor(index: Int) {
        when (index) {
            0 -> binding.rbOption1.setBackgroundResource(R.drawable.outlined_red)
            1 -> binding.rbOption2.setBackgroundResource(R.drawable.outlined_red)
            2 -> binding.rbOption3.setBackgroundResource(R.drawable.outlined_red)
            3 -> binding.rbOption4.setBackgroundResource(R.drawable.outlined_red)
        }
    }

    private fun ResultColor(index: Int) {
        when (index) {
            0 -> binding.bgResult.setBackgroundColor(resources.getColor(R.color.primary_15))
            1 -> binding.bgResult.setBackgroundColor(resources.getColor(R.color.green))
        }
    }

    private fun checkAnswer(selectedAnswer: Int) {
        val correctAnswerIndex = answer[currentQuestionIndex]

        if (selectedAnswer == correctAnswerIndex) {
            score += 100 / question.size
            correct++
            correctOptionColor(selectedAnswer)
            ResultColor(1)
            binding.btnLanjut.backgroundTintList =
                ColorStateList.valueOf(resources.getColor(R.color.green1))
            binding.tvBenarSalah.text = "Benar! Monggo dilanjut"
            binding.tvBenarSalah.setTextColor(resources.getColor(R.color.green1))
        } else {
            wrong++
            correctOptionColor(correctAnswerIndex)
            wrongOptionColor(selectedAnswer)
            ResultColor(0)
            binding.tvBenarSalah.text = "Waduh, Jawabanmu Kurang Tepat"
            binding.tvBenarSalah.setTextColor(resources.getColor(R.color.primary))
        }

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_periksa -> {
                //button lanjut akan visible dan sebaliknya
                binding.btnLanjut.visibility = View.VISIBLE
                binding.btnPeriksa.visibility = View.INVISIBLE
                binding.tvBenarSalah.visibility = View.VISIBLE

                //progress bar
                binding.pbQuiz.progress += 1
                binding.pbQuiz.max = question.size

                //option disable
                binding.rbOption1.isEnabled = false
                binding.rbOption2.isEnabled = false
                binding.rbOption3.isEnabled = false
                binding.rbOption4.isEnabled = false

                //cek jawaban sesuai option dipilih
                val selectedOption = binding.rgOptions.checkedRadioButtonId
                val selectedOptionIndex =
                    binding.rgOptions.indexOfChild(findViewById(selectedOption))
                checkAnswer(selectedOptionIndex)

                //cek apabila soal telah selesai
                if (currentQuestionIndex == question.size - 1) {
                    binding.btnLanjut.text = "Lihat Skor"
                }
            }

            R.id.btn_lanjut -> {
                //button periksa akan visible dan sebaliknya
                binding.btnLanjut.visibility = View.INVISIBLE
                binding.btnPeriksa.visibility = View.VISIBLE
                binding.tvBenarSalah.visibility = View.INVISIBLE

                //bersihkan option dipilih dan ubah warna jawaban
                binding.rgOptions.clearCheck()
                binding.bgResult.setBackgroundColor(Color.TRANSPARENT)
                binding.rbOption1.setBackgroundResource(R.drawable.selector_outlined_red_white)
                binding.rbOption2.setBackgroundResource(R.drawable.selector_outlined_red_white)
                binding.rbOption3.setBackgroundResource(R.drawable.selector_outlined_red_white)
                binding.rbOption4.setBackgroundResource(R.drawable.selector_outlined_red_white)
                binding.btnLanjut.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.primary))

                //option enable
                binding.rbOption1.isEnabled = true
                binding.rbOption2.isEnabled = true
                binding.rbOption3.isEnabled = true
                binding.rbOption4.isEnabled = true

                //index soal akan bertambah
                currentQuestionIndex++
                if (currentQuestionIndex < question.size) {
                    showQuestion()
                } else {
                    binding.pbQuiz.progress = 0
                    val message = "Jumlah Benar : $correct\n" +
                            "Jumlah Salah : $wrong"
                    showResultDialog(message, score)
                }
            }

            R.id.iv_quiz_back -> {
                val message: String? = "Apakah Anda yakin ingin berhenti mengerjakan?"
                showCustomDialogQuiz(message)
            }
        }
    }

    private fun showCustomDialogQuiz(message: String?) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_custom)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val tvMessageDialog: TextView = dialog.findViewById(R.id.tv_dialog_message)
        val btnYesDialog: Button = dialog.findViewById(R.id.btn_dialog_yes)
        val btnNoDialog: Button = dialog.findViewById(R.id.btn_dialog_no)

        tvMessageDialog.text = message

        btnYesDialog.setOnClickListener {
            binding.pbQuiz.progress = 0
            val intent = Intent()
//                intent.putExtra("key", 1)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
        btnNoDialog.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun showResultDialog(message: String, score: Int) {
        val bottomSheet = BottomSheetScoreFragment(this, message, score)
        bottomSheet.isCancelable = false
        bottomSheet.show(supportFragmentManager, bottomSheet.tag)
    }
}