package anningtex.entity;

import java.util.List;

public class OrderTwoEntity {
    private int OLID;
    private String OrderHead;
    private String OrderNo;
    private String OrderDate;
    private long Quantity;
    private String UnitName;
    private String CompanyName;
    private String TexType;
    private int IsUrgent;
    private String YperPc;
    private boolean IsExist_PicOrderFinished;
    private List<OrderAttrsEntity> attrs;
    private OrderLogEntity order_last_logs;
    private String order_score;

    public void setOLID(int OLID) {
        this.OLID = OLID;
    }

    public int getOLID() {
        return OLID;
    }

    public void setOrderHead(String OrderHead) {
        this.OrderHead = OrderHead;
    }

    public String getOrderHead() {
        return OrderHead;
    }

    public void setOrderNo(String OrderNo) {
        this.OrderNo = OrderNo;
    }

    public String getOrderNo() {
        return OrderNo;
    }

    public void setOrderDate(String OrderDate) {
        this.OrderDate = OrderDate;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setQuantity(long Quantity) {
        this.Quantity = Quantity;
    }

    public long getQuantity() {
        return Quantity;
    }

    public void setUnitName(String UnitName) {
        this.UnitName = UnitName;
    }

    public String getUnitName() {
        return UnitName;
    }

    public void setCompanyName(String CompanyName) {
        this.CompanyName = CompanyName;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setTexType(String TexType) {
        this.TexType = TexType;
    }

    public String getTexType() {
        return TexType;
    }

    public void setIsUrgent(int IsUrgent) {
        this.IsUrgent = IsUrgent;
    }

    public int getIsUrgent() {
        return IsUrgent;
    }

    public void setYperPc(String YperPc) {
        this.YperPc = YperPc;
    }

    public String getYperPc() {
        return YperPc;
    }

    public void setIsExist_PicOrderFinished(boolean IsExist_PicOrderFinished) {
        this.IsExist_PicOrderFinished = IsExist_PicOrderFinished;
    }

    public boolean getIsExist_PicOrderFinished() {
        return IsExist_PicOrderFinished;
    }

    public void setAttrs(List<OrderAttrsEntity> attrs) {
        this.attrs = attrs;
    }

    public List<OrderAttrsEntity> getAttrs() {
        return attrs;
    }

    public void setOrder_last_logs(OrderLogEntity order_last_logs) {
        this.order_last_logs = order_last_logs;
    }

    public OrderLogEntity getOrder_last_logs() {
        return order_last_logs;
    }

    public void setOrder_score(String order_score) {
        this.order_score = order_score;
    }

    public String getOrder_score() {
        return order_score;
    }

    @Override
    public String toString() {
        return OrderNo + "\t\t" + OrderHead +
                "\t\t" + OrderDate +
                "\t\t" + Quantity +
                "\t\t" + YperPc +
//                "\t\t" + attrs +
                "\t\t\t\t" + CompanyName;
    }
}