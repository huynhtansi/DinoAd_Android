package vn.dinosys.dinoad.ui.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import vn.dinosys.dinoad.R;
import vn.dinosys.dinoad.data.net.model.PromotionItem;
import vn.dinosys.dinoad.ui.activity.lockscreen.YoutubePlayerActivity;
import vn.dinosys.dinoad.ui.adapter.PromotionAdapter;
import vn.dinosys.dinoad.ui.fragment.base.BaseFragment;

/**
 * Created by htsi.
 * Since: 7/18/16 on 10:07 AM
 * Project: DinoAd
 */
public class PromotionFragment extends BaseFragment {

    public static PromotionFragment newInstance(String title) {
        PromotionFragment fragment = new PromotionFragment();
        Bundle bundle = new Bundle();
        bundle.putString("TITLE", title);
        fragment.setArguments(bundle);
        return fragment;
    }


    @BindView(R.id.list)
    ListView mListView;

    private PromotionAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_promotion, container, false);
    }

    @Override
    protected void onScreenVisible() {
        super.onScreenVisible();

        String title = getArguments().getString("TITLE");

        setupUI();
    }

    private void setupUI() {
        List<PromotionItem> promotionItems = new ArrayList<>();
        promotionItems.add(new PromotionItem(R.drawable.app1, "Father.IO",500));
        promotionItems.add(new PromotionItem(R.drawable.app2, "BADLAND 2",800));
        promotionItems.add(new PromotionItem(R.drawable.app3, "diep.io",1000));
        promotionItems.add(new PromotionItem(R.drawable.app4, "Lux Manager",700));
        promotionItems.add(new PromotionItem(R.drawable.app5, "MOBA Legends",600));
        promotionItems.add(new PromotionItem(R.drawable.app6, "TechnoStrike",500));
        promotionItems.add(new PromotionItem(R.drawable.ic_video, "Watch Video", "Video", 100));

        mAdapter = new PromotionAdapter(promotionItems);
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener((pAdapterView, pView, pI, pL) -> {
            if (promotionItems.get(pI).getDrawableId() == R.drawable.ic_video) {
                startActivity(YoutubePlayerActivity.createIntent(getContext(), "-GlZvDxv9pA"));
            }
        });
    }
}