package theancients.provolasuite;

/**
 * Created by TheAncients on 08/04/2015.
 */
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

public class FinishActivity extends ActionBarActivity {

    MediaPlayer logoMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        logoMusic = MediaPlayer.create(FinishActivity.this, R.raw.splashmusic);
        logoMusic.start();

        Thread logotimer = new Thread(){
            public void run(){
                try{
                    sleep(5000);
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

    @Override
    protected void onDestroy(){
        super.onDestroy();
        logoMusic.release();
    }


}

