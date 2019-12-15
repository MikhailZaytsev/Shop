package sample.controller;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import sample.assets.Constant;
import sample.assets.Crutch;

public class Employer extends Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label person;

    @FXML
    private Label permision;

    @FXML
    private TextField lastname;

    @FXML
    private TextField patronymic;

    @FXML
    private TextField login;

    @FXML
    private TextField password;

    @FXML
    private TextField passwordCH;

    @FXML
    private TextField loginCH;

    @FXML
    private TextField name;

    @FXML
    private ComboBox position;

    @FXML
    private DatePicker dateOfBirth;

    @FXML
    private TextField ID;

    @FXML
    private Button change;

    @FXML
    private Button delete;

    @FXML
    private Button home;

    @FXML
    private Button add;

    @FXML
    private AnchorPane employerChange;

    @FXML
    private Button yes;

    @FXML
    private Button no;

    @FXML
    private TextField lastnameCH;

    @FXML
    private TextField nameCH;

    @FXML
    private TextField patronymicCH;

    @FXML
    private ComboBox positionCH;

    @FXML
    private DatePicker dateCH;

    @FXML
    private AnchorPane delEmployer;

    @FXML
    private Button delYes;

    @FXML
    private Button delNo;

    @FXML
    private Label delName;

    /**
     * Список возможных должностей новых сотрудников
     */
    private ObservableList<String> postions =
            FXCollections.observableArrayList("Администратор",
                    "Товаровед", "Кассир");

    @FXML
    void initialize() {
        person.setText(Crutch.name);
        permision.setText(Crutch.per);

        position.setItems(postions);
        positionCH.setItems(postions);
        position.setValue(postions.get(0));

        dateOfBirth.setValue(LocalDate.now());

        home.setOnAction(event -> {
            openWindow(home, Constant.START);
        });

        change.setOnAction(event -> {
            employerChange.setVisible(true);

            try {
                int id = Integer.parseInt(ID.getText());
                resultSet = handler.getUser(id);
                while (resultSet.next()) {
                    nameCH.setText(resultSet.getString(Constant.EMP_FIRSTNAME));
                    lastnameCH.setText(resultSet.getString(Constant.EMP_LASTNAME));
                    patronymicCH.setText(resultSet.getString(Constant.EMP_PATRONYMIC));
                    positionCH.setValue(resultSet.getString(Constant.EMP_POSITION));
                    loginCH.setText(resultSet.getString(Constant.EMP_LOGIN));
                    passwordCH.setText(resultSet.getString(Constant.EMP_PASSWORD));
                }
            } catch (SQLException e) {
                e.printStackTrace();
                employerChange.setVisible(false);
            }
        });

        yes.setOnAction(event -> {
            worker.setName(nameCH.getText());
            worker.setLastName(lastnameCH.getText());
            worker.setPatronymic(patronymicCH.getText());
            worker.setPosition(positionCH.getValue().toString());
            worker.setLogin(loginCH.getText());
            worker.setPassword(passwordCH.getText());
            worker.setDate(dateCH.getValue());
            handler.updateWorker(Integer.parseInt(ID.getText()), worker);
            employerChange.setVisible(false);
        });

        no.setOnAction(event -> {
            employerChange.setVisible(false);
        });

        delete.setOnAction(event -> {
            delEmployer.setVisible(true);

            try {
                resultSet = handler.getUser(Integer.parseInt(ID.getText()));
                while (resultSet.next()) {
                    StringBuilder builder = new StringBuilder("");
                    builder.append(resultSet.getString(Constant.EMP_FIRSTNAME));
                    builder.append(" ");
                    builder.append(resultSet.getString(Constant.EMP_LASTNAME));
                    builder.append(" ");
                    builder.append(resultSet.getString(Constant.EMP_PATRONYMIC));
                    delName.setText(builder.toString());
                }
            } catch (SQLException e) {
                e.printStackTrace();
                delEmployer.setVisible(false);
            }
        });

        delYes.setOnAction(event -> {
            handler.deleteUser(Integer.parseInt(ID.getText()));
            delEmployer.setVisible(false);
        });

        delNo.setOnAction(event -> {
            delEmployer.setVisible(false);
        });

        add.setOnAction(event -> {
            ArrayList<TextField> texts = new ArrayList();
            texts.add(name);
            texts.add(lastname);
            texts.add(patronymic);
            texts.add(login);
            texts.add(password);

            for (TextField t : texts) {
                if (t.getText().equals("")) return;
            }

            worker.setName(name.getText());
            worker.setLastName(lastname.getText());
            worker.setPatronymic(patronymic.getText());
            worker.setPosition(position.getValue().toString());
            worker.setLogin(login.getText());
            worker.setPassword(password.getText());
            worker.setDate(dateOfBirth.getValue());

            handler.addEmployer(worker);
        });
    }
}
