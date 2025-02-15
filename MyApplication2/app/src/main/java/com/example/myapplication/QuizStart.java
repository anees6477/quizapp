package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuizStart extends AppCompatActivity implements View.OnClickListener {
    TextView questionTextView;
    Button answerButton1, answerButton2, answerButton3, answerButton4;
    Button nextButton, submitButton;

    int totalQuestions = QuizQuestions.questions.length;
    int score = 0;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_start);

        questionTextView = findViewById(R.id.question);
        answerButton1 = findViewById(R.id.answerA);
        answerButton2 = findViewById(R.id.answerB);
        answerButton3 = findViewById(R.id.answerC);
        answerButton4 = findViewById(R.id.answerD);
        nextButton = findViewById(R.id.nextButton);
        submitButton = findViewById(R.id.submitButton);

        answerButton1.setOnClickListener(this);
        answerButton2.setOnClickListener(this);
        answerButton3.setOnClickListener(this);
        answerButton4.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        submitButton.setOnClickListener(this);

        loadNewQuestion();
    }

    @Override
    public void onClick(View v) {
        if (v == nextButton) {
            checkAnswer();
            if (currentQuestionIndex < totalQuestions - 1) {
                currentQuestionIndex++;
                loadNewQuestion();
            }
            updateButtonVisibility();
        } else if (v == submitButton) {
            checkAnswer();
            finishQuiz();
        } else {
            handleAnswerSelection((Button) v);
        }
    }

    private void loadNewQuestion() {
        if (currentQuestionIndex >= totalQuestions) {
            finishQuiz();
            return;
        }
        questionTextView.setText(QuizQuestions.questions[currentQuestionIndex]);
        answerButton1.setText(QuizQuestions.choices[currentQuestionIndex][0]);
        answerButton2.setText(QuizQuestions.choices[currentQuestionIndex][1]);
        answerButton3.setText(QuizQuestions.choices[currentQuestionIndex][2]);
        answerButton4.setText(QuizQuestions.choices[currentQuestionIndex][3]);
        resetButtonColors();
    }

    private void handleAnswerSelection(Button clickedButton) {
        resetButtonColors();
        selectedAnswer = clickedButton.getText().toString();
        clickedButton.setBackgroundColor(Color.MAGENTA);
    }

    private void resetButtonColors() {
        answerButton1.setBackgroundColor(Color.LTGRAY);
        answerButton2.setBackgroundColor(Color.LTGRAY);
        answerButton3.setBackgroundColor(Color.LTGRAY);
        answerButton4.setBackgroundColor(Color.LTGRAY);
    }

    private void checkAnswer() {
        if (selectedAnswer.equals(QuizQuestions.correctAnswers[currentQuestionIndex])) {
            score++;
        }
    }

    private void updateButtonVisibility() {
        if (currentQuestionIndex == totalQuestions - 1) {
            nextButton.setVisibility(View.GONE);
            submitButton.setVisibility(View.VISIBLE);
        } else {
            nextButton.setVisibility(View.VISIBLE);
            submitButton.setVisibility(View.GONE);
        }
    }

    private void finishQuiz() {
        Intent intent = new Intent(QuizStart.this, QuizReviewActivity.class);
        intent.putExtra("score", score);
        intent.putExtra("totalQuestions", totalQuestions);
        startActivity(intent);
        finish();
    }
}
