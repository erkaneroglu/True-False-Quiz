package com.example.truefalse.Modal;

public class Item {
    private String question ,answer, hint;
    private int images;

    public Item(String question, String answer, String hint, int imageList) {
        this.question = question;
        this.answer = answer;
        this.hint = hint;
        this.images = imageList;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getHint() {
        return hint;
    }

    public int getImages(){
        return images;
    }
}
