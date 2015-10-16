package theancients.provolasuite;

/**
 * Created by TheAncients.
 */
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends ActionBarActivity {
    MediaPlayer menuMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        menuMusic = MediaPlayer.create(MenuActivity.this, R.raw.menusong);
        menuMusic.start();
        setContentView(R.layout.activity_menu);

        final MediaPlayer buttonSound = MediaPlayer.create(MenuActivity.this, R.raw.clicksound);

        Button btnStart = (Button)findViewById(R.id.startButton);
        Button btnMorgue = (Button)findViewById(R.id.morgueButton);

        //start button click event
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSound.start();
                startActivity(new Intent("android.intent.action.GAME"));
                finish();
            }
        });

        //morgue button click event
        btnMorgue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSound.start();
                startActivity(new Intent("android.intent.action.MORGUE"));
                finish();
            }
        });
    }

    @Override
    protected void onPause(){

        super.onPause();
        menuMusic.release();
    }

    @Override
    protected void onResume(){
        super.onResume();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater subMenu = getMenuInflater();
        subMenu.inflate(R.menu.sub_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.toGame:
                startActivity(new Intent("android.intent.action.GAME"));
                return true;

            case R.id.toMorgue:
                startActivity(new Intent("android.intent.action.MORGUE"));
                return true;

        }
        return false;
    }
}