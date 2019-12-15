package sample.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import sample.assets.Constant;
import sample.assets.Crutch;
import sample.assets.TableItem;

public class Sale extends Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label person;

    @FXML
    private Label permission;

    @FXML
    private TableView<TableItem> sale;

    @FXML
    private TableColumn<TableItem, String> productName;

    @FXML
    private TableColumn<TableItem, Integer> productCount;

    @FXML
    private TableColumn<TableItem, Float> productPrice;

    @FXML
    private TableColumn<TableItem, Float> productSumPrice;

    @FXML
    private TextField id;

    @FXML
    private Button add;

    @FXML
    private TextField count;

    @FXML
    private Button delete;

    @FXML
    private Button pay;

    @FXML
    private Label summary;

    @FXML
    private Button home;

    @FXML
    private AnchorPane cancelPane;

    @FXML
    private Button yes;

    @FXML
    private Button no;

    /**
     * переменные, для создания объекта
     * класса TableItem
     */
    private String prName = "";
    private int prCount = 0;
    private float prPrice = (float) 0.0;

    private float tot = (float) 0.0; //переменная, для отображения итоговой суммы

    private int selected = 0; //индекс выделенной строки

    /**
     * Объекты для отображения в таблице продаж
     */
    private ObservableList<TableItem> items = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        person.setText(Crutch.name);
        permission.setText(Crutch.per);

        productName.setCellValueFactory(new PropertyValueFactory<TableItem, String>("name"));
        productCount.setCellValueFactory(new PropertyValueFactory<TableItem, Integer>("count"));
        productPrice.setCellValueFactory(new PropertyValueFactory<TableItem, Float>("price"));
        productSumPrice.setCellValueFactory(new PropertyValueFactory<TableItem, Float>("total"));

        add.setOnAction(event -> {
            try {
                resultSet = handler.getProduct(Integer.parseInt(id.getText()));
                while (resultSet.next()) {
                    int d = 1;
                    if (Integer.parseInt(count.getText()) > 1) {
                        d = Integer.parseInt(count.getText());
                    }

                    prName = (resultSet.getString(Constant.PRD_NAME));
                    prCount = d;
                    prPrice = resultSet.getFloat(Constant.PRD_PRICE);

                    TableItem item = new TableItem(prName, prCount, prPrice);
                    items.add(item);

                    tot += item.getTotal();
                    summary.setText(String.valueOf(tot));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            sale.setItems(items);
        });

        home.setOnAction(event -> {
            openWindow(home, Constant.START);
        });

        delete.setOnAction(event -> {
            cancelPane.setVisible(true);
            selected = sale.getSelectionModel().getSelectedIndex();
        });

        yes.setOnAction(event -> {
            TableItem item = sale.getItems().get(selected);
            tot -= item.getTotal();
            summary.setText(String.valueOf(tot));
            sale.getItems().remove(selected);
            cancelPane.setVisible(false);
        });

        no.setOnAction(event -> {
            cancelPane.setVisible(false);
        });
    }
}