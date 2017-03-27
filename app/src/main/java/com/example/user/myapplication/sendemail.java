package com.example.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class sendemail extends AppCompatActivity {
    private Button B_send;
    private EditText TCON;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendemail);

        B_send=(Button)  findViewById(R.id.button7);
        TCON=(EditText) findViewById(R.id.editText7);

        B_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message =TCON.getText().toString();
                Emailmessage(message);

                Toast.makeText(getApplicationContext(), "郵件已寄出", Toast.LENGTH_SHORT).show();
                //sendemail.this.finish();

            }
        });
    }
    protected  void Emailmessage(String Message)
    {
        String [] to={"karta144593@gmail.com","B10117003@yuntech.edu.tw"};
        String subject="A message from your app!";
        Intent emailIntent =new Intent();
        emailIntent.putExtra(Intent.EXTRA_EMAIL,to);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT,Message);
        emailIntent.setType("message/rfc822");
        startActivity(Intent.createChooser(emailIntent,"Email"));






    }
}
