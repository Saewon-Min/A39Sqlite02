package com.kosmo.a39sqlite02;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "iKosmo";

    // SQLite와 어뎁터를 사용하기 위한 멤버변수 선언
    SQLiteDatabase database;
    SingerAdapter adapter;
    TextView textView2;

    String dbName;
    String tname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 테이블명
        tname = "customer";

        // 어뎁터 객체 생성 및 리스트뷰 위젯에 설정
        adapter = new SingerAdapter(this);
        ListView listView1 = findViewById(R.id.listView1);
        listView1.setAdapter(adapter);

        // 리스트뷰에 리스너 부착
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            /*
            특정 아이템을 클릭했을때 전달되는 인덱스 i를 통해 어뎁터 항목을
            가져온다. getName()으로 이름을 가져와서 토스트로 출력한다.
             */
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                SingerDTO item = (SingerDTO)adapter.getItem(i);
                Toast.makeText(getApplicationContext(),
                        "선택항목 : "+item.getName(),
                        Toast.LENGTH_SHORT).show();

            }
        });

        textView2 = findViewById(R.id.textView2);

        createMyDatabase();
        createMyTable();
        selectAllData();


    }

    // 추가하기 버튼
    public void onBtn1Clicked(View v){

        // 2개의 데이터를 입력한 후 텍스트 뷰에 결과를 출력한다.
        String sql1 = "insert into customer " +
                "(name, age, mobile) values ('소녀시대'," +
                "9,'010-1111-1111') ";
        String sql2 = "insert into customer " +
                "(name, age, mobile) values ('워너원'," +
                "11,'010-2222-2222') ";

        try {
            database.execSQL(sql1);
            printInfo("데이터 추가 : 1");
            database.execSQL(sql2);
            printInfo("데이터 추가 : 1");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // 데이터를 조회 및 어뎁터에 추가하기
    public void onBtn2Clicked(View v){
        selectAllData();
    }

    // 매개변수에 전달되는 문자열을 텍스트 뷰에 추가한다.
    private void printInfo(String msg){
        textView2.append(msg+"\n");
    }

    // 데이터 베이스 생성
    private void createMyDatabase(){

        try {
            database = openOrCreateDatabase("customer.sqlite",
                    Activity.MODE_PRIVATE, null);

            printInfo("데이터 베이스 생성 : customer.sqlite");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    // 테이블 생성
    private void createMyTable(){

        // 테이블이 없을때만 새롭게 생성한다.
        String sql = "create table if not exists customer " +
                "(name text, age integer, mobile text ) ";
        try {
            database.execSQL(sql);
            printInfo("테이블 생성 : customer");
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    // 테이블의 모든 레코드를 조회한다.
    private  void selectAllData(){

        String sql = "select name, age, mobile from customer ";

        try{

            Cursor cursor = database.rawQuery(sql, null);

            int count = cursor.getCount();
            printInfo("데이터 개수 : "+count);

            int i = 0;
            while (i < count){
                cursor.moveToNext();
                String name = cursor.getString(0);
                int age = cursor.getInt(1);
                String mobile = cursor.getString(2);

                Log.d(TAG, "# "+name+" : "+age+" : "+mobile);
                printInfo( "# "+name+" : "+age+" : "+mobile);

                // 하나의 레코드르 DTO에 저장한 후 어뎁터에 추가한다.
                SingerDTO item = new SingerDTO(name, age, mobile);
                adapter.addItem(item);

                i++;

            }


        }catch (Exception e){
            e.printStackTrace();
        }


    }


}