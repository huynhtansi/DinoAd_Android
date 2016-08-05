package vn.dinosys.dinoad.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.google.android.gms.plus.PlusShare;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import vn.dinosys.dinoad.R;
import vn.dinosys.dinoad.data.net.model.ShareItem;
import vn.dinosys.dinoad.ui.adapter.InviteFriendAdapter;
import vn.dinosys.dinoad.ui.fragment.base.BaseFragment;
import vn.dinosys.dinoad.util.Util;


public class InviteFriendFragment extends BaseFragment {

    public static InviteFriendFragment newInstance() {
        InviteFriendFragment fragment = new InviteFriendFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString("TITLE", title);
//        fragment.setArguments(bundle);

        return fragment;
    }


    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.rvInviteFriends)
    RecyclerView mRvShare;

    private final String urlDownloadApp = "http://vietlott.vn/";

    public enum Invite {
        Facebook, Google, Messenger, Email, SMS, CopyURL("Copy URL");

        private String name;

        Invite() {}

        Invite(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            if (TextUtils.isEmpty(this.name))
                return super.toString();
            else
                return name;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_invite_friend, container, false);
    }

    @Override
    protected void onScreenVisible() {
        super.onScreenVisible();
        setHasOptionsMenu(true);

//        String title = getArguments().getString("TITLE");

        initToolbar();
        setupUI();
    }

    private void setupUI() {
        List<ShareItem> shareItems = new ArrayList<>();
        shareItems.add(new ShareItem(R.drawable.ic_facebook_round, Invite.Facebook.toString()));
        shareItems.add(new ShareItem(R.drawable.ic_google_round, Invite.Google.toString()));
        shareItems.add(new ShareItem(R.drawable.ic_facebook_round, Invite.Messenger.toString()));
        shareItems.add(new ShareItem(R.drawable.ic_email, Invite.Email.toString()));
        shareItems.add(new ShareItem(R.drawable.ic_message, Invite.SMS.toString()));
        shareItems.add(new ShareItem(R.drawable.ic_link, Invite.CopyURL.toString()));

        InviteFriendAdapter adapter = new InviteFriendAdapter(shareItems);
        mRvShare.setAdapter(adapter);
        mRvShare.setVerticalScrollBarEnabled(false);
        mRvShare.setNestedScrollingEnabled(false);
        adapter.setOnItemClickListener(position -> {
            switch (Invite.values()[position]) {
                case Facebook:
                    shareFacebook();
                    break;
                case Google:
                    shareGoogle();
                    break;
                case Email:
                    shareByEmail();
                    break;
                case SMS:
                    shareBySMS();
                    break;
                case CopyURL:
                    copyURL();
                    break;
                default:
                    Toast.makeText(getActivity(), "Share " + Invite.values()[position].toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initToolbar() {
        AppCompatActivity appCompatActivity = ((AppCompatActivity) getActivity());
        appCompatActivity.setSupportActionBar(mToolbar);
        ActionBar actionBar = appCompatActivity.getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.invite_friends);
        }
    }

    public void shareFacebook() {
        if (ShareDialog.canShow(ShareLinkContent.class)) {
            ShareLinkContent linkContent = new ShareLinkContent.Builder()
                    .setContentTitle("Download DinoAd today")
                    .setContentDescription("Install app and get your reward")
                    .setContentUrl(Uri.parse(urlDownloadApp))
                    .build();

            ShareDialog shareDialog = new ShareDialog(getActivity());
            shareDialog.show(linkContent);
        }
    }

    public void shareGoogle() {
        Intent shareIntent = new PlusShare.Builder(getActivity())
                .setType("text/plain")
                .setText("Download DinoAd today")
                .setContentUrl(Uri.parse(urlDownloadApp))
                .getIntent();

        startActivityForResult(shareIntent, 0);
    }

    public void  shareByEmail() {
        String subject = "Download DinoAd today";
        String body = "Let's enjoy " + urlDownloadApp;
        String chooserTitle = "Send email... ";

//        ShareCompat.IntentBuilder.from(getActivity())
//                .setType("message/rfc822")
//                .setSubject(subject)
//                .setText(body)
//                .setChooserTitle(chooserTitle)
//                .startChooser();

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
//        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void shareBySMS() {
        String smsText="Download DinoAd today \nLet's enjoy " + urlDownloadApp;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) //At least KitKat
        {
            String defaultSmsPackageName = Telephony.Sms.getDefaultSmsPackage(getActivity()); //Need to change the build to API 19

            Intent sendIntent = new Intent(Intent.ACTION_SEND);
            sendIntent.setType("text/plain");
            sendIntent.putExtra(Intent.EXTRA_TEXT, smsText);

            if (defaultSmsPackageName != null)//Can be null in case that there is no default, then the user would be able to choose any app that support this intent.
            {
                sendIntent.setPackage(defaultSmsPackageName);
            }
            startActivity(sendIntent);

        }
        else //For early versions, do what worked for you before.
        {
            Intent sendIntent = new Intent(Intent.ACTION_VIEW);
            sendIntent.setData(Uri.parse("sms:"));
            sendIntent.putExtra("sms_body", smsText);
            startActivity(sendIntent);
        }
    }

    public void copyURL() {
        Util.copyToClipboard(getContext(), urlDownloadApp);
        Toast.makeText(getActivity(), "Copied url to clipboard", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            getActivity().finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
