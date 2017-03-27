package com.example.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class basic_question extends AppCompatActivity {

    private Button b_ok;

    private EditText name;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_question);
        name=(EditText) findViewById(R.id.editText);

        b_ok=(Button) findViewById(R.id.button2);
        b_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(basic_question.this, Index_page.class);

                Bundle bundle = new Bundle();
                bundle.putString("user_name",name.getText().toString()); //傳送facecondition 回index
                intent.putExtras(bundle);

                startActivity(intent);
                basic_question.this.finish();

            }
        });
    }


}
