package anningtex.entity;

/**
 * 获取布产单列表里的
 */
public class OrderAttrsEntity {
    private int id;
    private int order_id;
    private String field_name;
    private String field_value;
    private String create_user;
    private String update_user;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setField_name(String field_name) {
        this.field_name = field_name;
    }

    public String getField_name() {
        return field_name;
    }

    public void setField_value(String field_value) {
        this.field_value = field_value;
    }

    public String getField_value() {
        return field_value;
    }

    public void setCreate_user(String create_user) {
        this.create_user = create_user;
    }

    public String getCreate_user() {
        return create_user;
    }

    public void setUpdate_user(String update_user) {
        this.update_user = update_user;
    }

    public String getUpdate_user() {
        return update_user;
    }
}