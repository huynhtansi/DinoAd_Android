package vn.dinosys.dinoad.data.net.model;

/**
 * Created by huutai.
 * Since: 8/5/16 on 2:05 PM
 * Project: DinoAd
 */
public class CouponItem {

    private String title;

    private long endDate;

    private int drawableId;

    public CouponItem(int pDrawableId, String pTitle, long pEndDate) {
        drawableId = pDrawableId;
        title = pTitle;
        endDate = pEndDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String pTitle) {
        title = pTitle;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long pEndDate) {
        endDate = pEndDate;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int pDrawableId) {
        drawableId = pDrawableId;
    }
}
