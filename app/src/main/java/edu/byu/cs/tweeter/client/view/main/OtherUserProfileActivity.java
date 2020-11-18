package edu.byu.cs.tweeter.view.main;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.client.presenter.OtherUserProfilePresenter;
import edu.byu.cs.tweeter.client.view.asyncTasks.GetOtherUserProfileTask;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.FollowingStatusRequest;
import edu.byu.cs.tweeter.model.service.response.FollowingStatusResponse;

public class OtherUserProfileActivity extends AppCompatActivity implements OtherUserProfilePresenter.View, GetOtherUserProfileTask.Observer {


    private OtherUserProfilePresenter presenter;
    public static final String CURRENT_USER_KEY = "CurrentUser";
    public static final String AUTH_TOKEN_KEY = "AuthTokenKey";
    public static final String OTHER_USER_ALIAS = "OtherUserAlias";


    public User user;
    public String otherUserAlias;
    public Boolean isFollowing = true;
    public Button followUnFollowButton;

//    private Toast loginInToast;

    // later change this to make tabs to view that persons story and whatnot
    //    LoginRegisterSectionsPagerAdapter myLoginRegisterSectionsPagerAdapter;

//    ViewPager myViewPager;
//    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_user_profile);// Original code

        presenter = new OtherUserProfilePresenter(this); // Original code


        user = (User) getIntent().getSerializableExtra(CURRENT_USER_KEY);
        AuthToken authToken = (AuthToken) getIntent().getSerializableExtra(AUTH_TOKEN_KEY);
        otherUserAlias = (String) getIntent().getSerializableExtra(OTHER_USER_ALIAS);


//        // the OtherUser's name
//        TextView userFullName = findViewById(R.id.otherUsersUserName);
//        userFullName.setText(user.getAlias());
        // the OtherUser's alias
        TextView userAlias = findViewById(R.id.otherUsersAlias);
        userAlias.setText(otherUserAlias);


        // DEFINES THE LOGIN BUTTON
        followUnFollowButton = findViewById(R.id.FollowUnFollowButton);
        followUnFollowButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Makes a login request. The user is hard-coded, so it doesn't matter what data we put
             * in the LoginRequest object.
             *
             * @param view the view object that was clicked.
             */
            @Override
            public void onClick(View view) {

                isFollowing = !isFollowing;
                FollowingStatusRequest followingStatusRequest = new FollowingStatusRequest(user.getAlias(), otherUserAlias, isFollowing); // Alias is the @username
                GetOtherUserProfileTask getOtherUserProfileTask = new GetOtherUserProfileTask(presenter, OtherUserProfileActivity.this);
                getOtherUserProfileTask.execute(followingStatusRequest);

                if (isFollowing) {
                    followUnFollowButton.setText("FOLLOWING");
                }
                else {
                    followUnFollowButton.setText("FOLLOW");
                }

            }
        });
//
//        if (isFollowing) {
//            followUnFollowButton.setText("FOLLOWING");
//        }
//        else {
//            followUnFollowButton.setText("FOLLOW");
//        }





//        myViewPager = (ViewPager) findViewById(R.id.login_register_view_pager);
//        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

//        setPagerAdapter();
//        setTabLayout();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {

//        View view = inflater.inflate(R.layout.fragment_login, container, false);

        return super.onCreateView(name, context, attrs);
    }

    @Override
    public void followingRetrieved(FollowingStatusResponse followingStatusResponse) {
        isFollowing = followingStatusResponse.getFollowing();
        if (isFollowing) {
            followUnFollowButton.setText("FOLLOWING");
        }
        else {
            followUnFollowButton.setText("FOLLOW");
        }
    }

    @Override
    public void handleException(Exception exception) {

    }

//    private void setPagerAdapter(){
//        myLoginRegisterSectionsPagerAdapter = new LoginRegisterSectionsPagerAdapter(getSupportFragmentManager());
//        myViewPager.setAdapter(myLoginRegisterSectionsPagerAdapter);
//    }

//    private void setTabLayout() {
//        tabLayout.setupWithViewPager(myViewPager);
//        tabLayout.getTabAt(0).setText("LOGIN");
//        tabLayout.getTabAt(1).setText("REGISTER");
//    }
}
