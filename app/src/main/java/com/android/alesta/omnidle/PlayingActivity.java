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

import java.util.Objects;

public class PlayingActivity extends AppCompatActivity {

    Button buttonBack;
    Button buttonConfirm;
    EditText editText;
    String topic;

    public String [] tempAnswers = {
            "average", "base", "circle", "decimal", "exponent", "factor",
            "graph", "histogram", "inequality", "justify", "kilogram", "length",
            "mean", "numerator", "odd", "perimeter", "quotient", "ratio",
            "slope", "tangent", "unit", "vector",
            "width", "x-axis", "y-axis", "zero"
    };

    public String[] tempQuestions = {
            "What is the sum of a set of numbers divided by the count of numbers?",
            "What is the bottom side of a triangle called?",
            "What shape is defined by all points equidistant from a central point?",
            "What is a fractional number represented by a dot and digits?",
            "What indicates the number of times a base is multiplied by itself?",
            "What number divides another number evenly?",
            "What visual representation shows relationships between variables?",
            "What type of chart displays frequency distribution?",
            "What mathematical statement compares two expressions using greater than, less than, or equal to?",
            "What do you do when you provide reasons for your mathematical steps?",
            "What unit measures mass?",
            "What is the distance between two points?",
            "What is the average of a set of numbers?",
            "What is the top number in a fraction called?",
            "What kind of number is not divisible by 2?",
            "What is the total distance around a two-dimensional shape?",
            "What is the result of division?",
            "What compares two quantities?",
            "What describes the steepness of a line?",
            "What is the line that touches a circle at only one point?",
            "What is a standard measurement?",
            "What has both magnitude and direction?",
            "What is the horizontal distance of a rectangle?",
            "What is the horizontal axis in a coordinate plane?",
            "What is the vertical axis in a coordinate plane?",
            "What is the additive identity?"
    };


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
        System.out.println(331313131);

        //TODO: DEĞİŞECEK
        Assets assets = new Assets();
        assets.setAnswers(tempAnswers);
        assets.setQuestions(tempQuestions);

        /*
        TODO:BURADA BELİRLENEN KONUYA GÖRE SORULAR VE CEVAPLAR HAZIRLANACAK ŞUAN MANUAL GİRİLMEKTE
        */

        buttonConfirm = findViewById(R.id.btnConfirm);
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                topic = editText.getText().toString();
                if (!Objects.equals(topic, "")){
                    /*
                    "LLM FONKSİYONU"
                    startActivities("LLM",intent);
                     */
                    Intent intent = new Intent(PlayingActivity.this, QuestionsActivity.class);
                    startActivity(intent);
                }else {
                    String errorMessage = "Lütfen bir konu seçin !";
                    Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}