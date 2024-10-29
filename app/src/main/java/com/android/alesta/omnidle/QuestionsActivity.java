package com.android.alesta.omnidle;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class QuestionsActivity extends AppCompatActivity {

    // Renk tanımlamaları
    int correctGreen = ContextCompat.getColor(getApplicationContext(), R.color.correct_green);
    int wrongRed = ContextCompat.getColor(getApplicationContext(), R.color.wrong_red);
    int passYellow = ContextCompat.getColor(getApplicationContext(), R.color.pass_yellow);
    int whiteEmpty = ContextCompat.getColor(getApplicationContext(), R.color.white);

    Button btnOK;
    Button btnPass;
    EditText eTxtAnswer;
    TextView txtLetter;
    TextView txtQuestion;
    TextView txtTimer;
    CountDownTimer timer;

    //TODO: ŞUANLIK LİSTE BOŞ
    CircularLinkedList<ArrayList<String>> questions = new CircularLinkedList<>();
    Node<ArrayList<String>> question = questions.head;
    int questionsNumber = questions.countNodes();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_questions);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // HARF
        txtLetter = findViewById(R.id.txtLetter);

        // SORU
        txtQuestion = findViewById(R.id.txtQuestion);

        txtLetter.setText(question.data.get(0));
        txtQuestion.setText(question.data.get(2));



        // TIMER
        txtTimer = findViewById(R.id.txtTimer);
        startTime();


        // BUTTON OK
        btnOK = findViewById(R.id.btnOk);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // KULLANICI CEVABI
                eTxtAnswer = findViewById(R.id.eTxtAnswer);
                // Doğru
                if (Objects.equals(eTxtAnswer.getText().toString().toLowerCase(), question.data.get(1).toLowerCase())){
                    txtLetter.setBackgroundColor(correctGreen);
                    question.isEmpty = false;
                }
                // Pas geçme durumu
                else {
                    if (eTxtAnswer.getText().toString().isEmpty()) {
                        txtLetter.setBackgroundColor(passYellow);
                    }
                    // Yanlış
                    else {
                        txtLetter.setBackgroundColor(wrongRed);
                        question.isEmpty = false;
                    }
                }
                try {
                    wait(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                txtLetter.setBackgroundColor(whiteEmpty);
                question= question.next;
                txtLetter.setText(question.data.get(0));
                txtQuestion.setText(question.data.get(2));
            }
        });


        // BUTTON PASS
        btnPass = findViewById(R.id.btnPass);
        btnPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtLetter.setBackgroundColor(passYellow);
                txtLetter.setBackgroundColor(whiteEmpty);
                question= question.next;
                txtLetter.setText(question.data.get(0));
                txtQuestion.setText(question.data.get(2));
            }
        });



    }


    private void startTime() {
        timer = new CountDownTimer(60000*5,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long minutes = ((millisUntilFinished / 1000) % 3600) / 60;
                long seconds = (millisUntilFinished / 1000) % 60;
                String timeFormatted = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
                txtTimer.setText(timeFormatted);
            }

            @Override
            public void onFinish() {
                txtTimer.setText("00:00");
                Toast.makeText(QuestionsActivity.this,"Vakit Bitti", Toast.LENGTH_SHORT).show();
            }
        }.start();
    }
}