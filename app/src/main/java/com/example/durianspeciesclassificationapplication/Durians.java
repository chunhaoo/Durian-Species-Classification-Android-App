package com.example.durianspeciesclassificationapplication;

public class Durians {
    private String name;
    private String desc;
    private String rarity;
    private String source;
    private String features;
    private String taste;

    public Durians(){
    }

    public Durians(String name, String desc, String rarity, String source, String features, String taste) {
        this.name = name;
        this.desc = desc;
        this.rarity = rarity;
        this.source = source;
        this.features = features;
        this.taste = taste;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getFeatures(){ return features;}

    public String getTaste(){ return taste;}

    /*public int getImage(String name){

        switch(name){
            case "D24":
                return R.drawable.d24);
                break;
            case "D101":
                return R.drawable.d101;
                break;
            case "Musang King":
                return R.drawable.mk;
                break;
            case "D13":
                return R.drawable.d13;
                break;
        }
    }*/
}
