package anningtex.entity;

/**
 * 获取布产单品名列表
 */
public class OrderTexInfoEntity {
    private String TexID;
    private String OrderHead;
    private String MetersPerBale;
    private String TexID_01;
    private String TexName_Show;

    public String getTexID() {
        return TexID;
    }

    public void setTexID(String texID) {
        TexID = texID;
    }

    public String getOrderHead() {
        return OrderHead;
    }

    public void setOrderHead(String orderHead) {
        OrderHead = orderHead;
    }

    public String getMetersPerBale() {
        return MetersPerBale;
    }

    public void setMetersPerBale(String metersPerBale) {
        MetersPerBale = metersPerBale;
    }

    public String getTexID_01() {
        return TexID_01;
    }

    public void setTexID_01(String texID_01) {
        TexID_01 = texID_01;
    }

    public String getTexName_Show() {
        return TexName_Show;
    }

    public void setTexName_Show(String texName_Show) {
        TexName_Show = texName_Show;
    }

    @Override
    public String toString() {
        return TexName_Show;
    }
}