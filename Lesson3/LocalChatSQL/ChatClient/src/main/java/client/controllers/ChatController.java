package client.controllers;


import client.NetworkClient;
import client.models.Network;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Scanner;

public class ChatController {

    @FXML
    public ListView<String> usersList;

    @FXML
    private Button sendButton;
    @FXML
    private TextArea chatHistory;
    @FXML
    private TextField textField;
    @FXML
    private Label usernameTitle;

    private Network network;

    private int countHMF; //сумма строк истории таблицы

    public void setLabel(String usernameTitle) {
        this.usernameTitle.setText(usernameTitle);
    } //Поменять текст в Label usernameTitle

    public void setNetwork(Network network) {
        this.network = network;
    }

    @FXML
    public void initialize() {
        usersList.setItems(FXCollections.observableArrayList(NetworkClient.USERS_TEST_DATA));
        sendButton.setOnAction(event -> ChatController.this.sendMessage());
        textField.setOnAction(event -> ChatController.this.sendMessage());
        addHistory();
    }
//TODO Метод, загружающий историю чата из файла (не более 100 строк) и удаляющий первые строки, если последняя строк >200
    private void addHistory() {
        File historyMesFile = new File("ChatClient/src/main/resources/HistoryMessege");

        try {
            Scanner scanner = new Scanner(historyMesFile);
            String[] hmf = new String[300];
            int i =0;
            while (scanner.hasNextLine()){
                hmf[i] = String.valueOf(scanner.nextLine());
                i++;
            }
            scanner.close();
            if(i>100 && i<=200){
                for (int j = i-100; j < i; j++) {
                    chatHistory.appendText(hmf[j]);
                    chatHistory.appendText(System.lineSeparator());
            }
            }else if (i>200) {
                try(FileWriter writer = new FileWriter(historyMesFile)) {
                    writer.write("");
                }
                try(FileWriter writer = new FileWriter(historyMesFile,true)) {
                    for (int del = i-100; del < i; del++) {
                        chatHistory.appendText(hmf[del]);
                        chatHistory.appendText(System.lineSeparator());
                        writer.write(hmf[del]+"\n");
                }
                }
            }else {
                for (int k = 0; k < i; k++) {
                    chatHistory.appendText(hmf[k]);
                    chatHistory.appendText(System.lineSeparator());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


//        try (FileInputStream fileIn = new FileInputStream(historyMesFile)) {
//
//            byte[] byteArr = new byte[4096];
//
//            while ((fileIn.read(byteArr))!=-1){
//
//                chatHistory.appendText(new String(byteArr));
//            }
//        } catch (IOException b) {
//            b.printStackTrace();
//
//        }
    }

    private void sendMessage() { //Отправка сообщения юзером
        String message = textField.getText(); //Чтение сообщения с поля
        appendMessage("Я: " + message); //Передача его методу appendMessage
        textField.clear(); //Очистить поле для надписи у клиента

        try {
            network.sendMessage(message); //отправим сообщение методу network для отправки сообщения на сервер

        } catch (IOException e) {
            e.printStackTrace();
            NetworkClient.showErrorMessage("Ошибка подключения", "Ошибка при отправке сообщения", e.getMessage());
        }

    }

    public void appendMessage(String message) {
        String timestamp = DateFormat.getInstance().format(new Date());
        chatHistory.appendText(timestamp); // добавление в окно чтения сообщений - Время отправки
        chatHistory.appendText(System.lineSeparator()); //Пробел
        chatHistory.appendText(message); //добавление в окно чтения сообщений - Сообщение
        chatHistory.appendText(System.lineSeparator()); //Пробел
        chatHistory.appendText(System.lineSeparator()); //Пробел
    }

    public void setUsernameTitle(String username) {

    }
}