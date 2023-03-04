package com.example.numberguessinggame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var guessInput: EditText
    private lateinit var displayTextView: TextView
    private lateinit var playAgainButton: Button

    private var answer = (1..1000).random()
    private var guessCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        guessInput = findViewById(R.id.guess_input)
        displayTextView = findViewById(R.id.displayTextView)
        playAgainButton = findViewById(R.id.play_again_button)

        guessInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val guess = guessInput.text.toString().toIntOrNull()
                if (guess == null) {
                    displayTextView.text = "Hint: Guess the number!"
                } else {
                    guessCount++
                    when {
                        guess < answer -> displayTextView.text = "Hint: It's lower!"
                        guess > answer -> displayTextView.text = "Hint: It's higher!"
                        else -> displayTextView.text = "Correct! You guessed $guessCount times before you got it right.!"
                    }
                }
                true
            } else {
                false
            }
        }

        playAgainButton.setOnClickListener {
            answer = (1..1000).random()
            guessCount = 0
            displayTextView.text = ""
            guessInput.text.clear()
        }
    }
}