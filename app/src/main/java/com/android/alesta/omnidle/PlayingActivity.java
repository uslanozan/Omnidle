package com.android.alesta.omnidle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PlayingActivity extends AppCompatActivity {

    Button buttonBack;
    Button buttonConfirm;
    EditText editText;
    String topic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_playing);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        buttonBack = findViewById(R.id.btnBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlayingActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        editText = findViewById(R.id.eTxtInput);
        topic = editText.getText().toString();

        buttonConfirm = findViewById(R.id.btnConfirm);
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (topic!=null){
                    /*
                    "LLM FONKSİYONU"
                    Intent intent = new Intent(PlayingActivity.this,"DİĞER AKTİVİTE YAZILACAK");
                    startActivities("LLM",intent);
                     */
                }else {
                    String errorMessage = "Lütfen bir konu seçin !";
                    Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}