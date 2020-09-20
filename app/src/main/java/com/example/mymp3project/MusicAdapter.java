package com.example.mymp3project;

import android.content.Context;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class MusicAdapter extends BaseAdapter {
    private ArrayList<MusicData> arrayList;
    private Context context;

    public MusicAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return (long)position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        //리스트뷰에 들어갈 music_layout.xml 화면객체를 만든다.
        if(view == null){
            view = inflater.inflate(R.layout.music_layout, null);
        }

        //music_layout.xml 속에있는 위젯을 찾는다.
        ImageView imageView = view.findViewById(R.id.imageView);
        TextView textView = view.findViewById(R.id.textView);
        TextView textView2 = view.findViewById(R.id.textView2);

        //해당된 데이터를 가져온다.
        MusicData musicData = arrayList.get(position);

        //해당되는 값을 저장한다.
        imageView.setImageResource(musicData.getMp3Picture());
        textView.setText(musicData.getMp3Title());
        textView2.setText(musicData.getMp3Singer());

        return view;
    }

    public ArrayList<MusicData> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<MusicData> arrayList) {
        this.arrayList = arrayList;
    }
}
