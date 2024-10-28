package com.android.alesta.omnidle;

import android.os.Bundle;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.content.Context;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class QuestionCreator extends AppCompatActivity {
    public interface GenerateTextCallback {
        void onSuccess(ArrayList<ArrayList<String>> questAnsw);
        void onFailure(Exception e);
    }

    String string="";
    String filename = "myfile";
    String regex = "'\\w': \\['([^']*)', '([^']*)'\\]";
    String topic;
    ArrayList<ArrayList<String>> questAnsw = new ArrayList<>();
    ArrayList<String> answers = new ArrayList<>();
    ArrayList<String> questions = new ArrayList<>();
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        Bundle bundle = getIntent().getExtras();

    }

        public  void generateText(String subject,Context context,GenerateTextCallback callback){

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
            }
            catch (IOException e) {
            }
            finally {
                String contents = stringBuilder.toString();
            }

            Pattern pattern = Pattern.compile(regex);

            GenerativeModel gm =
                    new GenerativeModel(
                            /* modelName */ "gemini-1.5-pro",
                            /* apiKey */ "AIzaSyBRyOUHZUCuYqHQK6JqRPObQpCv9krug8g");
            GenerativeModelFutures model = GenerativeModelFutures.from(gm);
        String prompt ="Her anahtarın \"a\"dan \"z\"ye kadar bir harf olduğu ve değerin iki öğeden oluşan bir liste olduğu bir Python sözlüğü oluşturun:\n" +
                "O harfle başlayan, %1$s ile ilgili bir kelime, ancak %2$s kelimelerinden kaçının.\n" +
                "O kelimeyle cevaplanabilecek bir soru.\n" +
                "Örneğin, 'a' harfi için değer ['açı', 'Geometride bir açının ölçüsü nedir?'] olabilir.\n" +
                "Her cevap, harfle ilgili tek kelimelik bir %1$s kavramı olmalıdır ve her soru net ve öz olmalıdır.\n" +
                "Lütfen bunu A'dan Z'ye kadar tüm harfler için tamamlayın. Cevaplar türkçe olmak zorundadır. Cevaplar karşı geldikleri harfle başlamalıdır. a dan z ye türk alfabesi kullanılmalıdır. Alfabeye çok dikkat et bütün türkçe harfleri olucak ğ hariç.";
            Content content =
                    new Content.Builder().addText(String.format(prompt,subject,string
 )).build();

            ListenableFuture<GenerateContentResponse> response = model.generateContent(content);
            Futures.addCallback(
                    response,
                    new FutureCallback<GenerateContentResponse>() {
                        @Override
                        public void onSuccess(GenerateContentResponse result) {

                            string ="";
                            String resultText = result.getText();
                            Matcher matcher = pattern.matcher(resultText);

                            while (matcher.find()) {
                                String answer = matcher.group(1);
                                String question = matcher.group(2);
                                answers.add(answer);
                                questions.add(question);
                                string=string + answer+",";
                            }

                            questAnsw.add(answers);
                            questAnsw.add(questions);

                            try (FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE)) {
                                fos.write(string.getBytes(StandardCharsets.UTF_8));
                            } catch (FileNotFoundException e) {
                                throw new RuntimeException(e);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }

                            callback.onSuccess(questAnsw);
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            callback.onFailure(new Exception(t));
                            t.printStackTrace();
                        }

                    },
                    context.getMainExecutor());
        }
}
