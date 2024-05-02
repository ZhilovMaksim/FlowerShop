package sample.flowershop;

public class Const {
    public static final String USER_TABLE = "users";
    public static final String USER_ID = "user_id";
    public static final String USERNAME = "username";
    public static final String USER_PASSWORD = "password";

    public static final String PRODUCT_TABLE = "products";
    public static final String PRODUCT_ID = "product_id";
    public static final String PRODUCT_NAME = "product_name";
    public static final String PRODUCT_PRICE = "product_price";
    // Другие поля для продуктов, если нужно

    // Константы для таблицы заказов
    public static final String ORDER_TABLE = "orders";
    public static final String ORDER_ID = "order_id";
    public static final String ORDER_CUSTOMER_ID = "customer_id"; // Изменено для ясности
    public static final String ORDER_TOTAL_COST = "total_cost";
    // Другие поля для заказов, если нужно

    // Константы для таблицы товаров в заказе
    public static final String ORDER_ITEM_TABLE = "order_items";
    public static final String ORDER_ITEM_ORDER_ID = "order_id";
    public static final String ORDER_ITEM_PRODUCT_ID = "product_id";
    public static final String ORDER_ITEM_QUANTITY = "quantity";
}
