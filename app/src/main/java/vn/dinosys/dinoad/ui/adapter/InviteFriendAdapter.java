package vn.dinosys.dinoad.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.dinosys.dinoad.R;
import vn.dinosys.dinoad.data.net.model.ShareItem;

/**
 * Created by huutai.
 * Since: 8/2/16 on 3:33 PM
 * Project: DinoAd
 */
public class InviteFriendAdapter extends RecyclerView.Adapter<InviteFriendAdapter.ShareViewHolder> {

    private List<ShareItem> mData;


    private OnItemClickListener mOnItemClickListener;

    public InviteFriendAdapter(List<ShareItem> pShareItems) {
        mData = pShareItems;
    }

    @Override
    public ShareViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_invite_friends_item, parent, false);

        return new ShareViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShareViewHolder holder, int position) {
        ShareItem shareItem = mData.get(position);

        holder.ivShareIcon.setImageResource(shareItem.getDrawableId());
        holder.textShareName.setText(shareItem.getName());

        ((ViewGroup) holder.itemView).getChildAt(0).setOnClickListener(pView -> {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    static class ShareViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivShareIcon)
        ImageView ivShareIcon;

        @BindView(R.id.textShareName)
        TextView textShareName;

        public ShareViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setOnItemClickListener(OnItemClickListener pOnItemClickListener) {
        mOnItemClickListener = pOnItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
