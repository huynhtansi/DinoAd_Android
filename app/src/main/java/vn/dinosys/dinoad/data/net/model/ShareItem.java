package vn.dinosys.dinoad.data.net.model;

/**
 * Created by huutai.
 * Since: 8/2/16 on 3:32 PM
 * Project: DinoAd
 */
public class ShareItem {

    private int drawableId;

    private String name;

    public ShareItem(int pDrawableId, String pName) {
        drawableId = pDrawableId;
        name = pName;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int pDrawableId) {
        drawableId = pDrawableId;
    }

    public String getName() {
        return name;
    }

    public void setName(String pName) {
        name = pName;
    }
}
