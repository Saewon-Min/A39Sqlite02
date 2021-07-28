package com.kosmo.a39sqlite02;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class SingerAdapter extends BaseAdapter {

    Context context;
    ArrayList<SingerDTO> items = new ArrayList<>();

    public SingerAdapter(Context context){
        this.context = context;
    }

    // 매개변수로 전달되는 DTO 객체를 데이터로 추가한다.
    public void addItem (SingerDTO item){
        items.add(item);
    }

    // BaseAdapter 를 상속한 후 기본적으로 오버라이딩 하는 4가지 메소드
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {


        SingerItemView view = null;

        if(convertView==null){
            view = new SingerItemView(context);
        }else{
            view = (SingerItemView)convertView;
        }

        // 인덱스에 해당하는 item을 얻어와서 어뎁터 항목을 설정한다.
        final  SingerDTO item = items.get(i);
        view.setName(item.getName());
        view.setAge(item.getAge());
        view.setMobile(item.getMobile());

        return view;
    }
}
