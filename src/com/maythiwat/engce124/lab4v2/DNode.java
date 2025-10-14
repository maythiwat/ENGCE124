package com.maythiwat.engce124.lab4v2;

import java.io.File;

public class DNode {
    DNode lLink, rLink;
    File info;

    public DNode(File data){
        info = data;
    }

    public File getInfo() {
        return info;
    }

    public DNode getlLink() {
        return lLink;
    }

    public DNode getrLink() {
        return rLink;
    }
}
