package study.section09.injejeong.quiz;

import study.week2.section09.injejeong.quiz.Item;

public class Album extends Item {
    public String artist;

    public Album(String name, int price, String artist) {
        super(name, price);
        this.artist = artist;
    }

    @Override
    public void print() {
        super.print();
        System.out.println("- 아티스트: " + artist);
    }
}