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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class MorgueActivity extends ActionBarActivity {
    String attempts, time, size;
    private final static String SCOREBOARD = "scoreboard.txt";
    EditText pName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morgue);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int value = extras.getInt("Attempts");
            attempts = "" + value;
            time = extras.getString("Time");
            size = extras.getString("Size");
        }
        ((TextView)findViewById(R.id.size)).setText("Size: " + size);
        ((TextView)findViewById(R.id.attempts)).setText("Attempts: " + attempts);
        ((TextView)findViewById(R.id.time)).setText("Time taken: " + time);
        pName = (EditText)findViewById(R.id.playerName);
        final MediaPlayer buttonSound = MediaPlayer.create(MorgueActivity.this, R.raw.clicksound);

        Button btnSave = (Button)findViewById(R.id.morgueSave);
        Button btnAgain = (Button)findViewById(R.id.morgueAgain);
        Button btnQuit = (Button)findViewById(R.id.morgueQuit);
        Button btnHighScore = (Button)findViewById(R.id.highScores);
        Button btnClearHighScore = (Button)findViewById(R.id.clearHighScores);

        //high score button click event
        btnClearHighScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSound.start();
                clearScores();
            }
        });

        //high score button click event
        btnHighScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSound.start();
                showScores();
            }
        });

        //save button click event
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSound.start();
                saveScore();
            }
        });

        //play again button click event
        btnAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSound.start();
                startActivity(new Intent("android.intent.action.GAME"));
            }
        });

        //quit button click event
        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSound.start();
                Intent intent = new Intent("android.intent.action.FINISH");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }

    public void showScores(){
        try {
            InputStream in = openFileInput(SCOREBOARD);
            if (in != null) {
                InputStreamReader tmp = new InputStreamReader(in);
                BufferedReader reader = new BufferedReader(tmp);
                String str;
                StringBuilder buf = new StringBuilder();
                while ((str = reader.readLine()) != null) {
                    buf.append(str+"\n");
                }
                in.close();
                ((TextView)findViewById(R.id.highScoreList)).setText(buf.toString());
            }
        }
        catch (java.io.FileNotFoundException e) {
            // that's OK, we probably haven't created it yet
        }
        catch (Throwable t) {
            Toast.makeText(this, "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
        }
    }



    public void saveScore () {
        try {
            OutputStreamWriter out = new OutputStreamWriter(openFileOutput(SCOREBOARD, MODE_APPEND));

            String text = pName.getText().toString() + "," + size + "," + attempts + "," + time;
            out.write(text);
            out.write("\n");
            out.close();
            Toast.makeText(this, "The contents are saved in the file.", Toast.LENGTH_LONG).show();

        } catch (Throwable t) {
            Toast.makeText(this, "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
        }
        //Toast.makeText(this, "Save not implemented yet.", Toast.LENGTH_SHORT).show();
    }

    public void clearScores(){
        boolean deleted = deleteFile(SCOREBOARD);
        if(deleted){
            Toast.makeText(this, "Deleted!", Toast.LENGTH_LONG).show();
            ((TextView)findViewById(R.id.highScoreList)).setText("");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater subMenu = getMenuInflater();
        subMenu.inflate(R.menu.menu_morgue, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.again:
                startActivity(new Intent("android.intent.action.GAME"));
                return true;

            case R.id.quit:
                finish();
                return true;

            case R.id.high:
                showScores();
                return true;

            case R.id.clear_high:
                clearScores();
                return true;
        }
        return false;
    }
}
