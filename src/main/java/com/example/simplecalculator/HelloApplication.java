package com.example.simplecalculator;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    public float numberA;
    public float numberB;
    public float finalAnswer;

    @Override
    public void start(Stage stage) throws Exception {
        Label numAL = new Label("Number A:");
        Label numBL = new Label("Number B: ");
        Label answerL = new Label("Ans: ");

        TextField numA = new TextField("0");
        TextField numB = new TextField("0");
        numA.setMinWidth(700);
        numB.setMinWidth(700);
        TextField answer = new TextField("0");
        answer.setEditable(false);

        Button add = new Button("+");
        Button subtract = new Button("-");
        Button multiply = new Button("x");
        Button divide = new Button("÷");

        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                numberA = (Float.parseFloat(numA.getText()));
                numberB = (Float.parseFloat(numB.getText()));
                finalAnswer = numberA + numberB;
                answer.setText(String.valueOf(finalAnswer));
            }
        });

        subtract.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                numberA = (Float.parseFloat(numA.getText()));
                numberB = (Float.parseFloat(numB.getText()));
                finalAnswer = numberA - numberB;
                answer.setText(String.valueOf(finalAnswer));
            }
        });

        multiply.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                numberA = (Float.parseFloat(numA.getText()));
                numberB = (Float.parseFloat(numB.getText()));
                finalAnswer = numberA * numberB;
                answer.setText(String.valueOf(finalAnswer));
            }
        });

        divide.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                numberA = (Float.parseFloat(numA.getText()));
                numberB = (Float.parseFloat(numB.getText()));
                if(numberB == 0) {
                    answer.setText("Cannot Divide By 0");
                    return;
                }
                finalAnswer = numberA / numberB;
                answer.setText(String.valueOf(finalAnswer));
            }
        });

        FlowPane root = new FlowPane();
        root.getChildren().add(answerL);
        root.getChildren().add(answer);
        root.getChildren().add(numAL);
        root.getChildren().add(numA);
        root.getChildren().add(numBL);
        root.getChildren().add(numB);
        root.getChildren().add(add);
        root.getChildren().add(subtract);
        root.getChildren().add(multiply);
        root.getChildren().add(divide);

        Scene scene = new Scene(root,200,150);
        stage.setTitle("Calculator");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}