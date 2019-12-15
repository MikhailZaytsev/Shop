package sample.assets;

/**
 * Класс для отображения элементов таблицы
 * в окне продажи
 */
public class TableItem {
    private String name;
    private int count;
    private float price;
    private float total;

    public TableItem(String name, int count, float price) {
        this.name = name;
        this.count = count;
        this.price = price;
        total = count * price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
