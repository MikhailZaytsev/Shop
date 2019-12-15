package sample.assets;

public class Constant {
    /**
     * константы, содержащие имена и
     * пути расположения графических окон
     */
    public static final String PACK = "/sample/fxml/";
    public static final String START = PACK + "start.fxml";
    public static final String DECISION = PACK + "decision.fxml";
    public static final String PRODUCT = PACK + "product.fxml";
    public static final String EMPLOYER = PACK + "employer.fxml";
    public static final String SALE = PACK + "sale.fxml";

    /**
     * константы, содержащие названия
     * таблицы и столбцов базы данных работников
     */
    public final static String EMP_TABLE = "employers";
    public final static String EMP_ID = "id";
    public final static String EMP_FIRSTNAME = "firstname";
    public final static String EMP_LASTNAME = "lastname";
    public final static String EMP_PATRONYMIC = "patronymic";
    public final static String EMP_LOGIN = "login";
    public final static String EMP_PASSWORD = "password";
    public final static String EMP_POSITION = "position";
    public final static String EMP_DATE_OF_BIRTH = "dateofbirth";

    /**
     * константы, содержащие названия
     * таблицы и столбцов базы данных товаров
     */
    public final static String PRD_TABLE = "products";
    public final static String PRD_ID = "id";
    public final static String PRD_NAME = "productname";
    public final static String PRD_FACTORY = "factory";
    public final static String PRD_PRICE = "price";
    public final static String PRD_DESCRIPTION = "description";
    public final static String PRD_COUNT = "count";
}
