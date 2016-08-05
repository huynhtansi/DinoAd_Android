package vn.dinosys.dinoad.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.dinosys.dinoad.R;
import vn.dinosys.dinoad.data.net.model.PointDetail;

/**
 * Created by huutai.
 * Since: 7/29/16 on 4:35 PM
 * Project: DinoAd
 */
public class PointDetailAdapter extends BaseExpandableListAdapter {


    private List<PointDetail> mGroupData;
    private HashMap<PointDetail, List<PointDetail>> mData;

    public PointDetailAdapter(List<PointDetail> pGroupData, HashMap<PointDetail, List<PointDetail>> pData) {
        this.mData = pData;
        this.mGroupData = pGroupData;
    }

    public void setData(List<PointDetail> pGroupData, HashMap<PointDetail, List<PointDetail>> pData) {
        if (pGroupData != null && pData != null) {
            this.mGroupData = pGroupData;
            this.mData = pData;
            notifyDataSetChanged();
        }
    }

    @Override
    public int getGroupCount() {
        return mGroupData.size();
    }

    @Override
    public int getChildrenCount(int pI) {
        return mData.get(mGroupData.get(pI)) != null ? mData.get(mGroupData.get(pI)).size() : 0;
    }

    @Override
    public PointDetail getGroup(int groupPosition) {
        return mGroupData.get(groupPosition);
    }

    @Override
    public PointDetail getChild(int groupPosition, int childPosition) {
        return mData.get(mGroupData.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int pI) {
        return pI;
    }

    @Override
    public long getChildId(int pI, int pI1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public int getChildType(int groupPosition, int childPosition) {
        return mData.get(mGroupData.get(groupPosition)).get(childPosition).getType().ordinal();
    }

    @Override
    public int getChildTypeCount() {
        return PointDetail.Type.values().length;
    }

    @Override
    public View getGroupView(int pI, boolean isExpanded, View convertView, ViewGroup pViewGroup) {
        GroupViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(pViewGroup.getContext()).inflate(R.layout.list_point_detail_item, pViewGroup, false);
            holder = new GroupViewHolder(convertView);
            convertView.setTag(holder);
        }
        else {
            holder = (GroupViewHolder) convertView.getTag();
        }

        PointDetail pointDetail = getGroup(pI);
        holder.textTitle.setText(pointDetail.getTitle());
        holder.textPrice.setText(String.format(Locale.US, "%,d đ", pointDetail.getPrice()));
        if (getChildrenCount(pI) != 0) {
            holder.ivGroupIndicator.setVisibility(View.VISIBLE);
            if (isExpanded) {
                holder.ivGroupIndicator.setScaleY(-1);
            }
            else {
                holder.ivGroupIndicator.setScaleY(1);
            }
        }
        else {
            holder.ivGroupIndicator.setVisibility(View.GONE);
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup pViewGroup) {
        ViewHolder holder = null;
        ViewHolderHeader holderHeader = null;

        if (convertView == null) {
            if (getChildType(groupPosition, childPosition) == PointDetail.Type.HEADER.ordinal()) {
                convertView = LayoutInflater.from(pViewGroup.getContext()).inflate(R.layout.list_point_detail_child_header_item, pViewGroup, false);
                holderHeader = new ViewHolderHeader(convertView);
                convertView.setTag(holderHeader);
            }
            else {
                convertView = LayoutInflater.from(pViewGroup.getContext()).inflate(R.layout.list_point_detal_child_item, pViewGroup, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            }
        }
        else {
            if (getChildType(groupPosition, childPosition) == PointDetail.Type.HEADER.ordinal()) {
                holderHeader = (ViewHolderHeader) convertView.getTag();
            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }
        }

        PointDetail pointDetail = getChild(groupPosition, childPosition);
        if (getChildType(groupPosition, childPosition) == PointDetail.Type.HEADER.ordinal()) {
            holderHeader.textTitle.setText(pointDetail.getTitle());
        }
        else {
            holder.textTitle.setText(pointDetail.getTitle());
            holder.textPrice.setText(String.format(Locale.US, "%,d đ", pointDetail.getPrice()));
        }
        return convertView;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int pI, int pI1) {
        return true;
    }

    static class GroupViewHolder {

        @BindView(R.id.textPointDetailTitle)
        TextView textTitle;

        @BindView(R.id.textPointDetailPrice)
        TextView textPrice;

        @BindView(R.id.ivGroupIndicator)
        ImageView ivGroupIndicator;

        GroupViewHolder(View pView) {
            ButterKnife.bind(this, pView);
        }
    }

    static class ViewHolder {

        @BindView(R.id.textPointDetailTitle)
        TextView textTitle;

        @BindView(R.id.textPointDetailPrice)
        TextView textPrice;

        ViewHolder(View pView) {
            ButterKnife.bind(this, pView);
        }
    }

    static class ViewHolderHeader {

        @BindView(R.id.textPointDetailTitle)
        TextView textTitle;

        ViewHolderHeader(View pView) {
            ButterKnife.bind(this, pView);
        }
    }
}
