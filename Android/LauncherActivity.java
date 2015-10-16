package theancients.provolasuite;


import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.media.MediaPlayer;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

public class LauncherActivity extends ActionBarActivity {

    MediaPlayer menuMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        menuMusic = MediaPlayer.create(LauncherActivity.this, R.raw.menusong);
        menuMusic.start();
        setContentView(R.layout.activity_launcher);

        final MediaPlayer buttonSound = MediaPlayer.create(LauncherActivity.this, R.raw.clicksound);

        Button btnProvola = (Button)findViewById(R.id.button);
        Button btnTiled = (Button)findViewById(R.id.button2);
        //Provola button click listener
        btnProvola.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                buttonSound.start();
                startActivity(new Intent("android.intent.action.GAME"));
                finish();
            }
        });
        //tiled brain button click listener
        btnTiled.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                buttonSound.start();
                startActivity(new Intent("android.intent.action.GAME"));
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
        //subMenu.inflate(R.menu.sub_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.button:
                startActivity(new Intent("android.intent.action.GAME"));
                return true;

            case R.id.button2:
                startActivity(new Intent("android.intent.action.GAME"));
                return true;
        }
        return false;
    }
}
