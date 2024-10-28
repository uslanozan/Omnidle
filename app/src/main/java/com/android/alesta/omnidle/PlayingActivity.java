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

import java.util.ArrayList;
import java.util.Arrays;

public class PlayingActivity extends AppCompatActivity {

    Button buttonBack;
    Button buttonConfirm;
    EditText editText;
    String topic;
    ArrayList<String> turkishAlphabet = new ArrayList<>(Arrays.asList(
            "A", "B", "C", "Ç", "D", "E", "F", "G", "Ğ", "H", "I", "İ", "J", "K", "L", "M",
            "N", "O", "Ö", "P", "R", "S", "Ş", "T", "U", "Ü", "V", "Y", "Z"
    ));
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



        buttonConfirm = findViewById(R.id.btnConfirm);
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText = findViewById(R.id.eTxtInput);
                topic = editText.getText().toString();
                if (topic!=null){
                    QuestionCreator maker = new QuestionCreator();
                    maker.generateText(topic, getApplicationContext(), new QuestionCreator.GenerateTextCallback() {
                        @Override
                        public void onSuccess(ArrayList<ArrayList<String>> questAnsw) {
                            System.out.println(questAnsw);
                            CircularLinkedList<ArrayList<String>> circular=new CircularLinkedList<>();
                            circular.insertToEnd(questAnsw.get(0));
                        }

                        @Override
                        public void onFailure(Exception e) {
                            System.out.println("Generation Failed");
                        }
                    });

                }else {
                    String errorMessage = "Lütfen bir konu seçin !";
                    Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}