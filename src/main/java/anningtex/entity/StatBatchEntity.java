package anningtex.entity;

/**
 * desc:获取布产单完成图完成情况-批量查询
 */
public class StatBatchEntity {
    private int order_id;
    /**
     * 挂钩样
     */
    private int order_finished_count;
    /**
     * 成品图完成个数
     */
    private int order_item_finished_count;
    /**
     * 成品图总个数
     */
    private int order_item_count;

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getOrder_finished_count() {
        return order_finished_count;
    }

    public void setOrder_finished_count(int order_finished_count) {
        this.order_finished_count = order_finished_count;
    }

    public int getOrder_item_finished_count() {
        return order_item_finished_count;
    }

    public void setOrder_item_finished_count(int order_item_finished_count) {
        this.order_item_finished_count = order_item_finished_count;
    }

    public int getOrder_item_count() {
        return order_item_count;
    }

    public void setOrder_item_count(int order_item_count) {
        this.order_item_count = order_item_count;
    }
}