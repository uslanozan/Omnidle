package com.android.alesta.omnidle;

import android.graphics.drawable.Drawable;
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

    Button btnOK;
    Button btnPass;
    EditText eTxtAnswer;
    TextView txtLetter;
    TextView txtQuestion;
    TextView txtTimer;
    CountDownTimer timer;

    //TODO: ŞUANLIK LİSTE BOŞ
    CircularLinkedList<ArrayList<String>> questions = new CircularLinkedList<>();
    ArrayList<ArrayList<String>> questAnsw;
    Node<ArrayList<String>> question = questions.head;


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

        // Renk tanımlamaları
        Drawable correctGreen = ContextCompat.getDrawable(getApplicationContext(), R.drawable.circle_green);
        Drawable wrongRed = ContextCompat.getDrawable(getApplicationContext(), R.drawable.circle_red);
        Drawable passYellow = ContextCompat.getDrawable(getApplicationContext(), R.drawable.circle_yellow);
        Drawable whiteEmpty = ContextCompat.getDrawable(getApplicationContext(), R.drawable.circle_white);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            questAnsw = (ArrayList<ArrayList<String>>) bundle.getSerializable("questansw"); // Use the same key
            for (ArrayList<String> question:questAnsw
            ) {
                questions.insertToEnd(question);

            }
            questions.display();
        }

        question = questions.head;

        // HARF
        txtLetter = findViewById(R.id.txtLetter);

        // SORU
        txtQuestion = findViewById(R.id.txtQuestion);

        System.out.println(questions);
        System.out.println(question);

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
                    //TODO: BİR SONRAKİNİ YAPIYOR KENDİSİNİ DEĞİL
                    txtLetter.setBackground(correctGreen);
                    System.out.println(eTxtAnswer.getText().toString()+"TRUE");
                    question.isEmpty = false;
                }
                // Pas geçme durumu
                else {
                    if (eTxtAnswer.getText().toString().isEmpty()) {
                        txtLetter.setBackground(passYellow);
                        System.out.println(eTxtAnswer.getText().toString()+"PASS");
                    }
                    // Yanlış
                    else {
                        txtLetter.setBackground(wrongRed);
                        System.out.println(eTxtAnswer.getText().toString()+"FALSE");
                        question.isEmpty = false;
                    }
                }
                //TODO: BEKLEME İŞİ ÇALIŞMIYOR DÜZELT
                /*
                try {
                    wait(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                 */

                txtLetter.setBackground(whiteEmpty);
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
                txtLetter.setBackground(passYellow);
                txtLetter.setBackground(whiteEmpty);
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