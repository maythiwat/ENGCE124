package com.maythiwat.engce124.lab4.forms;

import com.maythiwat.engce124.lab4.DList;
import com.maythiwat.engce124.lab4.DNode;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.media.Media;

public class Player {
    private JPanel contentPane;
    private JList<String> list1;
    private JButton addButton;
    private JButton insBefButton;
    private JButton insAftButton;
    private JButton delButton;

    private final JFrame frame;
    private final DefaultListModel<String> listModel;

    private DList playlist;

    public Player() {
        frame = new JFrame("JavAmp: Playlist Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(contentPane);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);

        Font font = new Font("Anuphan", Font.BOLD, 14);
        UIManager.put("Button.font", font);
        UIManager.put("Label.font", font);
        UIManager.put("TextField.font", font);

        listModel = new DefaultListModel<>();
        list1.setModel(listModel);

        final JFXPanel fxPanel = new JFXPanel();
        frame.add(fxPanel);

        addButton.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(frame, "Song name to add:", "Add", JOptionPane.QUESTION_MESSAGE);
            if (name != null && !name.trim().isEmpty()) {
                playlist.append(name);
                refreshListView();
            }
        });

        delButton.addActionListener(e -> onDeleteButton());

        insBefButton.addActionListener(e -> {
            System.out.println(list1.getSelectedIndex() + " -> " + list1.getSelectedValue());
            if (list1.getSelectedValue() == null) {
                JOptionPane.showMessageDialog(frame, "Please select song");
                return;
            }

            String name = JOptionPane.showInputDialog(frame, "Song name to insert:", "Insert before: " + list1.getSelectedValue(), JOptionPane.QUESTION_MESSAGE);
            if (name != null && !name.trim().isEmpty()) {
                int i = list1.getSelectedIndex();
                playlist.insertBefore(i, name);
                refreshListView();
                list1.setSelectedIndex(i);
            }
        });

        insAftButton.addActionListener(e -> {
            System.out.println(list1.getSelectedIndex() + " -> " + list1.getSelectedValue());
            if (list1.getSelectedValue() == null) {
                JOptionPane.showMessageDialog(frame, "Please select song");
                return;
            }

            String name = JOptionPane.showInputDialog(frame, "Song name to insert:", "Insert after: " + list1.getSelectedValue(), JOptionPane.QUESTION_MESSAGE);
            if (name != null && !name.trim().isEmpty()) {
                int i = list1.getSelectedIndex();
                playlist.insertAfter(i, name);
                refreshListView();
                list1.setSelectedIndex(i + 1);
            }
        });

        InputMap inputMap = list1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "DelKey");
        list1.getActionMap().put("DelKey", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onDeleteButton();
            }
        });
    }

    private void onDeleteButton() {
        if (list1.getSelectedValue() != null) {
            int i = list1.getSelectedIndex();
            playlist.deleteAt(i);
            refreshListView();
            if (!playlist.isEmpty()) {
                list1.setSelectedIndex(i == 0 ? 0 : i - 1);
            }
        }
    }

    private void refreshListView() {
        listModel.clear();

        List<String> list = new ArrayList<>();
        DNode travel = playlist.getNodeAt(0);
        while (travel != null) {
            list.add(travel.getInfo());
            travel = travel.getrLink();
        }

        listModel.addAll(list);
    }

    public void start(DList playlist) {
        this.playlist = playlist;
        refreshListView();
        frame.setVisible(true);

//        try {
//            File f = new File("./resources/demo.wav");
//            AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
//            Clip clip = AudioSystem.getClip();
//            clip.open(audioIn);
//            clip.start();
//        } catch (Exception ex) {
//            // Do nothing
//        }
    }
}
