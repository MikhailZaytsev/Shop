package sample.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sample.assets.Constant;
import sample.assets.Crutch;

public class Decision extends Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button home;

    @FXML
    private Label person;

    @FXML
    private Label permission;

    @FXML
    private Button employee;

    @FXML
    private Button sale;

    @FXML
    private Button product;

    @FXML
    void initialize() {
        person.setText(Crutch.name);
        permission.setText(Crutch.per);

        home.setOnAction(event -> {
            System.out.println(person.getText());
            openWindow(home, Constant.START);
        });

        product.setOnAction(event -> {
            if (permission.getText().equals("Кассир")) return;
            openWindow(product, Constant.PRODUCT);
        });

        employee.setOnAction(event -> {
            if (permission.getText().equals("Кассир") ||
                    permission.getText().equals("Товаровед")) return;
            openWindow(employee, Constant.EMPLOYER);
        });

        sale.setOnAction(event -> {
            openWindow(sale, Constant.SALE);
        });
    }
}
