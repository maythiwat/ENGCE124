package com.maythiwat.engce124.lab4v2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Modality;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class PlayerScene {
    private MediaPlayer mediaPlayer;

    @FXML
    private AnchorPane ap;
    @FXML
    private ListView<File> listView;

    private int playingIndex = -1;
    private DList playlist;

    @FXML
    public void initialize() {
        IO.println("Player Scene Loaded.");

        playlist = new DList();

        // Config ListView
        listView.setCellFactory(lv -> {
            ListCell<File> cell = new ListCell<>() {
                @Override
                protected void updateItem(File item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty || item == null) {
                        setText(null);
                    } else if (getIndex() == playingIndex) {
                        setText("▶ " + item.getName());
                    } else {
                        setText(item.getName());
                    }
                }
            };

            cell.setOnMouseClicked(event -> {
                if (!cell.isEmpty() && event.getClickCount() == 2) {
                    System.out.println("Double-clicked on: " + cell.getItem());

                    playingIndex = cell.getIndex();
                    listView.refresh();

                    playMediaFile(cell.getItem());
                }
            });

            return cell;
        });

        refreshListView();
        winampIntro();
    }

    private void refreshListView() {
        List<File> tempList = new ArrayList<>();
        DNode travel = playlist.getNodeAt(0);
        while (travel != null) {
            tempList.add(travel.getInfo());
            travel = travel.getrLink();
        }

        ObservableList<File> items = FXCollections.observableArrayList(tempList);
        listView.setItems(items);
        listView.refresh();
    }

    private void playMediaFile(File file) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
            mediaPlayer = null;
        }

        Media media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        IO.println("Now playing: " + file.getName());

        mediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer.dispose();
            if (playlist.size() > 1 && playingIndex < (playlist.size() - 1)) {
                playingIndex = playingIndex + 1;
                playMediaFile(playlist.getNodeAt(playingIndex).getInfo());
                refreshListView();
            }
        });
    }

    private void winampIntro() {
        Media media = new Media(Objects.requireNonNull(getClass().getResource("/media/demo.wav")).toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    private void openFileChooser(int mode) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose media file");
        // fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.setInitialDirectory(new File("C:\\Users\\Maythiwat\\Downloads\\ytdl"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Media", "*.wav", "*.mp3", "*.m4a"));

        if (mode == 0) {
            List<File> selectedFiles = fileChooser.showOpenMultipleDialog(ap.getScene().getWindow());
            if (selectedFiles != null && !selectedFiles.isEmpty()) {
                selectedFiles.forEach(f -> {
                    playlist.append(f);
                });

                if (playingIndex == -1) {
                    playingIndex = 0;
                    playMediaFile(playlist.getNodeAt(0).getInfo());
                } else {
                    if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.DISPOSED) {
                        playingIndex = playlist.size() - 1;
                        playMediaFile(playlist.getNodeAt(playingIndex).getInfo());
                    }
                }

                refreshListView();
            }
        } else {
            if (listView.getFocusModel().getFocusedItem() != null) {
                int target = listView.getFocusModel().getFocusedIndex();
                File selectedFile = fileChooser.showOpenDialog(ap.getScene().getWindow());
                if (selectedFile != null) {
                    if (mode == 1) {
                        playlist.insertBefore(target, selectedFile);
                    } else {
                        playlist.insertAfter(target, selectedFile);
                    }

                    if (target < playingIndex || (mode == 1 && target == playingIndex)) {
                        playingIndex = playingIndex + 1;
                    }

                    refreshListView();
                }
            }
        }
    }

    @FXML
    public void onAddSongBtnAction() {
        openFileChooser(0);
    }

    @FXML
    public void onInsBeforeBtnAction() {
        openFileChooser(1);
    }

    @FXML
    public void onInsAfterBtnAction() {
        openFileChooser(2);
    }

    @FXML
    public void onDeleteBtnAction() {
        if (listView.getFocusModel().getFocusedItem() != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Sure?");
            alert.setHeaderText("Are you sure you want to delete:");
            alert.setContentText(listView.getFocusModel().getFocusedItem().getName());
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(ap.getScene().getWindow());

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                int target = listView.getFocusModel().getFocusedIndex();

                if (mediaPlayer != null && playingIndex == target) {
                    playingIndex = -1;
                    mediaPlayer.stop();
                    mediaPlayer.dispose();
                    mediaPlayer = null;
                }

                playlist.deleteAt(target);

                if (target < playingIndex) {
                    playingIndex = playingIndex - 1;
                }

                refreshListView();
            }
        }
    }

    @FXML
    public void onPlayBtnAction() {
        if (mediaPlayer != null) {
            if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                mediaPlayer.pause();
            } else {
                mediaPlayer.play();
            }
        }
    }

    @FXML
    public void onNextBtnAction() {
        if (playingIndex != -1 && !playlist.isEmpty()) {
            DNode currentNode = playlist.getNodeAt(playingIndex);
            if (currentNode.getrLink() != null) {
                playMediaFile(currentNode.getrLink().getInfo());
                playingIndex = playingIndex + 1;
                refreshListView();
            }
        }
    }

    @FXML
    public void onPrevBtnAction() {
        if (playingIndex > 0 && !playlist.isEmpty()) {
            DNode currentNode = playlist.getNodeAt(playingIndex);
            if (currentNode.getlLink() != null) {
                playMediaFile(currentNode.getlLink().getInfo());
                playingIndex = playingIndex - 1;
                refreshListView();
            }
        }
    }
}
