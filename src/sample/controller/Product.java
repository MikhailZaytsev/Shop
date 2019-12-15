package sample.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import sample.assets.Constant;
import sample.assets.Crutch;

public class Product extends Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label person;

    @FXML
    private Label permision;

    @FXML
    private TextField name;

    @FXML
    private TextField factory;

    @FXML
    private TextField price;

    @FXML
    private TextArea description;

    @FXML
    private Button add;

    @FXML
    private TextField id;

    @FXML
    private Button delete;

    @FXML
    private Button change;

    @FXML
    private AnchorPane productChange;

    @FXML
    private Button home;

    @FXML
    private Button yes;

    @FXML
    private Button no;

    @FXML
    private TextField nameCH;

    @FXML
    private TextField factoryCH;

    @FXML
    private TextField priceCH;

    @FXML
    private TextField count;

    @FXML
    private TextArea descriptionCH;

    @FXML
    private AnchorPane delProduct;

    @FXML
    private Label delName;

    @FXML
    private Button delYes;

    @FXML
    private Button delNo;


    @FXML
    void initialize() {
        person.setText(Crutch.name);
        permision.setText(Crutch.per);

        change.setOnAction(event -> {
            productChange.setVisible(true);

            try {
                resultSet = handler.getProduct(Integer.parseInt(id.getText()));

                while (resultSet.next()) {
                    nameCH.setText(resultSet.getString(Constant.PRD_NAME));
                    factoryCH.setText(resultSet.getString(Constant.PRD_FACTORY));
                    priceCH.setText(resultSet.getString(Constant.PRD_PRICE));
                    descriptionCH.setText(resultSet.getString(Constant.PRD_DESCRIPTION));
                    count.setText(resultSet.getString(Constant.PRD_COUNT));
                }
            } catch (SQLException e) {
                e.printStackTrace();
                productChange.setVisible(false);
            }
        });

        yes.setOnAction(event -> {
            goods.setName(nameCH.getText());
            goods.setFactory(factoryCH.getText());
            goods.setPrice(Float.parseFloat(priceCH.getText()));
            goods.setDescription(descriptionCH.getText());
            goods.setCount(Integer.parseInt(count.getText()));
            handler.updateProduct(Integer.parseInt(id.getText()), goods);
            productChange.setVisible(false);
        });

        no.setOnAction(event -> {
            productChange.setVisible(false);
        });

        home.setOnAction(event -> {
            openWindow(home, Constant.START);
        });

        delete.setOnAction(event -> {
            delProduct.setVisible(true);
            try {
                resultSet = handler.getProduct(Integer.parseInt(id.getText()));
                while (resultSet.next()) {
                    delName.setText(resultSet.getString(Constant.PRD_NAME));
                }
            } catch (SQLException e) {
                e.printStackTrace();
                delProduct.setVisible(false);
            }
        });

        delYes.setOnAction(event -> {
            handler.deleteProduct(Integer.parseInt(id.getText()));
            delProduct.setVisible(false);
        });

        delNo.setOnAction(event -> {
            delProduct.setVisible(false);
        });

        add.setOnAction(event -> {
            if (name.getText().equals("") || factory.getText().equals("") ||
                    price.getText().equals("") || description.getText().equals("")) return;

            try {
                goods.setName(name.getText());
                goods.setFactory(factory.getText());
                goods.setPrice(Float.parseFloat(price.getText()));
                goods.setDescription(description.getText());

                handler.addProduct(goods);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        });
    }
}