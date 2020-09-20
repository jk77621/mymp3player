package com.example.mymp3project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Main3Activity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvTitle;
    private TextView tvTitle2;
    private MyImageView imgMusic;
    private TextView tvLyrics;
    private SeekBar seekBar2;
    private ImageButton btnChange;
    private ImageButton btnStart2;
    private ImageButton btnPause2;
    private ImageButton btnStop2;
    private ImageButton btnMenu;
    private ProgressBar progressBar;

    private int mp3position;
    private String sdcardPath;
    private String mp3Title;
    private String mp3Singer;
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        setTitle("SOUND WAVE");

        findViewByIdFuction();

        requestPermissionsFunction();

        sdcardPath = Environment.getExternalStorageDirectory().getPath()+"/";

        Intent intent = getIntent();
        if(intent != null){
            mp3position = intent.getIntExtra("mp3Position",0);
            mp3Title = intent.getStringExtra("mp3Title");
            mp3Singer = intent.getStringExtra("mp3Singer");
        }

        imgMusicSettingsFunction();

        btnStateChange(true, false, false, View.INVISIBLE);

        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

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
    }
    private void imgMusicSettingsFunction() {
        //외장하드에 있는 이미지를 가져온다.
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        final File[] imageFileArray = new File(path + "/image").listFiles();
        imgMusic.setImagePath(imageFileArray[mp3position].toString());
        tvLyrics.setMovementMethod(new ScrollingMovementMethod());
        try {
            switch (mp3Title) {
                case "iu.mp3":
                    imgMusic.setImagePath(imageFileArray[2].toString());
                    tvTitle.setText(mp3Title);
                    tvTitle2.setText(mp3Singer);
                    try {
                        FileInputStream fin = new FileInputStream("/sdcard/Lyrics/iu.txt");
                        byte[] content = new byte[fin.available()];
                        fin.read(content);
                        fin.close();
                        tvLyrics.setText(new String(content));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    imgMusic.invalidate();
                    break;
                case "hyuk5.mp3":
                    imgMusic.setImagePath(imageFileArray[1].toString());
                    tvTitle.setText(mp3Title);
                    tvTitle2.setText(mp3Singer);
                    try {
                        FileInputStream fin = new FileInputStream("/sdcard/Lyrics/hyuk5.txt");
                        byte[] content = new byte[fin.available()];
                        fin.read(content);
                        fin.close();
                        tvLyrics.setText(new String(content));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    imgMusic.invalidate();
                    break;
                case "bes.mp3":
                    imgMusic.setImagePath(imageFileArray[0].toString());
                    tvTitle.setText(mp3Title);
                    tvTitle2.setText(mp3Singer);
                    try {
                        FileInputStream fin = new FileInputStream("/sdcard/Lyrics/bes.txt");
                        byte[] content = new byte[fin.available()];
                        fin.read(content);
                        fin.close();
                        tvLyrics.setText(new String(content));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    imgMusic.invalidate();
                    break;
                case "beach.mp3":
                    imgMusic.setImagePath(imageFileArray[3].toString());
                    tvTitle.setText(mp3Title);
                    tvTitle2.setText(mp3Singer);
                    try {
                        FileInputStream fin = new FileInputStream("/sdcard/Lyrics/beach.txt");
                        byte[] content = new byte[fin.available()];
                        fin.read(content);
                        fin.close();
                        tvLyrics.setText(new String(content));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    imgMusic.invalidate();
                    break;
                case "flower.mp3":
                    imgMusic.setImagePath(imageFileArray[4].toString());
                    tvTitle.setText(mp3Title);
                    tvTitle2.setText(mp3Singer);
                    try {
                        FileInputStream fin = new FileInputStream("/sdcard/Lyrics/flower.txt");
                        byte[] content = new byte[fin.available()];
                        fin.read(content);
                        fin.close();
                        tvLyrics.setText(new String(content));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    imgMusic.invalidate();
                    break;
                case "goodbye.mp3":
                    imgMusic.setImagePath(imageFileArray[5].toString());
                    tvTitle.setText(mp3Title);
                    tvTitle2.setText(mp3Singer);
                    try {
                        FileInputStream fin = new FileInputStream("/sdcard/Lyrics/goodbye.txt");
                        byte[] content = new byte[fin.available()];
                        fin.read(content);
                        fin.close();
                        tvLyrics.setText(new String(content));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    imgMusic.invalidate();
                    break;
                case "leaves.mp3":
                    imgMusic.setImagePath(imageFileArray[6].toString());
                    tvTitle.setText(mp3Title);
                    tvTitle2.setText(mp3Singer);
                    try {
                        FileInputStream fin = new FileInputStream("/sdcard/Lyrics/leaves.txt");
                        byte[] content = new byte[fin.available()];
                        fin.read(content);
                        fin.close();
                        tvLyrics.setText(new String(content));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    imgMusic.invalidate();
                    break;
                case "maria.mp3":
                    imgMusic.setImagePath(imageFileArray[7].toString());
                    tvTitle.setText(mp3Title);
                    tvTitle2.setText(mp3Singer);
                    try {
                        FileInputStream fin = new FileInputStream("/sdcard/Lyrics/maria.txt");
                        byte[] content = new byte[fin.available()];
                        fin.read(content);
                        fin.close();
                        tvLyrics.setText(new String(content));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    imgMusic.invalidate();
                    break;
                case "summer.mp3":
                    imgMusic.setImagePath(imageFileArray[8].toString());
                    tvTitle.setText(mp3Title);
                    tvTitle2.setText(mp3Singer);
                    try {
                        FileInputStream fin = new FileInputStream("/sdcard/Lyrics/summer.txt");
                        byte[] content = new byte[fin.available()];
                        fin.read(content);
                        fin.close();
                        tvLyrics.setText(new String(content));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    imgMusic.invalidate();
                    break;
                case "summerhate.mp3":
                    imgMusic.setImagePath(imageFileArray[9].toString());
                    tvTitle.setText(mp3Title);
                    tvTitle2.setText(mp3Singer);
                    try {
                        FileInputStream fin = new FileInputStream("/sdcard/Lyrics/summerhate.txt");
                        byte[] content = new byte[fin.available()];
                        fin.read(content);
                        fin.close();
                        tvLyrics.setText(new String(content));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    imgMusic.invalidate();
                    break;
                default:
                    break;
            }
        } catch (NullPointerException e){
            imgMusic.setImagePath(imageFileArray[2].toString());
            tvTitle.setText("iu.mp3");
            tvTitle2.setText("아이유");
            try {
                FileInputStream fin = new FileInputStream("/sdcard/Lyrics/iu.txt");
                byte[] content = new byte[fin.available()];
                fin.read(content);
                fin.close();
                tvLyrics.setText(new String(content));
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            imgMusic.invalidate();
        }
    }

    private void btnStateChange(boolean b, boolean b1, boolean b2, int invisible) {
        btnStart2.setEnabled(b);
        btnPause2.setEnabled(b1);
        btnStop2.setEnabled(b2);
        progressBar.setVisibility(invisible);
    }

    private void requestPermissionsFunction() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);
    }

    private void findViewByIdFuction() {
        tvTitle = findViewById(R.id.tvTitle);
        tvTitle2 = findViewById(R.id.tvTitle2);
        imgMusic = findViewById(R.id.imgMusic);
        tvLyrics = findViewById(R.id.tvLyrics);
        seekBar2 = findViewById(R.id.seekBar2);
        btnChange = (ImageButton) findViewById(R.id.btnChange);
        btnStart2 = (ImageButton)  findViewById(R.id.btnStart2);
        btnPause2 = (ImageButton) findViewById(R.id.btnPause2);
        btnStop2 =  (ImageButton) findViewById(R.id.btnStop2);
        btnMenu = (ImageButton) findViewById(R.id.btnMenu);
        progressBar = findViewById(R.id.progressBar);

        btnChange.setOnClickListener(this);
        btnStart2.setOnClickListener(this);
        btnPause2.setOnClickListener(this);
        btnStop2.setOnClickListener(this);
        btnMenu.setOnClickListener(this);
        tvLyrics.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                View dialogView = View.inflate(getApplicationContext(), R.layout.music_dialog, null);

                ImageView dimageView = dialogView.findViewById(R.id.dimageView);
                TextView dtextView = dialogView.findViewById(R.id.dtextView);
                TextView dtextView2 = dialogView.findViewById(R.id.dtextView2);
                final TextView dtextView3 = dialogView.findViewById(R.id.dtextView3);

                AlertDialog.Builder dialog = new AlertDialog.Builder(Main3Activity.this);
                dialog.setTitle("내 음악 정보");
                dialog.setView(dialogView);
                try {
                    switch (mp3Title) {
                        case "iu.mp3":
                            dimageView.setImageResource(R.drawable.iu);
                            try {
                                FileInputStream fin = new FileInputStream("/sdcard/Lyrics/iu.txt");
                                byte[] content = new byte[fin.available()];
                                fin.read(content);
                                fin.close();
                                dtextView3.setText(new String(content));
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            break;
                        case "hyuk5.mp3":
                            dimageView.setImageResource(R.drawable.hyuk5);
                            try {
                                FileInputStream fin = new FileInputStream("/sdcard/Lyrics/hyuk5.txt");
                                byte[] content = new byte[fin.available()];
                                fin.read(content);
                                fin.close();
                                dtextView3.setText(new String(content));
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                            break;
                        case "bes.mp3":
                            dimageView.setImageResource(R.drawable.bes);
                            try {
                                FileInputStream fin = new FileInputStream("/sdcard/Lyrics/bes.txt");
                                byte[] content = new byte[fin.available()];
                                fin.read(content);
                                fin.close();
                                dtextView3.setText(new String(content));
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                            break;
                        case "beach.mp3":
                            dimageView.setImageResource(R.drawable.beach);
                            try {
                                FileInputStream fin = new FileInputStream("/sdcard/Lyrics/beach.txt");
                                byte[] content = new byte[fin.available()];
                                fin.read(content);
                                fin.close();
                                dtextView3.setText(new String(content));
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                            break;
                        case "flower.mp3":
                            dimageView.setImageResource(R.drawable.flower);
                            try {
                                FileInputStream fin = new FileInputStream("/sdcard/Lyrics/flower.txt");
                                byte[] content = new byte[fin.available()];
                                fin.read(content);
                                fin.close();
                                dtextView3.setText(new String(content));
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                            break;
                        case "goodbye.mp3":
                            dimageView.setImageResource(R.drawable.goodbye);
                            try {
                                FileInputStream fin = new FileInputStream("/sdcard/Lyrics/goodbye.txt");
                                byte[] content = new byte[fin.available()];
                                fin.read(content);
                                fin.close();
                                dtextView3.setText(new String(content));
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                            break;
                        case "leaves.mp3":
                            dimageView.setImageResource(R.drawable.leaves);
                            try {
                                FileInputStream fin = new FileInputStream("/sdcard/Lyrics/leaves.txt");
                                byte[] content = new byte[fin.available()];
                                fin.read(content);
                                fin.close();
                                dtextView3.setText(new String(content));
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                            break;
                        case "maria.mp3":
                            dimageView.setImageResource(R.drawable.maria);
                            try {
                                FileInputStream fin = new FileInputStream("/sdcard/Lyrics/maria.txt");
                                byte[] content = new byte[fin.available()];
                                fin.read(content);
                                fin.close();
                                dtextView3.setText(new String(content));
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                            break;
                        case "summer.mp3":
                            dimageView.setImageResource(R.drawable.summer);
                            try {
                                FileInputStream fin = new FileInputStream("/sdcard/Lyrics/summer.txt");
                                byte[] content = new byte[fin.available()];
                                fin.read(content);
                                fin.close();
                                dtextView3.setText(new String(content));
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                            break;
                        case "summerhate.mp3":
                            dimageView.setImageResource(R.drawable.summerhate);
                            try {
                                FileInputStream fin = new FileInputStream("/sdcard/Lyrics/summerhate.txt");
                                byte[] content = new byte[fin.available()];
                                fin.read(content);
                                fin.close();
                                dtextView3.setText(new String(content));
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                            break;
                    }
                    dtextView.setText(mp3Title);
                    dtextView2.setText(mp3Singer);
                }catch (NullPointerException e4){
                    dimageView.setImageResource(R.drawable.iu);
                    dtextView.setText("iu.mp3");
                    dtextView2.setText("아이유");
                    try {
                        FileInputStream fin = new FileInputStream("/sdcard/Lyrics/iu.txt");
                        byte[] content = new byte[fin.available()];
                        fin.read(content);
                        fin.close();
                        dtextView3.setText(new String(content));
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                }



                dtextView3.setMovementMethod(new ScrollingMovementMethod());
                dialog.setNegativeButton("나가기", null);

                dialog.show();
                return true;
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnChange:
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(intent);
                break;
            case R.id.btnStart2:
                try {
                    mediaPlayer.setDataSource(sdcardPath + mp3Title);
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
                                    seekBar2.setMax(mediaPlayer.getDuration());
                                    seekBar2.setProgress(mediaPlayer.getCurrentPosition());
                                }
                                SystemClock.sleep(100);
                            }

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mediaPlayer.reset();
                                    seekBar2.setProgress(0);
                                    btnStart2.setEnabled(true);
                                    btnPause2.setEnabled(false);
                                    btnStop2.setEnabled(false);
                                    progressBar.setVisibility(View.INVISIBLE);
                                }
                            });
                        }
                    };
                    thread.start();
                    btnStart2.setEnabled(false);
                    btnPause2.setEnabled(true);
                    btnStop2.setEnabled(true);
                    progressBar.setVisibility(View.VISIBLE);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"음악을 선택해주세요.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnPause2:
                if(flag == false){
                    mediaPlayer.pause();
//                    btnPause2.setTE("이어듣기");
                    btnPause2.setImageResource(R.drawable.pause3);
                    flag = true;
                    btnStart2.setEnabled(false);
                    btnStop2.setEnabled(true);
                    progressBar.setVisibility(View.INVISIBLE);
                }else{
                    mediaPlayer.start();
//                    btnPause2.setText("일시정지");
                    btnPause2.setImageResource(R.drawable.pause);
                    flag = false;
                    btnStart2.setEnabled(false);
                    btnStop2.setEnabled(true);
                    progressBar.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.btnStop2:
                mediaPlayer.stop();
                //미디어플레이어 초기화시킨다.
                mediaPlayer.reset();
                btnStart2.setEnabled(true);
                btnStop2.setEnabled(false);
                btnPause2.setEnabled(false);
                btnPause2.setImageResource(R.drawable.pause);
                progressBar.setVisibility(View.INVISIBLE);
                break;
            case R.id.btnMenu:
                Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent2);
                break;
            default: break;
        }
    }
}
