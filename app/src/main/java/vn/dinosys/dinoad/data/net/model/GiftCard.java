package vn.dinosys.dinoad.data.net.model;

/**
 * Created by huutai.
 * Since: 7/28/16 on 5:35 PM
 * Project: DinoAd
 */
public class GiftCard {

    private int drawableId;

    private String title;

    private int price;

    public GiftCard(int pDrawableId, String pTitle, int pPrice) {
        drawableId = pDrawableId;
        title = pTitle;
        price = pPrice;
    }

    public GiftCard(String pTitle, int pPrice) {
        price = pPrice;
        title = pTitle;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int pDrawableId) {
        drawableId = pDrawableId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String pTitle) {
        title = pTitle;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int pPrice) {
        price = pPrice;
    }
}
