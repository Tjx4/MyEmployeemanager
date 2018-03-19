package za.co.tangentsolutions.myemployeemanager.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import za.co.tangentsolutions.myemployeemanager.R;
import za.co.tangentsolutions.myemployeemanager.constants.Constants;
import za.co.tangentsolutions.myemployeemanager.presenters.BaseSlideMenuPresenter;

public abstract class BaseSlideMenuActivity extends BaseAsyncActivity implements NavigationView.OnNavigationItemSelectedListener {

    protected ViewStub currentPageLayout;
    protected View parentLayout;
    protected Toolbar toolbar;
    protected Menu slideMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees_dash_board);
    }

    public void onBackPressed() {
        super.onBackPressed();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        ((BaseSlideMenuPresenter)presenter).handleNavigationItemSelected(item);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        int itemId = item.getItemId();
        switch (itemId){
            case R.id.action_employee_stats:
                goToEmployeeStats();
                break;
            case R.id.action_logout:
                clearAllCache();
                goToLogin();
                break;
            case R.id.action_view_your_profile:
                Bundle payload = new Bundle();
                payload.putBoolean(Constants.ISMYPROFILE_KEY, true);
                goToProfile(payload);
                break;
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        MenuItem item1 =  item;

        switch (item.getItemId()){
            case R.id.action_info:
                showShortToast("Show instructions");
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.employees_dashboar_overflow_menu_drawer, menu);
        return true;
    }

    protected void setSlideMenuDependencies(BaseActivity activity, String title, int pageLayout) {
        setMainLayout(pageLayout);
        toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        activity.setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout)activity.findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                activity, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) activity.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener)activity);

        slideMenu = navigationView.getMenu();
    }

    public void setMainLayout(int pageLayout) {
        currentPageLayout = (ViewStub)findViewById(R.id.pageLayout);
        currentPageLayout.setLayoutResource(pageLayout);
    }

    protected View getMainLayout() {
        return currentPageLayout.inflate();
    }
}