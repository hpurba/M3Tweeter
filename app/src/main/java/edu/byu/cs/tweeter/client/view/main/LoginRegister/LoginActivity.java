package edu.byu.cs.tweeter.client.view.main.LoginRegister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.client.presenter.LoginPresenter;
import edu.byu.cs.tweeter.client.view.main.LoginRegister.LoginRegisterSectionsPagerAdapter;

/**
 * Contains the minimum UI required to allow the user to login with a hard-coded user. Most or all
 * of this should be replaced when the back-end is implemented.
 */
public class LoginActivity extends AppCompatActivity implements LoginPresenter.View {

    private static final String LOG_TAG = "LoginActivity";

    private LoginPresenter presenter;
    private Toast loginInToast;
    LoginRegisterSectionsPagerAdapter myLoginRegisterSectionsPagerAdapter;
    ViewPager myViewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);// Original code
        setContentView(R.layout.activity_login);// Original code
        presenter = new LoginPresenter(this); // Original code

        myViewPager = (ViewPager) findViewById(R.id.login_register_view_pager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        setPagerAdapter();
        setTabLayout();
    }

    private void setPagerAdapter(){
        myLoginRegisterSectionsPagerAdapter = new LoginRegisterSectionsPagerAdapter(getSupportFragmentManager());
        myViewPager.setAdapter(myLoginRegisterSectionsPagerAdapter);
    }

    private void setTabLayout() {
        tabLayout.setupWithViewPager(myViewPager);
        tabLayout.getTabAt(0).setText("LOGIN");
        tabLayout.getTabAt(1).setText("REGISTER");
    }
}