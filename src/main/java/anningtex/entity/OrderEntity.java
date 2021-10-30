package anningtex.entity;

import java.util.List;

/**
 * 获取布产单列表
 */
public class OrderEntity {
    /**
     * seq:序号
     */
    private int seq;
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
    private StatBatchEntity statBatchEntity;

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public boolean isExist_PicOrderFinished() {
        return IsExist_PicOrderFinished;
    }

    public void setExist_PicOrderFinished(boolean exist_PicOrderFinished) {
        IsExist_PicOrderFinished = exist_PicOrderFinished;
    }

    public String getQuantityInfo() {
        return Quantity + UnitName + "\n" + YperPc;
    }

    public String getAttrsInfo() {
        if (attrs == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        attrs.forEach(a -> {
            if ("wrap_spec".equals(a.getField_name())) {
                stringBuilder.append("包装规格：" + a.getField_value() + "\n");
            }
            if ("mixing".equals(a.getField_value())) {
                stringBuilder.append("打包方式：混花混色打包" + "\n");
            } else if ("single".equals(a.getField_value())) {
                stringBuilder.append("打包方式：独花独色打包" + "\n");
            }
        });
        if (YperPc != null) {
            stringBuilder.append("开剪方式：" + YperPc + "开剪 \n");
        }
        return stringBuilder.toString();
    }

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
        String orderScore;
        if (order_score == null) {
            orderScore = "";
        } else {
            orderScore = "评分：" + order_score;
        }
        return orderScore;
    }

    public StatBatchEntity getStatBatchEntity() {
        return statBatchEntity;
    }

    public void setStatBatchEntity(StatBatchEntity statBatchEntity) {
        this.statBatchEntity = statBatchEntity;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "OLID=" + OLID +
                ", OrderHead='" + OrderHead + '\'' +
                ", OrderNo='" + OrderNo + '\'' +
                '}';
    }
}