package com.example.mymp3project;

public class MusicData {
    private int mp3Picture;
    private String mp3Title;
    private String mp3Singer;

    public MusicData(int mp3Picture, String mp3Title, String mp3Singer) {
        this.mp3Picture = mp3Picture;
        this.mp3Title = mp3Title;
        this.mp3Singer = mp3Singer;
    }

    public int getMp3Picture() {
        return mp3Picture;
    }

    public void setMp3Picture(int mp3Picture) {
        this.mp3Picture = mp3Picture;
    }

    public String getMp3Title() {
        return mp3Title;
    }

    public void setMp3Title(String mp3Title) {
        this.mp3Title = mp3Title;
    }

    public String getMp3Singer() {
        return mp3Singer;
    }

    public void setMp3Singer(String mp3Singer) {
        this.mp3Singer = mp3Singer;
    }
}
