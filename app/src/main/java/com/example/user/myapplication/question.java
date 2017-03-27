package com.example.user.myapplication;


import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.os.Handler;
import android.widget.Toast;


class Utils {
    private static long lastClickTime;

    public  static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
}
public class question extends AppCompatActivity {

    private  String [] question_list={"1.是否有痘痘的困擾 ?","2.炎夏時，是否容易泛油光 ?","3.肌膚受刺激時，會不會自然的泛紅、長濕疹、刺癢 ?","4.鼻子、額頭(T字部位)易油，而臉頰是否乾燥 ?","5.肌膚緊繃，缺乏彈性 ?","6.肌膚易有皮屑、長黑斑 ?"};
    private TextView t1;
    private ImageButton b_yes;
    private ImageButton b_no;
    private  int nowquestion=0;
    private Handler myHandle;
    private  int [] face_condition; // 0中 1乾性 2油性 3敏感性 4混合性
    private String [] condition={"中性","乾性","油性","敏感性","混合性"};
    private String a="系統分析結果: \n";
    private TextView hint;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

      myHandle=new Handler()
      {
        public void handleMessage(Message msg)
        {
            if(msg.what==0)
            {
                t1.setText(question_list[nowquestion]);
            }
            else if (msg.what==1)
            {
                t1.setText(a);
            }
        }
      };

        face_condition=new int[5];


        find_view();
    }

    private void find_view()
    {

        t1=(TextView)findViewById(R.id.textView8);
        t1.setText(question_list[nowquestion]);
        hint=(TextView) findViewById(R.id.textView7);


        b_yes=(ImageButton) findViewById(R.id.imageButton3);
        b_no=(ImageButton) findViewById(R.id.imageButton4);

        b_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isFastDoubleClick()) {
                    Toast.makeText(getApplicationContext(), "請慢慢閱讀!~", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    yes_score(nowquestion);//計算分數
                    nowquestion++;
                    if(nowquestion>6)
                    {
                        question_finish();

                    }
                    else if(nowquestion>5)
                    {

                        show_result();
                    }
                    else
                    {
                        myHandle.sendEmptyMessage(0);
                    }
                }

            }


        });

        b_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isFastDoubleClick()) {
                    Toast.makeText(getApplicationContext(), "請慢慢閱讀!~", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    no_score(nowquestion); //計算分數

                    nowquestion++;
                    if(nowquestion>6)
                    {
                        question_finish();

                    }
                    else if(nowquestion>5)
                    {
                        show_result();

                    }
                    else
                    {
                        myHandle.sendEmptyMessage(0);
                    }
                }
            }
        });
    }
    private void question_finish()
  {

      Toast.makeText(getApplicationContext(), "回答完畢，資料已記錄", Toast.LENGTH_SHORT).show();

      question.this.finish();

      //transfer_data();
      //intent.setClass(question.this, Index_page.class);
      //question.this.finish();


  }
    private  void transfer_data()  //結果傳遞
    {
        Intent intent = new Intent();
        intent.setClass(question.this,Index_page.class);
        //new一個Bundle物件，並將要傳遞的資料傳入
        //Bundle bundle = new Bundle();
        //bundle.putIntArray("face_condition",face_condition); //傳送facecondition 回index
        //intent.putExtras(bundle);
        startActivity(intent);



    }

    private  void yes_score(int noquestion)
    {
        if(noquestion==0)//第一題
        {
            // 0中 1乾性 2油性 3敏感性 4混合性
            face_condition[2]++;
            face_condition[3]++;
            face_condition[4]++;
        }
        else if (noquestion==1)
        {
            face_condition[2]++;
            face_condition[4]++;
        }
        else if (noquestion==2)
        {
            face_condition[3]++;
        }
        else if(noquestion==3)
        {
            face_condition[4]++;
        }
        else if (noquestion==4)
        {
            face_condition[1]++;
        }
        else if (noquestion==5)
        {
            face_condition[1]++;
        }

    }
    private  void no_score(int noquestion)
    {
        // 0中 1乾性 2油性 3敏感性 4混合性
        if(noquestion==0)
        {
            face_condition[0]++;
            face_condition[1]++;
        }
        else if(noquestion==1)
        {
            face_condition[0]++;
            face_condition[1]++;
            face_condition[3]++;
        }
        else if(noquestion==2)
        {
            face_condition[2]++;
            face_condition[3]++;
        }
        else if (noquestion==3)
        {

            face_condition[2]++;
        }
        else if(noquestion==4)
        {
            face_condition[0]++;

        }
        else if(noquestion==5)
        {
            face_condition[0]++;
        }
    }

    private void show_result()
    {
        int step_score;
        String step;
        hint.setText("請按任意鑑離開~");

        for(int i =0;i<5;i++)
        {
            for(int k=0;k<5;k++)
            {
                if(face_condition[i]>=(face_condition[k]))
                {
                    step_score=face_condition[i];
                    face_condition[i]=face_condition[k];
                    face_condition[k]=step_score;

                    step=condition[i];
                    condition[i]=condition[k];
                    condition[k]=step;
                }

            }

        }

        if(face_condition[0]==face_condition[1])
        {
            face_condition[0]=face_condition[0]+3;
        }
        a=a+"恭喜你是 :"+condition[0]+"\n";
        for(int i =0 ;i<5;i++)
        {
            a=a+condition[i]+Integer.toString(face_condition[i])+"分\n";
        }
        myHandle.sendEmptyMessage(1);


    }

}
