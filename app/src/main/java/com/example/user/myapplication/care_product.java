package com.example.user.myapplication;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class care_product extends AppCompatActivity {

    private ListView care_product;
    private String[] care_product_list = {"玻尿酸清爽保濕化妝水     (推薦!!)","極潤緊緻彈力保濕化粧水(清爽型)     (推薦!!)","極潤金緻特濃保濕精華水 (肌研)","玻尿酸特濃保濕化妝水 (自白肌)","美白專科化妝水","細白晶透肌底液 (露得清)","深層涵水保濕露(美白)清爽型(膚蕊)","保濕專科化粧水(清爽型)","玻尿酸(複合植物)保濕菁華液Ⅱ"};
    private ArrayAdapter<String> listAdapter2;
    private TextView p_name;
    private TextView p_price;
    private Button b_back;
    private int num;
    private ImageButton get_code;
    private  int [] images={R.drawable.flower};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care_product);

        find_view();
    }

    private void find_view()
    {


        care_product=(ListView) findViewById(R.id.listView2);
        p_name=(TextView) findViewById(R.id.textView9);
        b_back=(Button) findViewById(R.id.button3);
        p_price=(TextView) findViewById(R.id.textView11);
        get_code=(ImageButton) findViewById(R.id.imageButton5);

        listAdapter2 = new ArrayAdapter(this,android.R.layout.simple_list_item_1,care_product_list);
        care_product.setAdapter(listAdapter2);

        care_product.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                p_name.setText(care_product_list[position]);
                num=(int)(Math.random()*200+100);
                p_price.setText("價錢:"+Integer.toString(num)+"元");
            }
        });

        b_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Intent intent = new Intent();
                intent.setClass(care_product.this, Index_page.class);
                startActivity(intent);
                */
                care_product.this.finish();

            }
        });

        get_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(care_product.this, QRcode.class);
                startActivity(intent);
                //Index_page.this.finish();

            }
        });


    }
}
