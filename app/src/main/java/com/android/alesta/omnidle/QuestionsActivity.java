package com.android.alesta.omnidle;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class QuestionsActivity extends AppCompatActivity {

    Button btnOK;
    Button btnPass;
    EditText eTxtAnswer;
    TextView txtLetter;
    TextView txtQuestion;
    TextView txtTimer;
    CountDownTimer timer;



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
        txtTimer = findViewById(R.id.txtTimer);
        startTime();

        /* TODO: BURADA ITERATING YAPILACAK CIRCULAR LINKEDLIST İÇİNDE
        for (int index = 0; index < ; index++) {
        }
        */

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