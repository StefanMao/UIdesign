package com.example.user.myapplication;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.security.MessageDigest;
 class SHAHashingExample
{
    public static String mess_or;

    public static void main(String[] args)throws Exception
    {
        String password = "12345678";
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());

        byte byteData[] = md.digest();
        //convert the byte to hex format method 1

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        System.out.println("Hex format : " + sb.toString());
        mess_or=sb.toString();

        //convert the byte to hex format method 2
        StringBuffer hexString = new StringBuffer();
        for (int i=0;i<byteData.length;i++) {
            String hex=Integer.toHexString(0xff & byteData[i]);
            if(hex.length()==1) hexString.append('0');
            hexString.append(hex);
        }
        System.out.println("Hex format : " + hexString.toString());
    }
}


public class QRcode extends AppCompatActivity {
    private Button creat;
    private TextView t_original;
    private TextView t_encryp;
    private Button b_email;


    //SHAHashingExample e1=new SHAHashingExample();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        t_original=(TextView)findViewById(R.id.textView12);
        t_encryp=(TextView)findViewById(R.id.textView13);
        b_email=(Button) findViewById(R.id.button6);
        b_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(QRcode.this, sendemail.class);
                startActivity(intent);
                //QRcode.this.finish();
            }
        });



        t_original.setText("原加密資料:用戶資料+時間+產品項目");
        t_encryp.setText("資料加密文(SHA-256): 8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92");

        creat=(Button)findViewById(R.id.button4);
        creat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                QRcode.this.finish();

            }
        });
    }
}
