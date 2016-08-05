package vn.dinosys.dinoad.data.net.model;

/**
 * Created by huutai.
 * Since: 7/29/16 on 4:39 PM
 * Project: DinoAd
 */
public class PointDetail {

    private String mTitle;

    private int mPrice;

    private Type mType;

    public PointDetail(String pTitle, int pPrice) {
        mTitle = pTitle;
        mPrice = pPrice;
        mType = Type.NORMAL;
    }

    public PointDetail(String pTitle, int pPrice, Type pType) {
        mTitle = pTitle;
        mPrice = pPrice;
        mType = pType;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String pTitle) {
        mTitle = pTitle;
    }

    public int getPrice() {
        return mPrice;
    }

    public void setPrice(int pPrice) {
        mPrice = pPrice;
    }

    public Type getType() {
        return mType;
    }

    public void setType(Type pType) {
        mType = pType;
    }

    public enum Type {
        HEADER, NORMAL
    }
}
