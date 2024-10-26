package com.android.alesta.omnidle;

import android.os.Bundle;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.ai.client.generativeai.GenerativeModel;
import com.google.ai.client.generativeai.java.GenerativeModelFutures;
import com.google.ai.client.generativeai.type.Content;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.content.Context;
import android.content.res.AssetManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;



public class MainActivity extends AppCompatActivity {


    private TextView textView;
    String string="";
    String filename = "myfile";
    String regex = "'\\w': \\['([^']*)', '([^']*)'\\]";
    Context context =this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        FileInputStream fis = null;
        try {
            fis = context.openFileInput("myfile");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        InputStreamReader inputStreamReader =
                new InputStreamReader(fis, StandardCharsets.UTF_8);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line).append('\n');
                line = reader.readLine();
            }
            string=stringBuilder.toString();
            System.out.println(string);
        } catch (IOException e) {
            // Error occurred when opening raw file for reading.
        } finally {
            String contents = stringBuilder.toString();
        }
        textView=findViewById(R.id.demotext);
        generateText("physics");

    }
        public void generateText(String subject){
            Pattern pattern = Pattern.compile(regex);


            // Specify a Gemini model appropriate for your use case
            GenerativeModel gm =
                    new GenerativeModel(
                            /* modelName */ "gemini-1.5-flash",
                            // Access your API key as a Build Configuration variable (see "Set up your API key"
                            // above)
                            /* apiKey */ "");
            GenerativeModelFutures model = GenerativeModelFutures.from(gm);
        String prompt ="Create a Python dictionary where each key is a letter from \"a\" to \"z\" and the value is a list with two items:\n" +
                "A %s-related word starting with that letter, avoid the words %s.\n" +
                "A question that could be answered by that word.\n" +
                "For example, for the letter 'a', the value might be ['angle', 'What is the measure of an angle in geometry?'].\n" +
                "Each answer should be a one-word math concept related to the letter, and each question should be clear and concise.\n" +
                "Please complete this for all letters, A to Z.";
            Content content =
                    new Content.Builder().addText(String.format(prompt,subject,string
 )).build();

// For illustrative purposes only. You should use an executor that fits your needs.
            Executor executor = Executors.newSingleThreadExecutor();

            ListenableFuture<GenerateContentResponse> response = model.generateContent(content);
            Futures.addCallback(
                    response,
                    new FutureCallback<GenerateContentResponse>() {
                        @Override
                        public void onSuccess(GenerateContentResponse result) {
                            string ="";
                            String resultText = result.getText();
                            Matcher matcher = pattern.matcher(resultText);

                            // Find matches and capture groups
                            while (matcher.find()) {
                                String answer = matcher.group(1);
                                String question = matcher.group(2);
                                string=string+answer+",";
                            }
                            System.out.println(string);
                            try (FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE)) {
                                fos.write(string.getBytes(StandardCharsets.UTF_8));
                            } catch (FileNotFoundException e) {
                                throw new RuntimeException(e);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            t.printStackTrace();
                        }
                    },
                    this.getMainExecutor());
        }
}
