package com.example.userbanksampah;

public class StaticRvModel
{
    private final int image;
    private final String text;

    public StaticRvModel(int image, String text){
        this.image = image;
        this.text = text;
    }

    public int getImage() {
        return image;
    }

    public String getText() {
        return text;
    }
}
