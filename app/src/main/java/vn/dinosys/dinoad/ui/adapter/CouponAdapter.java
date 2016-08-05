package vn.dinosys.dinoad.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.dinosys.dinoad.R;
import vn.dinosys.dinoad.data.net.model.CouponItem;
import vn.dinosys.dinoad.util.Util;

/**
 * Created by huutai.
 * Since: 8/5/16 on 1:57 PM
 * Project: DinoAd
 */
public class CouponAdapter extends BaseAdapter {

    private List<CouponItem> mData;

    public CouponAdapter(List<CouponItem> pBanners) {
        mData = pBanners;
    }

    public void setData(List<CouponItem> pGiftCards) {
        if (pGiftCards != null) {
            mData = pGiftCards;
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return mData != null ? mData.size() : 0;
    }

    @Override
    public CouponItem getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int pI) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup pViewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(pViewGroup.getContext()).inflate(R.layout.list_coupon_item, pViewGroup, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        CouponItem item = getItem(position);
        if (item.getDrawableId() != 0) {
            holder.ivCoupon.setImageResource(item.getDrawableId());
        }
        holder.textCoupon.setText(item.getTitle());
        holder.textEndDate.setText(Util.dateTimeToString(item.getEndDate()));

        return convertView;
    }

    static class ViewHolder {

        @BindView(R.id.ivCoupon)
        ImageView ivCoupon;

        @BindView(R.id.textCoupon)
        TextView textCoupon;

        @BindView(R.id.textEndDate)
        TextView textEndDate;

        ViewHolder(View pView) {
            ButterKnife.bind(this, pView);
        }
    }
}
