package sample.dataBase;

import sample.assets.Constant;
import sample.entity.Goods;
import sample.entity.Worker;

import java.sql.*;

public class DataBaseHandler extends Configuration {
    Connection dataBaseCon;
    private ResultSet resultSet = null;

    public Connection getDataBaseCon() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:postgresql://" + host + ":" +
                port + "/" + name;

        Class.forName("org.postgresql.Driver");

        dataBaseCon = DriverManager.getConnection(connectionString, user, pass);

        return dataBaseCon;
    }

    public void addEmployer(Worker worker) {
        String insert = "INSERT INTO " + Constant.EMP_TABLE + "(" + Constant.EMP_FIRSTNAME + "," +
                Constant.EMP_LASTNAME + "," + Constant.EMP_PATRONYMIC + "," +
                Constant.EMP_LOGIN + "," + Constant.EMP_PASSWORD + "," +
                Constant.EMP_POSITION + "," + Constant.EMP_DATE_OF_BIRTH + ")" +
                "VALUES(?,?,?,?,?,?,?)";

        try {
            PreparedStatement prSt = getDataBaseCon().prepareStatement(insert);
            prSt.setString(1, worker.getName());
            prSt.setString(2, worker.getLastName());
            prSt.setString(3, worker.getPatronymic());
            prSt.setString(4, worker.getLogin());
            prSt.setString(5, worker.getPassword());
            prSt.setString(6, worker.getPosition());
            prSt.setDate(7, Date.valueOf(worker.getDate()));

            prSt.execute();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addProduct(Goods goods) {
        String insert = "INSERT INTO " + Constant.PRD_TABLE + "(" + Constant.PRD_NAME +
                "," + Constant.PRD_FACTORY + "," + Constant.PRD_PRICE + "," +
                Constant.PRD_DESCRIPTION + "," + Constant.PRD_COUNT + ")" +
                "VALUES(?,?,?,?,?)";

        try {
            PreparedStatement statement = getDataBaseCon().prepareStatement(insert);
            statement.setString(1, goods.getName());
            statement.setString(2, goods.getFactory());
            statement.setFloat(3, goods.getPrice());
            statement.setString(4, goods.getDescription());
            statement.setInt(5, goods.getCount());

            statement.execute();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getUser(Worker worker) {
        resultSet = null;

        String select = "SELECT * FROM " + Constant.EMP_TABLE + " WHERE " +
                Constant.EMP_LOGIN + "=? AND " + Constant.EMP_PASSWORD + "=?";
        try {
            PreparedStatement statement = getDataBaseCon().prepareStatement(select);
            statement.setString(2, worker.getPassword());
            statement.setString(1, worker.getLogin());

            resultSet = statement.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public void deleteUser(int id) {
        String delete = "DELETE FROM " + Constant.EMP_TABLE + " WHERE " +
                Constant.EMP_ID + "=?";

        try {
            PreparedStatement statement = getDataBaseCon().prepareStatement(delete);
            statement.setInt(1, id);

            statement.execute();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(int id) {
        String delete = "DELETE FROM " + Constant.PRD_TABLE + " WHERE " +
                Constant.PRD_ID + "=?";

        try {
            PreparedStatement statement = getDataBaseCon().prepareStatement(delete);
            statement.setInt(1, id);

            statement.execute();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateProduct(int id, Goods goods) {
        String update = "UPDATE " + Constant.PRD_TABLE + " SET " +
                Constant.PRD_NAME + "=?, " + Constant.PRD_FACTORY + "=?, " +
                Constant.PRD_PRICE + "=?, " + Constant.PRD_DESCRIPTION + "=?, " +
                Constant.PRD_COUNT + "=? WHERE " + Constant.PRD_ID + "=?";

        try {
            PreparedStatement statement = getDataBaseCon().prepareStatement(update);
            statement.setString(1, goods.getName());
            statement.setString(2, goods.getFactory());
            statement.setFloat(3, goods.getPrice());
            statement.setString(4, goods.getDescription());
            statement.setInt(5, goods.getCount());
            statement.setInt(6, id);

            statement.execute();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateWorker(int id, Worker worker) {
        String update = "UPDATE " + Constant.EMP_TABLE + " SET " +
                Constant.EMP_FIRSTNAME + "=?, " + Constant.EMP_LASTNAME + "=?, " +
                Constant.EMP_PATRONYMIC + "=?, " + Constant.EMP_POSITION + "=?, " +
                Constant.EMP_LOGIN + "=?, " + Constant.EMP_PASSWORD + "=?, " +
                Constant.EMP_DATE_OF_BIRTH + "=? WHERE " + Constant.EMP_ID + "=?";
        try {
            PreparedStatement statement = getDataBaseCon().prepareStatement(update);
            statement.setString(1, worker.getName());
            statement.setString(2, worker.getLastName());
            statement.setString(3, worker.getPatronymic());
            statement.setString(4, worker.getPosition());
            statement.setString(5, worker.getLogin());
            statement.setString(6, worker.getPassword());
            statement.setDate(7, Date.valueOf(worker.getDate()));
            statement.setInt(8, id);

            statement.execute();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getUser(int id) {
        resultSet = null;

        String select = "SELECT * FROM " + Constant.EMP_TABLE +
                " WHERE " + Constant.EMP_ID + "=?";

        try {
            PreparedStatement statement = getDataBaseCon().prepareStatement(select);
            statement.setInt(1, id);

            resultSet = statement.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    public ResultSet getProduct(int id) {
        resultSet = null;

        String select = "SELECT * FROM " + Constant.PRD_TABLE + " WHERE " +
                Constant.PRD_ID + "=?";

        try {
            PreparedStatement statement = getDataBaseCon().prepareStatement(select);
            statement.setInt(1, id);

            resultSet = statement.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resultSet;
    }
}
