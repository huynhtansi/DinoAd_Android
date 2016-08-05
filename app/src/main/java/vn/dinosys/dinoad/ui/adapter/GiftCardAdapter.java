package vn.dinosys.dinoad.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.dinosys.dinoad.R;
import vn.dinosys.dinoad.data.net.model.GiftCard;

/**
 * Created by huutai.
 * Since: 7/28/16 on 5:31 PM
 * Project: DinoAd
 */
public class GiftCardAdapter extends BaseAdapter {

    private List<GiftCard> mData;

    public GiftCardAdapter(List<GiftCard> pBanners) {
        mData = pBanners;
    }

    public void setData(List<GiftCard> pGiftCards) {
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
    public GiftCard getItem(int position) {
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
            convertView = LayoutInflater.from(pViewGroup.getContext()).inflate(R.layout.list_gift_detail_item, pViewGroup, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        GiftCard giftCard = getItem(position);
        if (giftCard.getDrawableId() != 0) {
            holder.ivGiftCard.setImageResource(giftCard.getDrawableId());
        }
        holder.textGiftCard.setText(giftCard.getTitle());
        holder.textGiftCardPrice.setText(String.format(Locale.US, "%,d", giftCard.getPrice()));

        return convertView;
    }

    static class ViewHolder {

        @BindView(R.id.ivGiftCard)
        ImageView ivGiftCard;

        @BindView(R.id.textGiftCard)
        TextView textGiftCard;

        @BindView(R.id.textGiftCardPrice)
        TextView textGiftCardPrice;

        ViewHolder(View pView) {
            ButterKnife.bind(this, pView);
        }
    }
}

