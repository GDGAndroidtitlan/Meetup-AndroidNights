package com.androititlan.gdg.androiddesignlibrary.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.androititlan.gdg.androiddesignlibrary.R;
import com.androititlan.gdg.androiddesignlibrary.api.MyTwitterApiClient;
import com.androititlan.gdg.androiddesignlibrary.ui.fragment.TweetFragment;
import com.androititlan.gdg.androiddesignlibrary.util.ConfigPreferences;
import com.androititlan.gdg.androiddesignlibrary.util.Configure;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.models.User;

import java.util.StringTokenizer;

import butterknife.Bind;
import butterknife.ButterKnife;



public class MainActivity extends AppCompatActivity {


    @Bind(R.id.login_button)
    TwitterLoginButton twitterLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configure.getTwitterAuthConfig(this);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, TweetFragment.getInstance()).commit();
        }



        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        twitterLogin();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        twitterLoginButton.onActivityResult(requestCode, resultCode, data);

    }

    /**
     * Este metodo Inicia el Login con twitter sobrescribe dos metodos los cuales son
     * <p/>
     * succes: Si el Login tuvo exito nos devuelve la sessión y algunas cosas mas.
     * failure: Si tuvimos algun problema al iniciar sesión
     */
    private void twitterLogin() {

        twitterLoginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                /*Devuelve  TwitterSession lo que permite hacer llamadas a la API REST
                Nota:  el SDK de Twitter contiene un TwitterClient por defecto con algunos servicios disponibles
                puedes customizar tu client y tus services.*/
                TwitterSession twitterSession =     Twitter.getSessionManager().getActiveSession();

                getTwitterProfile(twitterSession, result.data.getUserId(), result.data.getUserName());
                ConfigPreferences.setId(MainActivity.this,result.data.getUserId());

            }

            @Override
            public void failure(TwitterException exception) {
                // capturamos excepciones
                exception.printStackTrace();
                Toast.makeText(MainActivity.this, "Parece que hay un problema con tu conexión intentalo mas tarde.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Este metodo sirve para obtener el perfil del usuario
     *
     * @param twitterSession pasamos la contiene la sessión activa despues de hacer el Login con twitter
     * @param userId         el id de nuestro usuario twitter
     * @param userName       nombre de usuario de nuestro usuario twitter
     */
    private void getTwitterProfile(TwitterSession twitterSession, Long userId, final String userName) {
        System.out.println(userId);
        MyTwitterApiClient myTwitterApiClient = new MyTwitterApiClient(twitterSession);
        myTwitterApiClient.getTwitterService().show(userId, null, true ,new Callback<User>() {
            @Override
            public void success(Result<User> result) {
                Toast.makeText(MainActivity.this,result.data.profileImageUrl,Toast.LENGTH_SHORT).show();
               Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                System.out.println(result.data.profileImageUrl);

                Bundle bundle = Configure.getProfile(result.data.name, userName, result.data.profileImageUrl, result.data.profileBannerUrl);
                intent.putExtra(Configure.AVATAR_PROFILE, bundle);
                startActivity(intent);

            }

            @Override
            public void failure(TwitterException e) {
                e.printStackTrace();
            }
        });
    }


    /**
     *
     * @param cadena hacemos un hack seaparamos el string para obtener la imagen original de perfil. (Puedes leer dimensiones Imagenes en twitter api para mas info.)
     * @return devolvemos la url especifica que es la que obtiene la foto del usuario.
     */
    private String getUrlPhotoProfile(String cadena){
            return cadena.substring(0,cadena.indexOf(Configure.SUFIXE)) + Configure.JPEG_EXTENSION;

    }

}
