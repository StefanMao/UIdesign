package com.example.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Index_page extends AppCompatActivity {

    private ImageButton img_face;
    private ImageButton client_IMF;
    private ListView    list_product;
    private TextView test1;


    private String[] list = {"保濕液","洗面乳","化妝水","乳液","精華液"};
    private ArrayAdapter<String> listAdapter;
    private  String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_page);

       // get_client_data(); //獲得用戶資料


        img_face=(ImageButton) findViewById(R.id.imageButton);
        client_IMF=(ImageButton) findViewById(R.id.imageButton2);
        list_product=(ListView) findViewById(R.id.listView);
        test1=(TextView) findViewById(R.id.textView3);

        show_client_data();

        img_face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(Index_page.this, question.class);
                startActivity(intent);
                //Index_page.this.finish();
            }
        });

        client_IMF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "施工至下個禮拜~敬請期待~", Toast.LENGTH_SHORT).show();
                /*
                Intent intent = new Intent();
                intent.setClass(Index_page.this, Client_data.class);
                startActivity(intent);
                //Index_page.this.finish();
                */
            }
        });

        listAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);
        list_product.setAdapter(listAdapter);
        list_product.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0)
                {
                    Intent intent = new Intent();
                    intent.setClass(Index_page.this, care_product.class);
                    startActivity(intent);
                    //Index_page.this.finish();

                }

            }
        });



    }

    private void show_client_data()
    {

        Bundle gdata =this.getIntent().getExtras();
        username=gdata.getString("user_name");
        if(username==null)
        {
            username="用戶";
        }

        test1.setText("親愛的"+username+":");


    }
}
