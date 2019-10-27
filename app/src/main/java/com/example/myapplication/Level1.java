package com.example.myapplication;

import java.util.List;

public class Level1 {
    public Level1(int cardType) {
        setCardType(cardType);
    }

    private int cardType;
    private List<Level2> level2s;

    public List<Level2> getLevel2s() {
        return level2s;
    }

    public void setLevel2s(List<Level2> level2s) {
        this.level2s = level2s;
    }

    public int getCardType() {
        return cardType;
    }

    public void setCardType(int cardType) {
        this.cardType = cardType;
    }

    public static class Level2 {
        private String image;
        private String s1;
        private String s2;
        private String s3;
        private String s4;
        private String s5;
        private String s6;
        public Level2(String s){
            setS1(s);setS2(s);setS3(s);setS4(s);setS5(s);setS6(s);
        }
        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getS1() {
            return s1;
        }

        public void setS1(String s1) {
            this.s1 = s1;
        }

        public String getS2() {
            return s2;
        }

        public void setS2(String s2) {
            this.s2 = s2;
        }

        public String getS3() {
            return s3;
        }

        public void setS3(String s3) {
            this.s3 = s3;
        }

        public String getS4() {
            return s4;
        }

        public void setS4(String s4) {
            this.s4 = s4;
        }

        public String getS5() {
            return s5;
        }

        public void setS5(String s5) {
            this.s5 = s5;
        }

        public String getS6() {
            return s6;
        }

        public void setS6(String s6) {
            this.s6 = s6;
        }
    }
}
