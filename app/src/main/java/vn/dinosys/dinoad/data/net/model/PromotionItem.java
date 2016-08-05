package vn.dinosys.dinoad.data.net.model;

/**
 * Created by huutai.
 * Since: 8/4/16 on 2:54 PM
 * Project: DinoAd
 */
public class PromotionItem {

    private String imgUrl;

    private int drawableId;

    private String promotionName;

    private String promotionDesc;

    private int price;

    public PromotionItem(int pDrawableId, String pPromotionName, int pPrice) {
        drawableId = pDrawableId;
        promotionName = pPromotionName;
        price = pPrice;
        promotionDesc = "Install and run";
    }

    public PromotionItem(int pDrawableId, String pPromotionName, String pPromotionDesc, int pPrice) {
        drawableId = pDrawableId;
        promotionName = pPromotionName;
        promotionDesc = pPromotionDesc;
        price = pPrice;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int pDrawableId) {
        drawableId = pDrawableId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String pImgUrl) {
        imgUrl = pImgUrl;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String pPromotionName) {
        promotionName = pPromotionName;
    }

    public String getPromotionDesc() {
        return promotionDesc;
    }

    public void setPromotionDesc(String pPromotionDesc) {
        promotionDesc = pPromotionDesc;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int pPrice) {
        price = pPrice;
    }
}
