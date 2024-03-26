package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    VBox bankerAllocationBox;
    @FXML
    VBox bankerMaxBox;
    @FXML
    TextField numProcessesField1;
    @FXML
    TextField numResourcesField1;
    @FXML
    Button goBtn1;
    @FXML
    HBox bankerAvailableBox;
    @FXML
    Button runButton1;
    @FXML
    Text bankerResult;

    @FXML
    VBox ddAllocationBox;
    @FXML
    VBox ddRequestBox;
    @FXML
    TextField numProcessesField2;
    @FXML
    TextField numResourcesField2;
    @FXML
    Button goBtn2;
    @FXML
    HBox ddAvailableBox;
    @FXML
    Button runButton2;
    @FXML
    Text ddResult;
    public void createTableBanker() {
        bankerResult.setText("");
        bankerAllocationBox.getChildren().clear();
        bankerMaxBox.getChildren().clear();

        int n = Integer.parseInt(numProcessesField1.getText());
        int m = Integer.parseInt(numResourcesField1.getText());

        for (int i = 0; i < n; ++i) {
            HBox box = new HBox();
            box.setSpacing(10);
            Text text = new Text("P" + i);
            box.getChildren().add(text);
            for (int j = 0; j < m; ++j) {
                TextField textField = new TextField();
                textField.setPrefWidth(30);
                textField.setPrefHeight(30);
                box.getChildren().add(textField);
            }
            bankerAllocationBox.getChildren().add(box);
        }

        for (int i = 0; i < n; ++i) {
            HBox box = new HBox();
            box.setSpacing(10);
            Text text = new Text("P" + i);
            box.getChildren().add(text);
            for (int j = 0; j < m; ++j) {
                TextField textField = new TextField();
                textField.setPrefWidth(30);
                textField.setPrefHeight(30);
                box.getChildren().add(textField);
            }
            bankerMaxBox.getChildren().add(box);
        }

        bankerAvailableBox.getChildren().clear();
        for (int i = 0; i < m; ++i) {
            TextField textField = new TextField();
            textField.setPrefWidth(30);
            textField.setPrefHeight(30);
            bankerAvailableBox.getChildren().add(textField);
        }
    }

    public void runBanker() {
        int n = bankerAllocationBox.getChildren().size();
        int m = ((HBox) bankerAllocationBox.getChildren().get(0)).getChildren().size() - 1;

        int available[] = new int[m];
        int max[][] = new int[n][m];
        int allocation[][] = new int[n][m];

        for (int i = 0; i < m; ++i) {
            available[i] = Integer.parseInt(((TextField) bankerAvailableBox.getChildren().get(i)).getText());
        }

        for (int i = 0; i < n; ++i) {
            HBox box = (HBox) bankerAllocationBox.getChildren().get(i);
            for (int j = 0; j < m; ++j) {
                allocation[i][j] = Integer.parseInt(((TextField) box.getChildren().get(j + 1)).getText());
            }
        }

        for (int i = 0; i < n; ++i) {
            HBox box = (HBox) bankerMaxBox.getChildren().get(i);
            for (int j = 0; j < m; ++j) {
                max[i][j] = Integer.parseInt(((TextField) box.getChildren().get(j + 1)).getText());
            }
        }

        Banker banker = new Banker(available, max, allocation);
        List<Integer> order = banker.solve();
        if (order.size() == n) {
            String s = "Safe order: ";
            for (int i = 0; i < n; ++i) {
                s += "P" + order.get(i);
                if (i < n - 1) {
                    s += " -> ";
                }
            }
            bankerResult.setText(s);
        } else {
            bankerResult.setText("Unsafe!");
        }
    }


    public void createTableDd() {
        ddResult.setText("");
        ddAllocationBox.getChildren().clear();
        ddRequestBox.getChildren().clear();

        int n = Integer.parseInt(numProcessesField2.getText());
        int m = Integer.parseInt(numResourcesField2.getText());

        for (int i = 0; i < n; ++i) {
            HBox box = new HBox();
            box.setSpacing(10);
            Text text = new Text("P" + i);
            box.getChildren().add(text);
            for (int j = 0; j < m; ++j) {
                TextField textField = new TextField();
                textField.setPrefWidth(30);
                textField.setPrefHeight(30);
                box.getChildren().add(textField);
            }
            ddAllocationBox.getChildren().add(box);
        }

        for (int i = 0; i < n; ++i) {
            HBox box = new HBox();
            box.setSpacing(10);
            Text text = new Text("P" + i);
            box.getChildren().add(text);
            for (int j = 0; j < m; ++j) {
                TextField textField = new TextField();
                textField.setPrefWidth(30);
                textField.setPrefHeight(30);
                box.getChildren().add(textField);
            }
            ddRequestBox.getChildren().add(box);
        }

        ddAvailableBox.getChildren().clear();
        for (int i = 0; i < m; ++i) {
            TextField textField = new TextField();
            textField.setPrefWidth(30);
            textField.setPrefHeight(30);
            ddAvailableBox.getChildren().add(textField);
        }
    }

    public void runDd() {
        int n = ddAllocationBox.getChildren().size();
        int m = ((HBox) ddAllocationBox.getChildren().get(0)).getChildren().size() - 1;

        int available[] = new int[m];
        int request[][] = new int[n][m];
        int allocation[][] = new int[n][m];

        for (int i = 0; i < m; ++i) {
            available[i] = Integer.parseInt(((TextField) ddAvailableBox.getChildren().get(i)).getText());
        }

        for (int i = 0; i < n; ++i) {
            HBox box = (HBox) ddAllocationBox.getChildren().get(i);
            for (int j = 0; j < m; ++j) {
                allocation[i][j] = Integer.parseInt(((TextField) box.getChildren().get(j + 1)).getText());
            }
        }

        for (int i = 0; i < n; ++i) {
            HBox box = (HBox) ddRequestBox.getChildren().get(i);
            for (int j = 0; j < m; ++j) {
                request[i][j] = Integer.parseInt(((TextField) box.getChildren().get(j + 1)).getText());
            }
        }

        DeadlockDetection deadlockDetection = new DeadlockDetection(available, request, allocation);
        List<Integer> deadlock = deadlockDetection.solve();
        if (deadlock.size() == 0) {
            ddResult.setText("No deadlock!");
        } else {
            ddResult.setText("Deadlock detected! Processes: " + deadlock);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}