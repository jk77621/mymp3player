package com.example.mymp3project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    private ListView listViewMp3;
    private Button btnStart;
    private Button btnPause;
    private Button btnStop;
    private Button btnChange2;
    private Button btnMenu2;
    private TextView tvMusicName;
    private ProgressBar progressBarMp3;
    private SeekBar seekBar;
    private LinearLayout linearSlidingLayout;
    private ListView listViewAll;

    private ArrayList<String> mp3ArrayList = new ArrayList<String>();
    private ArrayAdapter<String> adapter2;

    //----------------------------------------------------------------------------------------
    private int mp3Position;
    private String sdcardPath;
    private String mp3Singer;
    //    private ArrayList<String> mp3NameArrayList = new ArrayList<String>();
    private ArrayList<MusicData> mp3MusicDataArrayList = new ArrayList<MusicData>();
    //    private String selectedMP3;  //리스트뷰에서 선택된 파일이름
    private MusicData selectedMP3;  //리스트뷰에서 선택된 파일이름
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private boolean flag = false;
    private MusicAdapter adapter;

    private MyDBHelper myDBHelper;
    private SQLiteDatabase sqLiteDatabase;

    private boolean btnflag = false;
    private Animation translate_left;
    private Animation translate_right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setTitle("SOUND WAVE");

        //위젯객체변수 설정 및 이벤트등록
        findViewByIdFunction();

        //외부 저장장치 권한을 요청함.
        requestPermissionFunction();

        //sdcard 절대경로저장한다.
        sdcardPath = Environment.getExternalStorageDirectory().getPath()+"/";


        //화면에 ListView 보여줄 Adapter 설정한다.
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_singlechoice, mp3NameArrayList);
        adapter = new MusicAdapter(getApplicationContext());
        adapter.setArrayList(mp3MusicDataArrayList);

        //초기화 종료버튼 클릭못하게 하고, 프로그래스 바는 감춰놔야한다.
        //버튼 상태변환 함수
        btnStateChange(true, false, false, View.INVISIBLE);

        //리스트뷰 어뎁터설정, 리스트뷰 이벤트처리
        listViewMp3SettingFunction(adapter);

        linearSlidingLayout.setVisibility(View.INVISIBLE);
        translate_left = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_left);
        translate_right = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_right);

        mp3ArrayList.add("현재재생목록");

        adapter2 = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, mp3ArrayList
        );

        listViewAll.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        listViewAll.setAdapter(adapter2);
        listViewAll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String selectData = mp3ArrayList.get(position);
                Toast.makeText(getApplicationContext(), selectData, Toast.LENGTH_SHORT).show();
                switch (position){
                    case 0:
                        myDBHelper = new MyDBHelper(getApplicationContext(), "myDB");

                        ArrayList<MusicData> arrayList = myDBHelper.selectMyTBL();

                        mp3MusicDataArrayList.clear();
                        for(int i = 0; i < arrayList.size(); i++){
                            MusicData musicData2 = arrayList.get(i);
                            mp3MusicDataArrayList.add(musicData2);
                        }
                        adapter.notifyDataSetChanged();
                        break;
                    default: break;
                }
            }
        });


    }

    //리스트뷰 어뎁터설정, 리스트뷰 이벤트처리
    private void listViewMp3SettingFunction(MusicAdapter adapter) {
        listViewMp3.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listViewMp3.setAdapter(adapter);
        listViewMp3.setItemChecked(0,true);

        //리스트뷰 이벤트 선택 아이템을 등록한다.
        listViewMp3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                selectedMP3 = mp3MusicDataArrayList.get(position);
                mp3Position = position;
            }
        });
    }

    //버튼 상태변환 함수
    private void btnStateChange(boolean b, boolean b1, boolean b2, int invisible) {
        btnStart.setEnabled(b);
        btnPause.setEnabled(b1);
        btnStop.setEnabled(b2);
        progressBarMp3.setVisibility(invisible);
    }

    //외부 저장장치 권한을 요청함.
    private void requestPermissionFunction() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);
    }

    //위젯객체변수 설정 및 이벤트등록
    private void findViewByIdFunction() {
        listViewMp3 = findViewById(R.id.listViewMp3);
        btnStart = findViewById(R.id.btnStart);
        btnPause = findViewById(R.id.btnPause);
        btnStop = findViewById(R.id.btnStop);
        btnChange2 = findViewById(R.id.btnChange2);
        btnMenu2 = findViewById(R.id.btnMenu2);
        tvMusicName = findViewById(R.id.tvMusicName);
        progressBarMp3 = findViewById(R.id.progressBarMp3);
        seekBar = findViewById(R.id.seekBar);
        linearSlidingLayout = findViewById(R.id.linearSlidingLayout);
        listViewAll = findViewById(R.id.listViewAll);

        //이벤트등록
        btnStart.setOnClickListener(this);
        btnPause.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        btnChange2.setOnClickListener(this);
        btnMenu2.setOnClickListener(this);

        btnStart.setBackgroundResource(R.drawable.play2);
        btnPause.setBackgroundResource(R.drawable.pause2);
        btnStop.setBackgroundResource(R.drawable.stop2);
        btnChange2.setBackgroundResource(R.drawable.cd2);
        btnMenu2.setBackgroundResource(R.drawable.menu);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(b){
                    mediaPlayer.seekTo(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }//end of findView

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnStart:
                try {
                    mediaPlayer.setDataSource(sdcardPath + selectedMP3.getMp3Title());
                    //음악준비하는시간을 기다려줘야한다.
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    //2. 시크바로 노래가 나가는 시간을 맞추어서 시크바도 진행해야된다.(스레드를 설계한다.)
                    Thread thread = new Thread(){
                        @Override
                        public void run() {
                            while(mediaPlayer.isPlaying() || flag == true){
                                if(flag != true) {
                                    //1. 선택된 노래의 음악시간을 가져와서 시크바에 최대값에 대입한다.
                                    seekBar.setMax(mediaPlayer.getDuration());
                                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                                }
                                SystemClock.sleep(100);
                            }

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mediaPlayer.reset();
                                    seekBar.setProgress(0);
                                    progressBarMp3.setVisibility(View.INVISIBLE);
                                    btnStart.setEnabled(true);
                                    btnPause.setEnabled(false);
                                    btnStop.setEnabled(false);
                                }
                            });
                        }
                    };
                    thread.start();
                    btnStart.setEnabled(false);
                    btnPause.setEnabled(true);
                    btnStop.setEnabled(true);
                    tvMusicName.setText("현재실행음악 : "+selectedMP3.getMp3Title());
                    progressBarMp3.setVisibility(View.VISIBLE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btnPause:
                if(flag == false){
                    mediaPlayer.pause();
//                    btnPause.setText("이어듣기");
                    btnPause.setBackgroundResource(R.drawable.pause4);
//                    btnPause.setImageResource(R.drawable.play2);
                    flag = true;
                    btnStart.setEnabled(false);
                    btnStop.setEnabled(true);
                    progressBarMp3.setVisibility(View.INVISIBLE);
                }else{
                    mediaPlayer.start();
//                    btnPause.setText("일시정지");
                    btnPause.setBackgroundResource(R.drawable.pause2);
//                    btnPause.setImageResource(R.drawable.pause2);
                    flag = false;
                    btnStart.setEnabled(false);
                    btnStop.setEnabled(true);
                    progressBarMp3.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.btnStop:
                mediaPlayer.stop();
                //미디어플레이어 초기화시킨다.
                mediaPlayer.reset();
                btnStart.setEnabled(true);
                btnStop.setEnabled(false);
                btnPause.setEnabled(false);
                btnPause.setBackgroundResource(R.drawable.pause2);
                tvMusicName.setText("음악을 선택해주세요.");
                progressBarMp3.setVisibility(View.INVISIBLE);
                break;
            case R.id.btnChange2:
                try {
                    Intent intent = new Intent(getApplicationContext(), Main3Activity.class);
                    intent.putExtra("mp3Position", mp3Position);
                    intent.putExtra("mp3Title", selectedMP3.getMp3Title());
                    intent.putExtra("mp3Singer", selectedMP3.getMp3Singer());
                    startActivity(intent);
                }catch(NullPointerException e){
                    Toast.makeText(getApplicationContext(), "음악을 선택해주세요.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnMenu2:
                View dialogView = View.inflate(getApplicationContext(), R.layout.music_dialog2, null);

                final EditText edtTitle = dialogView.findViewById(R.id.edtTitle);
                final EditText edtSinger = dialogView.findViewById(R.id.edtSinger);
                final EditText edtMp3picture = dialogView.findViewById(R.id.edtMp3Picture);
                Button btnInit = dialogView.findViewById(R.id.btnInit);
                Button btnInsert = dialogView.findViewById(R.id.btnInsert);
                Button btnEdit = dialogView.findViewById(R.id.btnEdit);
                Button btnDelete = dialogView.findViewById(R.id.btnDelete);
                Button btnSearch = dialogView.findViewById(R.id.btnSearch);
                ListView listView = dialogView.findViewById(R.id.listView);

                AlertDialog.Builder dialog = new AlertDialog.Builder(Main2Activity.this);
                dialog.setTitle("내 음악 리스트 관리");
                dialog.setView(dialogView);


                listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                listView.setAdapter(adapter);
                listView.setItemChecked(0,true);


                btnInit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sqLiteDatabase = myDBHelper.getWritableDatabase();
                        myDBHelper.onUpgrade(sqLiteDatabase, 1,2);
                        sqLiteDatabase.close();
                        Toast.makeText(getApplicationContext(), "초기화성공", Toast.LENGTH_SHORT).show();
                    }
                });

                btnInsert.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String picture = edtMp3picture.getText().toString();
                        String mp3Picture = "R.drawable."+picture;
                        int mp3Picture2 = 0;
                        switch (mp3Picture){
                            case "R.drawable.iu":
                                mp3Picture2 = R.drawable.iu;
                                break;
                            case "R.drawable.hyuk5":
                                mp3Picture2 = R.drawable.hyuk5;
                                break;
                            case "R.drawable.bes":
                                mp3Picture2 = R.drawable.bes;
                                break;
                            case "R.drawable.beach":
                                mp3Picture2 = R.drawable.beach;
                                break;
                            case "R.drawable.flower":
                                mp3Picture2 = R.drawable.flower;
                                break;
                            case "R.drawable.goodbye":
                                mp3Picture2 = R.drawable.goodbye;
                                break;
                            case "R.drawable.leaves":
                                mp3Picture2 = R.drawable.leaves;
                                break;
                            case "R.drawable.maria":
                                mp3Picture2 = R.drawable.maria;
                                break;
                            case "R.drawable.summer":
                                mp3Picture2 = R.drawable.summer;
                                break;
                            case "R.drawable.summerhate":
                                mp3Picture2 = R.drawable.summerhate;
                                break;
                        }

                        String title = edtTitle.getText().toString();
                        String name = edtSinger.getText().toString();

                        MusicData musicData = new MusicData(mp3Picture2, title, name);

                        mp3MusicDataArrayList.add(musicData);
                        adapter.notifyDataSetChanged();

                        boolean returnValue = myDBHelper.insertMyTBL(musicData);
                        if(returnValue == true){
                            Toast.makeText(getApplicationContext(), "입력성공", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "입력잘못", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                btnEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(edtTitle.getText().toString().equals("")){
                            Toast.makeText(getApplicationContext(), "제목을 입력해주세요.", Toast.LENGTH_SHORT).show();
                        }else{
                            sqLiteDatabase = myDBHelper.getWritableDatabase();

                            sqLiteDatabase.execSQL("UPDATE myTBL SET name='"+ edtSinger.getText().toString()+
                                    "' WHERE title = '"+edtTitle.getText().toString()+"';");


                            ArrayList<MusicData> arrayList2 = myDBHelper.selectMyTBL();

                            mp3MusicDataArrayList.clear();
                            for(int i = 0; i < arrayList2.size(); i++){
                                MusicData musicData2 = arrayList2.get(i);
                                String strTitle2 = musicData2.getMp3Title();
                                if(edtTitle.getText().toString() == strTitle2){
                                    arrayList2.remove(edtTitle.getText().toString());
                                }
                            }
                            adapter.notifyDataSetChanged();
                            sqLiteDatabase.close();
                            Toast.makeText(getApplicationContext(), "수정성공", Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(), "다시 조회버튼을 눌러주세요.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(edtTitle.getText().toString().equals("")){
                            Toast.makeText(getApplicationContext(), "제목을 입력해주세요.", Toast.LENGTH_SHORT).show();
                        }else{
                            sqLiteDatabase = myDBHelper.getWritableDatabase();
                            sqLiteDatabase.execSQL("DELETE FROM myTBL WHERE title = '"+edtTitle.getText().toString()+"';");
                            mp3MusicDataArrayList.remove(edtTitle.getText().toString());
                            sqLiteDatabase.close();
                            Toast.makeText(getApplicationContext(), "삭제성공", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

                btnSearch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ArrayList<MusicData> arrayList = myDBHelper.selectMyTBL();

                        mp3MusicDataArrayList.clear();
                        for(int i = 0; i < arrayList.size(); i++){
                            MusicData musicData2 = arrayList.get(i);
                            mp3MusicDataArrayList.add(musicData2);
                        }
                        adapter.notifyDataSetChanged();

                        Toast.makeText(getApplicationContext(), "조회성공", Toast.LENGTH_SHORT).show();
                    }
                });


                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        MusicData selectedMP32 = mp3MusicDataArrayList.get(position);

                        edtTitle.setText(selectedMP32.getMp3Title());
                        edtSinger.setText(selectedMP32.getMp3Singer());
                        edtMp3picture.setText(selectedMP32.getMp3Picture());
                        String str = edtMp3picture.getText().toString();
                        String result = str.substring(13);
                        String mp3Picture = result.replace(".jpg","");
                        edtMp3picture.setText(mp3Picture);
                    }
                });

                dialog.setNegativeButton("나가기", null);

                dialog.show();
                break;
            default: break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuListAdd:
                View dialogView = View.inflate(getApplicationContext(), R.layout.music_dialog3, null);

                TextView textView = dialogView.findViewById(R.id.textView);
                EditText edtListTitle = dialogView.findViewById(R.id.edtListTitle);

                AlertDialog.Builder dialog = new AlertDialog.Builder(Main2Activity.this);
                dialog.setTitle("내 음악 리스트 추가");
                dialog.setView(dialogView);

                dialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                dialog.setNegativeButton("나가기", null);

                dialog.show();
                break;
            case R.id.menuAdd :
                View dialogView2 = View.inflate(getApplicationContext(), R.layout.music_dialog3, null);

                TextView textView2 = dialogView2.findViewById(R.id.textView);
                final EditText edtListTitle2 = dialogView2.findViewById(R.id.edtListTitle);

                AlertDialog.Builder dialog2 = new AlertDialog.Builder(Main2Activity.this);
                dialog2.setTitle("내 음악 리스트 추가");
                dialog2.setView(dialogView2);

                dialog2.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mp3ArrayList.add(edtListTitle2.getText().toString());
                    }
                });

                dialog2.setNegativeButton("나가기", null);

                dialog2.show();
                break;
            case R.id.menuList:
                linearSlidingLayout.bringToFront();
                handleMenuBarListener();
                break;
            default: break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void handleMenuBarListener() {
        if(btnflag == true){
            linearSlidingLayout.startAnimation(translate_right);
            btnflag = false;
        }else{
            linearSlidingLayout.startAnimation(translate_left);
            btnflag = true;
        }
    }


}
