package pl.michalgajewski.simplerhymebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity {
    EditText editTextWord;
    Button b_read;
    TextView tv_text;
    Button b_clear;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);

        editTextWord = (EditText)findViewById(R.id.editTextWord);
        b_read = (Button)findViewById(R.id.buttonSearch);
        tv_text = (TextView)findViewById(R.id.nameTxt);
        b_clear = (Button)findViewById(R.id.buttonClear);



        b_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        b_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                try {
                    InputStream is = getAssets().open("words.txt");
                    int size = is.available();
                    BufferedReader rd = new BufferedReader(new InputStreamReader(is,"UTF-8"));
                    String line;
                    String word = editTextWord.getText().toString();

                    while ((line = rd.readLine()) !=null ){


                        if (line.substring(line.length()-2).equals(word.substring(word.length()-2))){
                            tv_text.setText(line);

                        }else {
                            Toast.makeText(getApplicationContext(), "Nie znaleziono rymu do Twojego słowa", Toast.LENGTH_SHORT).show();
                        }
                    }

                }catch (IOException ex){
                    ex.printStackTrace();
                }


            }
        });
    }

}
