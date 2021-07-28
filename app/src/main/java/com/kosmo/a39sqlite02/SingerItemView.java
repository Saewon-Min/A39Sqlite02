package com.kosmo.a39sqlite02;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

/*
가수 정보 항목 1개를 설정하기 위한 클래스로 LinearLayout을 상속하여 정의한다.
 */
public class SingerItemView extends LinearLayout {

    TextView textView1;
    TextView textView2;
    TextView textView3;

    // 생성자
    public SingerItemView(Context context) {

        // Context형 매개변수를 통해 레이아웃을 전개할 액티비티를 결정한다.
       super(context);

        // 커스텀 뷰를 inflate한다.
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.singer_item_view, this, true);

        // 데이터를 출력할 위젯을 가져온다.
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
    }

    // 각각의 텍스트뷰를 설정할 setter() 메소드 정의
    public void setName(String name){
        textView1.setText(name);
    }
    public void setAge(int age){
        textView2.setText(String.valueOf(age));
    }
    public void setMobile(String mobile){
        textView3.setText(mobile);
    }

}
