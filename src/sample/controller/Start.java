package sample.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.assets.Constant;
import sample.assets.Crutch;

public class Start extends Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    @FXML
    private Button enter;

    @FXML
    void initialize() {
        enter.setOnAction(event -> {
            worker.setLogin(login.getText());
            worker.setPassword(password.getText());
            resultSet = handler.getUser(worker);

            StringBuilder builder = new StringBuilder("");

            try {
                while (resultSet.next()) {
                    /**
                     * проверка на совпадение логина и пароля
                     */
                    if (resultSet.getString(Constant.EMP_LOGIN).equals(login.getText())) {
                        /**
                         * создание строки с именем, фамилией и
                         * отчеством текущего пользователя
                         */
                        builder.append(resultSet.getString(Constant.EMP_LASTNAME));
                        builder.append(' ');
                        builder.append(resultSet.getString(Constant.EMP_FIRSTNAME));
                        builder.append(' ');
                        builder.append(resultSet.getString(Constant.EMP_PATRONYMIC));

                        /**
                         * ФИО и должность текущего пользователя
                         */
                        Crutch.name = builder.toString();
                        Crutch.per = resultSet.getString(Constant.EMP_POSITION);

                        openWindow(enter, Constant.DECISION);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
