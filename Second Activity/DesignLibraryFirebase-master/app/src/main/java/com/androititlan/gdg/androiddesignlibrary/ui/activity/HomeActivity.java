package com.androititlan.gdg.androiddesignlibrary.ui.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.androititlan.gdg.androiddesignlibrary.R;
import com.androititlan.gdg.androiddesignlibrary.ui.fragment.ChatFragment;
import com.androititlan.gdg.androiddesignlibrary.ui.fragment.TweetFragment;
import com.androititlan.gdg.androiddesignlibrary.util.ConfigPreferences;
import com.androititlan.gdg.androiddesignlibrary.util.Configure;
import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;



public class HomeActivity extends AppCompatActivity {


    @Bind(R.id.txt_user_name) TextView userName;
    @Bind(R.id.txt_nick_name) TextView userNickName;
    @Bind(R.id.circule_view_photo_profile) CircleImageView photoProfile;
    @Bind(R.id.img_view_photo_background) ImageView photoBackgroundProfile;

    @Bind(R.id.drawer_layout) DrawerLayout drawerLayout;
    @Bind(R.id.nav_view) NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        //injectamos las vistas
        ButterKnife.bind(this);
        fragmentTransactionReplace(TweetFragment.getInstance());

        initializeNavDrawerProfile();

        if(navigationView != null)
            setUpDrawerContent(navigationView);
    }

    /**
     *
     * @param item hacemos el override para asignarle a nuestro item menu que cuando se pulse abra el drawer layout
     *
     * @return
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     *
     * @param navigationView recibe nuestra vista de navegación a la cual le asignamos un listener
     *                       para saber en que item se hizo click y hacer la tarea correspondiente en este caso
     *                       solo cambiamos de fragmento.
     */
    private void setUpDrawerContent(NavigationView navigationView) {

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.nav_tweet:
                        fragmentTransactionReplace(TweetFragment.getInstance());

                        break;
                    case R.id.nav_chat:
                        fragmentTransactionReplace(ChatFragment.getInstance());
                        break;
                }
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    /**
     *  Recibimos el intent y obtenemos los datos del Bundle!
     */
    private void initializeNavDrawerProfile(){

        Bundle bundle = getIntent().getBundleExtra(Configure.AVATAR_PROFILE);
        if (bundle != null) {
            Glide.with(this).load(bundle.getString(Configure.AVATAR_PHOTO_PROFILE)).fitCenter().into(photoProfile);
            Glide.with(this).load(bundle.getString(Configure.AVATAR_BACKGROUND_PHOTO_PROFILE)).fitCenter().into(photoBackgroundProfile);
            userName.setText("@" + bundle.getString(Configure.AVATAR_NICK_NAME_PROFILE));
            userNickName.setText(bundle.getString(Configure.AVATAR_NAME_PROFILE));
            ConfigPreferences.setUrl(this,bundle.getString(Configure.AVATAR_PHOTO_PROFILE) );
        }
    }

    /**
     *
     * @param fragmentInstance Remplazamos el fragmento en nuestro contenedor pasando la instancia de ese fragmento elegido
     */

    private void fragmentTransactionReplace(Fragment fragmentInstance) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragmentInstance)
                .commit();
    }


}
