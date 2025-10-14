package com.maythiwat.engce124.lab4;

public class DNode {
    DNode lLink, rLink;
    String info;

    public DNode(String data){
        info = data;
    }

    public String getInfo() {
        return info;
    }

    public DNode getlLink() {
        return lLink;
    }

    public DNode getrLink() {
        return rLink;
    }
}
