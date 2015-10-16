package theancients.provolasuite;

/**
 * Created by TheAncients.
 */
import java.io.FileNotFoundException;
import java.io.InputStream;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ViewSwitcher;


public class PuzzleActivity extends Activity {
    private GameBoard board;
    private int screenOrientation;
    private Bitmap sourceImage;
    private Context context;
    private ViewSwitcher inGameViewSwitcher;
    private PuzzleCreator creator;
    private ShakeDetector mShakeDetector;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;

    private class PauseDialog extends Dialog implements android.view.View.OnClickListener{

        public PauseDialog(){
            super(PuzzleActivity.this, R.style.PauseMenuStyle);
            this.setContentView(R.layout.pause_menu);
            Button resumeButton = (Button) findViewById(R.id.pausemenu_resumeButton);
            resumeButton.setOnClickListener(this);
            Button quitButton = (Button) findViewById(R.id.pausemenu_quitButton);
            quitButton.setOnClickListener(this);
        }

        public void onClick(View v) {

            switch(v.getId()){
                case R.id.pausemenu_resumeButton:
                    this.dismiss();
                    break;
                case R.id.pausemenu_quitButton:
                    finish();
                    break;
            }
        }
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screenOrientation = getIntent().getIntExtra(MainMenuActivity.EXTRA_BOARD_ORIENTATION, 1);

        //locking the app in needed position
        if(screenOrientation == GameBoard.ORIENTATION_PORTRAIT)
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        else
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        //making the app full screen
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_puzzle);

        //Setting backgrounds to black.
        findViewById(R.id.centerLayout).setBackgroundColor(Color.BLACK);
        findViewById(R.id.backgroundLayout).setBackgroundColor(Color.BLACK);

        inGameViewSwitcher = (ViewSwitcher) findViewById(R.id.inGameViewSwitcher);

        //Crating a game board.
        board = new GameBoard(decodeGameSizeFromIntent(),
                (RelativeLayout) findViewById(R.id.centerLayout),
                screenOrientation, this);

        sourceImage = loadBitmapFromIntent();

        ImageView preview = (ImageView) findViewById(R.id.previewImageView);
        preview.setImageBitmap(sourceImage);

        context = this.getApplicationContext();

        creator = new PuzzleCreator(sourceImage, board, context);
        board.loadTiles(creator.createPuzzle());
        board.drawBoard();

        // ShakeDetector initialization
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null){
            mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            mShakeDetector = new ShakeDetector(new ShakeDetector.OnShakeListener() {
                @Override
                public void onShake() {
                    Toast.makeText(context, "Shake it baby", Toast.LENGTH_LONG).show();
                    creator = new PuzzleCreator(sourceImage, board, context);
                    board.loadTiles(creator.createPuzzle());
                    board.drawBoard();
                }
            });
        }
        else {
            Toast.makeText(context, "No shaking possible", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_puzzle, menu);
        return true;
    }

    private Dimension decodeGameSizeFromIntent(){

        Dimension size;

        String str = getIntent().getStringExtra(MainMenuActivity.EXTRA_GAMESIZE);

        String[] gameSizes = getResources().getStringArray(R.array.gamesizes);

        if(str.equals(gameSizes[0])) size = new Dimension(2,3);
        else if(str.equals(gameSizes[1])) size = new Dimension(3,5);
        else if(str.equals(gameSizes[2])) size = new Dimension(4,7);
        else if(str.equals(gameSizes[3])) size = new Dimension(6,10);
        else
            throw new RuntimeException("Decoding game size from intent failed. String does not match.");

        return size;
    }

    private Bitmap loadBitmapFromIntent(){

        Bitmap selectedImage = null;
        Uri imgUri = getIntent().getParcelableExtra(MainMenuActivity.EXTRA_IMGURI);

        try{
            InputStream imageStream = getContentResolver().openInputStream(imgUri);
            selectedImage = BitmapFactory.decodeStream(imageStream);
        }catch(FileNotFoundException ex){
            Log.e("LOADING ERROR", "Cannot load picture from the given URI", ex);
        }
        return selectedImage;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }

    protected Dialog createDialog() {
        return new PauseDialog();
    }

    @Override
    public void onBackPressed() {
        createDialog().show();
    }

    public void inGameButtonsOnClick(View view){
        switch(view.getId()){

            case R.id.previewButton:
                inGameViewSwitcher.showNext();
                break;

            case R.id.backToGameButton:
                inGameViewSwitcher.showPrevious();
                break;
        }
    }
}

