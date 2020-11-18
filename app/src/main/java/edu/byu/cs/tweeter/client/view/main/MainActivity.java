package edu.byu.cs.tweeter.client.view.main;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.client.presenter.LogoutPresenter;
import edu.byu.cs.tweeter.client.presenter.TweetPresenter;
import edu.byu.cs.tweeter.client.view.asyncTasks.LogoutTask;
import edu.byu.cs.tweeter.client.view.asyncTasks.TweetTask;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.LogoutRequest;
import edu.byu.cs.tweeter.model.service.request.TweetRequest;
import edu.byu.cs.tweeter.model.service.response.LogoutResponse;
import edu.byu.cs.tweeter.model.service.response.TweetResponse;
import edu.byu.cs.tweeter.client.view.main.LoginRegister.LoginActivity;

/**
 * The main activity for the application. Contains tabs for feed, story, following, and followers.
 */
public class MainActivity extends AppCompatActivity implements LogoutPresenter.View, LogoutTask.Observer, TweetPresenter.View, TweetTask.Observer {

    public static final String CURRENT_USER_KEY = "CurrentUser";
    public static final String AUTH_TOKEN_KEY = "AuthTokenKey";
    public static final String FOLLOWING_BOOL = "followingBool";

    private LogoutPresenter logoutPresenter;
    private TweetPresenter tweetPresenter;
    private Toast logoutToast;
    private Toast tweetCompleteToast;
    public User user;
    public ViewGroup root;

    private String firstName;
    private String lastName;
    private String alias;
    private Bitmap profilePicture;
    private String tweetText;
    private View view;
    private PopupWindow popupWindow;
    private ImageView tweetProfilePictureView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        view = findViewById(android.R.id.content).getRootView();

        logoutPresenter = new LogoutPresenter(this); // maybe change this to view
        tweetPresenter = new TweetPresenter(this);

        user = (User) getIntent().getSerializableExtra(CURRENT_USER_KEY);
        if(user == null) {
            throw new RuntimeException("User not passed to activity");
        }
        else {
            // grab the user's
            firstName = user.getFirstName();
            lastName = user.getLastName();
            alias =  user.getAlias();
        }
        AuthToken authToken = (AuthToken) getIntent().getSerializableExtra(AUTH_TOKEN_KEY);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(), user, authToken);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);


        // OPEN UP THE WRITE TWEET BOX!
        FloatingActionButton fab = findViewById(R.id.fab);
        // We should use a Java 8 lambda function for the listener (and all other listeners), but
        // they would be unfamiliar to many students who use this code.
        // THIS SHOULD GO TO MAKING A NEW TWEET FUNCTIONALITY
//        ViewGroup view = (ViewGroup) getWindow().getDecorView().getRootView();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                ViewGroup root = (ViewGroup) getWindow().getDecorView().getRootView();
                onButtonShowPopupWindowClick(view, root);
            }
        });



        // LATER THESE WILL NEED TO RETRIEVE ACTUAL DATA
        TextView userName = findViewById(R.id.userName);
        userName.setText(user.getName());

        TextView userAlias = findViewById(R.id.userAlias);
        userAlias.setText(user.getAlias());

        ImageView userImageView = findViewById(R.id.userImageMain);
//        userImageView.setImageDrawable(ImageUtils.drawableFromByteArray(user.getImageBytes()));
        byte [] imageBytes = user.getImageBytes();
        setImageViewWithByteArray(userImageView, imageBytes);

        TextView followeeCount = findViewById(R.id.followeeCount);
        followeeCount.setText(getString(R.string.followeeCount, 20));

        TextView followerCount = findViewById(R.id.followerCount);
        followerCount.setText(getString(R.string.followerCount, 20));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }


    public static void setImageViewWithByteArray(ImageView view, byte[] data) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
        view.setImageBitmap(bitmap);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.logoutMenu:
                logoutOfApp();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void logoutOfApp(){
        logoutToast = Toast.makeText(this, "Logging Out!", Toast.LENGTH_LONG);
        logoutToast.show();
        // do the logout

        LogoutRequest logoutRequest = new LogoutRequest(user.getAlias()); // Alias is the @username
        LogoutTask logoutTask = new LogoutTask(logoutPresenter, this);
        logoutTask.execute(logoutRequest);
    }


    @Override
    public void logoutSuccessful(LogoutResponse logoutResponse) {
        Intent intent = new Intent(this, LoginActivity.class);
        logoutToast.cancel();
        startActivity(intent);
    }

    @Override
    public void logoutUnsuccessful(LogoutResponse logoutResponse) {

    }

    @Override
    public void tweetSuccessful(TweetResponse tweetResponse) {

    }

    @Override
    public void tweetUnsuccessful(TweetResponse tweetResponse) {

    }

    @Override
    public void handleException(Exception ex) {

    }


    // POPUP WINDOW FOR MAKING A TWEET
    public void onButtonShowPopupWindowClick(View view, ViewGroup root) {

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.fragment_tweet, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        applyDim(root, 0.6f);


        // set the First name last name, alias, and the user profile picture.
        TextView userName = popupView.findViewById(R.id.userFirstNameLastNameInTweetWindow);
        userName.setText(user.getName());

        TextView userAlias = popupView.findViewById(R.id.userAliasInTweetWindow);
        userAlias.setText(user.getAlias());

        tweetProfilePictureView = popupView.findViewById(R.id.tweetProfilePictureImageView);
        Bitmap userProfilePictureBitmap = BitmapFactory.decodeByteArray(user.getImageBytes(), 0, user.getImageBytes().length);
        tweetProfilePictureView.setImageBitmap(userProfilePictureBitmap);




        // Clicking away throws away the tweet
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                popupWindow.dismiss();
                clearDim(root);
            }
        });



        Button tweetButton = popupView.findViewById(R.id.et_postTweetButton);
        tweetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText tweetEditText =  popupView.findViewById(R.id.et_tweetText);
                tweetText = tweetEditText.getText().toString();
                if (postTweet(tweetText) == true) {
                   // continue
                }
                else {
                    // You have a problem!!
                }

                popupWindow.dismiss();
                clearDim(root);
//                tweetCompleteToast.cancel();
            }
        });

    }

    // This will post the tweet with the tweet text provided
    public Boolean postTweet(String tweetText){

        String output = "Nice job " + firstName + " " + lastName + "! You just posted a tweet as " + alias + ".\n Your tweet: " + tweetText;
        tweetCompleteToast = Toast.makeText(this, output, Toast.LENGTH_LONG);
        tweetCompleteToast.show();


        TweetRequest tweetRequest = new TweetRequest(user.getAlias(), tweetText);
        TweetTask tweetTask = new TweetTask(tweetPresenter, this);
        tweetTask.execute(tweetRequest);

        return true;
    }

    // This handles the background dimming and undimming
    // https://stackoverflow.com/questions/3221488/blur-or-dim-background-when-android-popupwindow-active
    public static void applyDim(@NonNull ViewGroup parent, float dimAmount){
        Drawable dim = new ColorDrawable(Color.BLACK);
        dim.setBounds(0, 0, parent.getWidth(), parent.getHeight());
        dim.setAlpha((int) (255 * dimAmount));

        ViewGroupOverlay overlay = parent.getOverlay();
        overlay.add(dim);
    }
    public static void clearDim(@NonNull ViewGroup parent) {
        ViewGroupOverlay overlay = parent.getOverlay();
        overlay.clear();
    }
}