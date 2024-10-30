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
    TextView txtLetterBefore;
    TextView txtLetterAfter;
    CountDownTimer timer;

    //TODO: ŞUANLIK LİSTE BOŞ
    CircularLinkedList<ArrayList<String>> questions = new CircularLinkedList<>();
    ArrayList<ArrayList<String>> questAnsw;
    ArrayList<ArrayList<String>> resultList=new ArrayList<>();;

    Node<ArrayList<String>> question = questions.head;
    Node<ArrayList<String>> head;

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
        head = questions.head;
        // HARF
        txtLetter = findViewById(R.id.txtLetter);
        txtLetterBefore=findViewById(R.id.txtLetterBefore);
        txtLetterAfter=findViewById(R.id.txtLetterAfter);
        // SORU
        txtQuestion = findViewById(R.id.txtQuestion);


        System.out.println(questions);
        System.out.println(question);
        txtLetterBefore.setVisibility(View.INVISIBLE);
        txtLetter.setText(question.data.get(0));
        txtQuestion.setText(question.data.get(2));
        txtLetterAfter.setText(question.next.data.get(0));



        // TIMER
        txtTimer = findViewById(R.id.txtTimer);
        startTime();


        // BUTTON OK
        btnOK = findViewById(R.id.btnOk);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtLetterAfter.setVisibility(View.INVISIBLE);
                // KULLANICI CEVABI
                eTxtAnswer = findViewById(R.id.eTxtAnswer);
                txtLetterBefore.setVisibility(View.VISIBLE);


                // Doğru
                if (Objects.equals(eTxtAnswer.getText().toString().toLowerCase(), question.data.get(1).toLowerCase())){
                    //TODO: BİR SONRAKİNİ YAPIYOR KENDİSİNİ DEĞİL
                    txtLetterBefore.setBackground(correctGreen);
                    question.data.add("g");
                    resultList.add(question.data);
                    Toast.makeText(getApplicationContext(), "True", Toast.LENGTH_SHORT).show();
                }
                // Pas geçme durumu
                else {
                    if (eTxtAnswer.getText().toString().isEmpty()) {
                        questions.insertToEnd(question.data, question.color);
                        txtLetterBefore.setBackground(passYellow);
                        question.color="y";
                        System.out.println(eTxtAnswer.getText().toString()+"PASS");
                    }
                    // Yanlış
                    else {
                        System.out.println(eTxtAnswer.getText().toString()+"FALSE");
                        question.data.add("r");
                        resultList.add(question.data);
                        txtLetterBefore.setBackground(wrongRed);
                    }
                }

                txtLetter.setBackground(whiteEmpty);
                txtLetterBefore.setText(question.data.get(0));
                if (question.next!=head){
                    question= question.next;
                    txtLetterAfter.setText(question.next.data.get(0));
                    txtLetterAfter.setVisibility(View.VISIBLE);
                }
                txtLetter.setText(question.data.get(0));
                txtQuestion.setText(question.data.get(2));

                eTxtAnswer.setText("");
            }
        });


        // BUTTON PASS
        btnPass = findViewById(R.id.btnPass);
        btnPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questions.insertToEnd(question.data,question.color);
                txtLetterBefore.setVisibility(View.VISIBLE);
                eTxtAnswer = findViewById(R.id.eTxtAnswer);
                txtLetterAfter.setVisibility(View.INVISIBLE);
                txtLetterBefore.setBackground(passYellow);
                question.color="y";
                txtLetter.setBackground(whiteEmpty);

                if (question.next!=head){
                    txtLetterBefore.setText(question.data.get(0));
                    question= question.next;
                    txtLetterAfter.setText(question.next.data.get(0));
                    txtLetterAfter.setVisibility(View.VISIBLE);
                }
                txtLetter.setText(question.data.get(0));
                txtQuestion.setText(question.data.get(2));

                eTxtAnswer.setText("");
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