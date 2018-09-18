package leonardoribeiro.seriezando.MVP.Activity.Main;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;

import io.paperdb.Paper;
import leonardoribeiro.seriezando.Adapter.ViewPagerAdapter;
import leonardoribeiro.seriezando.MVP.Fragments.Estatisticas.EstatisticasFragment;
import leonardoribeiro.seriezando.MVP.Fragments.Home.HomeFragment;
import leonardoribeiro.seriezando.MVP.Fragments.Lista.ListaFragment;
import leonardoribeiro.seriezando.MVP.Activity.Login.LoginActivity;
import leonardoribeiro.seriezando.R;
import leonardoribeiro.seriezando.Util.BottomNavigationViewHelper;

public class MainActivity extends AppCompatActivity {

    public static ViewPager mViewPager;

    @Override
    protected void onStart() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(MainActivity.this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        mGoogleApiClient.connect();

        super.onStart();
    }

    private BottomNavigationView navigation;
    private MenuItem prevMenuItem;
    private GoogleApiClient mGoogleApiClient;

    private HomeFragment mHomeFragment;
    private ListaFragment mListaFragment;
    private EstatisticasFragment mEstatisticasFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
//                    mHomeFragment.refreshUI();
                    mViewPager.setCurrentItem(0);
                    break;
                case R.id.navigation_lista:
                    mViewPager.setCurrentItem(1);
                    break;
                case R.id.navigation_estat:
                    mViewPager.setCurrentItem(2);
                    break;
                case R.id.navigation_sair:
                    if (mGoogleApiClient.isConnected()) {
                        Auth.GoogleSignInApi.signOut(mGoogleApiClient);
                    }
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    Paper.book().destroy();
                    break;
//                case R.id.navigation_exit:
//                    Paper.book().destroy();
//
//                    CustomApplication customApplication = (CustomApplication) getApplicationContext();
//                    customApplication.destroySession();
//
//                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    startActivity(intent);
//                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(navigation);


        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    navigation.getMenu().getItem(0).setChecked(false);
                }

                navigation.getMenu().getItem(position).setChecked(true);
                prevMenuItem = navigation.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setupViewPager(mViewPager);
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        mHomeFragment = new HomeFragment();
        mListaFragment = new ListaFragment();
        mEstatisticasFragment = new EstatisticasFragment();

        adapter.addFragment(mHomeFragment);
        adapter.addFragment(mListaFragment);
        adapter.addFragment(mEstatisticasFragment);

        /*
        adapter.addFragment(myOrdersFragment);
        adapter.addFragment(myAccountFragment);*/

        viewPager.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        if (mViewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.

            finish();
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() - mViewPager.getCurrentItem());
        }
    }
}