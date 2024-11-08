package com.android.alesta.omnidle;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ResultScreenActivity extends AppCompatActivity {

    //TODO: BURANIN DÜZELMESİ GEREK ESKTRA BİLGİ ALMAM LAZIM
    // HARF, CEVAP, SORU, DOĞRU/YANLIŞ/PAS
    ArrayList<ArrayList<String>> resultList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        listView = findViewById(R.id.listView);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            resultList = (ArrayList<ArrayList<String>>) bundle.getSerializable("resultList"); // Use the same key

        }

        if (resultList != null) {
            ResultAdapter adapter = new ResultAdapter(this, resultList);
            listView.setAdapter(adapter);
        } else {
            System.out.println("Liste boş");
        }

    }
}