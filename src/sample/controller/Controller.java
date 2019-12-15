package sample.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.dataBase.DataBaseHandler;
import sample.entity.Goods;
import sample.entity.Worker;

import java.io.IOException;
import java.sql.ResultSet;

/**
 * Класс-родитель для остальных контроллеров окон
 */
public class Controller {

    /**
     * Объекты различных классов для использования при
     * работе с базами данных
     */
    protected Worker worker = new Worker();
    protected Goods goods = new Goods();
    protected ResultSet resultSet = null;
    protected DataBaseHandler handler = new DataBaseHandler();

    /**
     * метод для перехода на необходимое окно
     *
     * @param button    - хранит в себе ссылку на окно для закрытия
     * @param sceneName - имя окна, которое необходимо открыть
     */
    protected void openWindow(Button button, String sceneName) {
        button.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(sceneName));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
