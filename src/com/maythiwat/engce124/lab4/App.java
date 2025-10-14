package com.maythiwat.engce124.lab4;

import com.maythiwat.engce124.lab4.forms.Player;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        DList playlist = new DList();

//        for (int i = 0; i < 50; i++) {
//            playlist.append("Test Song #" + (i + 1));
//        }

        playlist.append("คำภีร์ - หนุ่มน้อย");
        playlist.append("เบิร์ด ธงไชย - ซ่อมได้");
        playlist.append("ปู พงษ์สิทธิ์ - ตลอดเวลา");
        playlist.append("คาราบาว - บัวลอย");
        playlist.append("เสก โลโซ - คืนจันทร์");
        playlist.append("บอดี้สแลม - ความเชื่อ");
        playlist.append("โปเตโต้ - ที่เดิม");
        playlist.append("บิ๊กแอส - เล่นของสูง");

        SwingUtilities.invokeLater(() -> {
            Player player = new Player();
            player.start(playlist);
        });
    }
}
