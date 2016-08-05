package vn.dinosys.dinoad.ui.fragment.point;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import vn.dinosys.dinoad.R;
import vn.dinosys.dinoad.data.net.model.PointDetail;
import vn.dinosys.dinoad.ui.adapter.PointDetailAdapter;
import vn.dinosys.dinoad.ui.fragment.base.BaseFragment;

/**
 * Created by huutai.
 * Since: 7/29/16 on 3:17 PM
 * Project: DinoAd
 */
public class PointDetailFragment  extends BaseFragment {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.elvPointDetail)
    ExpandableListView mElvPointDetail;

    TextView mTextTotalPoint;

    public static PointDetailFragment newInstance(String title) {
        PointDetailFragment fragment = new PointDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("TITLE", title);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_point_detail, container, false);
    }

    @Override
    protected void onScreenVisible() {
        super.onScreenVisible();
        setHasOptionsMenu(true);

        String title = getArguments().getString("TITLE");

        initToolbar();
        setupUI();
    }


    private void initToolbar() {
        AppCompatActivity appCompatActivity = ((AppCompatActivity) getActivity());
        appCompatActivity.setSupportActionBar(mToolbar);
        ActionBar actionBar = appCompatActivity.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.points_detail);
    }

    private void setupUI() {
        ArrayList<PointDetail> listGroup = new ArrayList<>();
        HashMap<PointDetail, List<PointDetail>> pointDetailData = new HashMap<>();

        listGroup.add(new PointDetail("Total points",  13125));
        pointDetailData.put(listGroup.get(listGroup.size()-1), null);
        listGroup.add(new PointDetail("Promotion points",  70));
        pointDetailData.put(listGroup.get(listGroup.size()-1), null);
        listGroup.add(new PointDetail("Display points",  1810));
        pointDetailData.put(listGroup.get(listGroup.size()-1), null);

        ArrayList<PointDetail> arrayList = new ArrayList<>();
        arrayList.add(new PointDetail("Point history is available for 14 days", 0, PointDetail.Type.HEADER));
        arrayList.add(new PointDetail("PokeChat Go", 250));
        listGroup.add(new PointDetail("Install points",  250));
        pointDetailData.put(listGroup.get(listGroup.size()-1), arrayList);

        arrayList = new ArrayList<>();
        arrayList.add(new PointDetail("Point history is available for 14 days", 0, PointDetail.Type.HEADER));
        arrayList.add(new PointDetail("PokemonGuide", 800));
        arrayList.add(new PointDetail("Solitaire", 115));
        arrayList.add(new PointDetail("Fishdom", 250));
        arrayList.add(new PointDetail("Township", 300));
        arrayList.add(new PointDetail("V - Live Broadcasting AP", 580));
        arrayList.add(new PointDetail("Ban co biet", 500));
        arrayList.add(new PointDetail("RadaBike", 500));
        listGroup.add(new PointDetail("Install and Run points",  3045));
        pointDetailData.put(listGroup.get(listGroup.size()-1), arrayList);

        arrayList = new ArrayList<>();
        arrayList.add(new PointDetail("Point history is available for 14 days", 0, PointDetail.Type.HEADER));
        arrayList.add(new PointDetail("Affiliate Video", 30));
        arrayList.add(new PointDetail("Affiliate Video", 30));
        arrayList.add(new PointDetail("Affiliate Video", 30));
        arrayList.add(new PointDetail("Affiliate Video", 30));
        arrayList.add(new PointDetail("Affiliate Video", 30));
        arrayList.add(new PointDetail("Nhận thưởng từ quảng cáo", 450));
        arrayList.add(new PointDetail("NESCAFE", 100));
        arrayList.add(new PointDetail("ROMANO", 100));
        listGroup.add(new PointDetail("Video points",  950));
        pointDetailData.put(listGroup.get(listGroup.size()-1), arrayList);

        arrayList = new ArrayList<>();
        arrayList.add(new PointDetail("Recommendee point", 0));
        arrayList.add(new PointDetail("Recommendee point", 0));
        arrayList.add(new PointDetail("Signup point", 7000));
        arrayList.add(new PointDetail("Event point", 0));
        listGroup.add(new PointDetail("Event points",  7000));
        pointDetailData.put(listGroup.get(listGroup.size()-1), arrayList);

        arrayList = new ArrayList<>();
        arrayList.add(new PointDetail("Point history is available for 14 days", 0, PointDetail.Type.HEADER));
        listGroup.add(new PointDetail("Used points",  0));
        pointDetailData.put(listGroup.get(listGroup.size()-1), arrayList);

        PointDetailAdapter adapter = new PointDetailAdapter(listGroup, pointDetailData);
        mElvPointDetail.setAdapter(adapter);

        View headerView = LayoutInflater.from(getContext()).inflate(R.layout.layout_header_point_detail, null);
        mTextTotalPoint = (TextView) headerView.findViewById(R.id.textTotalPoint);
        mTextTotalPoint.setText(String.format(Locale.US, "%,d", 13125));

        mElvPointDetail.addHeaderView(headerView);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Log.d("GiftDetail", "onOptionsItemSelected");

        if (id == android.R.id.home) {
            getActivity().finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
