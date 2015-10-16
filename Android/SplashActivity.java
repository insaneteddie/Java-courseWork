package theancients.provolasuite;

/**
 * Created by TheAncients.
 */
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

public class SplashActivity extends ActionBarActivity {

    MediaPlayer logoMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        logoMusic = MediaPlayer.create(SplashActivity.this, R.raw.splashmusic);
        logoMusic.start();

        Thread logotimer = new Thread(){
            public void run(){
                try{
                    sleep(5000);
                    Intent menuIntent = new Intent("android.intent.action.MENU");
                    startActivity(menuIntent);
                }
                catch(InterruptedException ex){
                    ex.printStackTrace();
                }finally{
                    finish();
                }
            }
        };
        logotimer.start();
    }

    @Override
    protected void onPause(){
        super.onPause();
        logoMusic.release();
    }

}
