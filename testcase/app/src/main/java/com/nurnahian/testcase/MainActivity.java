package com.nurnahian.testcase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText text1,text2;
    TextView view1,view2;
    Button submit,reset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text1 =findViewById(R.id.editTextTextPersonName);
        text2 =findViewById(R.id.editTextTextPassword);

        view1 = findViewById(R.id.textView3);
        view2 = findViewById(R.id.textView4);

        submit = findViewById(R.id.button2);
        reset = findViewById(R.id.button);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view1.setText(text1.getText());
                view2.setText(text2.getText());

                text1.setText("");
                text2.setText("");
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view1.setText("");
                view2.setText("");
                text1.setText("");
                text2.setText("");
            }
        });

    }
}