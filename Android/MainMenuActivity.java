package theancients.provolasuite;

/**
 * Created by TheAncients.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ViewSwitcher;



public class MainMenuActivity extends Activity {

    public final static String EXTRA_GAMESIZE = "com.tiledbrain.frkfba.tiledbrain.GAMESIZE";
    public final static String EXTRA_IMGURI = "com.tiledbrain.frkfba.tiledbrain.IMGURI";
    public final static String EXTRA_BOARD_ORIENTATION = "com.tiledbrain.frkfba.tiledbrain.BOARD_ORIENTATION";

    public final static int PHOTO_FROM_MEMORY_REQUESTED = 10;
    public final static int PHOTO_FROM_CAMERA_REQUESTED = 20;

    public final static String MAIN_FOLDER = "/com.tiledbrain.frkfba.tiledbrain.tilepuzzle/";

    private Uri tempPictureUri;

    private ViewSwitcher menuViewSwitcher;
    private boolean shouldSwitch = true;

    private Spinner gameSizeSpinner;
    private Button playButton;

    private Bitmap selectedImage;
    private Uri imageUri;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //making the app full screen
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main_menu);
        menuViewSwitcher = (ViewSwitcher) findViewById(R.id.mainMenuViewSwitcher);
        gameSizeSpinner = (Spinner) findViewById(R.id.newGameMenuGameSizeSpinner);

        //At this point picture has not been chosen yet, so the PLAY button has to be disabled.
        playButton = (Button) findViewById(R.id.playButton);
        playButton.setEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        return true;
    }

    public void newGameButtonOnClick(View view){
        menuViewSwitcher.showNext();
    }

    public void mainMenuQuitOnClick(View view){
        finish();
    }

    public void backToMainOnClick(View view){
        menuViewSwitcher.showPrevious();
    }

    public void pickImageOnClick(View view){
        shouldSwitch = false;
        Intent i = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, PHOTO_FROM_MEMORY_REQUESTED);
    }

    public static  boolean isCameraAvailable(Context context) {
        PackageManager pm = context.getPackageManager();
        return (pm.hasSystemFeature(PackageManager.FEATURE_CAMERA));
    }

    public static boolean freeSpaceAvailable(){
        long freeSpace =   Environment.getExternalStorageDirectory().getFreeSpace();
        int megAvailable = ((int)freeSpace / 1024) / 1024;
        Log.e("","Available MB : "+megAvailable);
        Log.i("Frk: free memory in MB", ""+megAvailable);
        return(megAvailable > 100);

    }

    public void TakePhotoOnClick(View view){
        if(isCameraAvailable(this) && freeSpaceAvailable()){
            shouldSwitch = false;
            //Creating an intent to take a photo.
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            //Creating a file where temporary picture will be kept.
            File photo;
            try
            {
                // place where to store camera taken picture
                photo = this.createGameFile("puzzle", ".jpg");
                photo.delete();
            }
            catch(Exception e)
            {

                Log.v("FrK", "Can't create file to take picture!");
                Log.d("FrK", e.getMessage());
                return;
            }

            tempPictureUri = Uri.fromFile(photo);

            //Passing the uri to the intent.
            i.putExtra(MediaStore.EXTRA_OUTPUT, tempPictureUri);
            i.putExtra(MediaStore.EXTRA_SCREEN_ORIENTATION, ActivityInfo.SCREEN_ORIENTATION_SENSOR);

            //And there we go!
            startActivityForResult(i, PHOTO_FROM_CAMERA_REQUESTED);
        }
        else{
            if(!isCameraAvailable(this)){
                Toast.makeText(this, "Sorry, no camera on this device!", Toast.LENGTH_LONG).show();
                return;
            }
            if(!freeSpaceAvailable()){
                Toast.makeText(this, "Sorry, not enough free space on this device SD!", Toast.LENGTH_LONG).show();
                return;
            }
        }
    }

    /**
     * Creates temporary file in .temp directory on the SD card.
     * @param part filename
     * @param ext extension
     * @return the file
     * @throws Exception
     */
    private File createGameFile(String part, String ext) throws Exception
    {
        //Getting external storage path, and .temp directory in it.
        File mainDir= Environment.getExternalStorageDirectory();
        mainDir=new File(mainDir.getAbsolutePath()+MAIN_FOLDER);

        //If .temp does not exists, it is created.
        if(!mainDir.exists()) mainDir.mkdir();

        //return new File(mainDir.getAbsolutePath().toString(), part+ext);
        return new File(mainDir.getAbsolutePath(), part+ext);
    }

    public void playOnClick(View View){
        Intent intent = new Intent(this, PuzzleActivity.class);
        String[] gameSizes = getResources().getStringArray(R.array.gamesizes);
        intent.putExtra(EXTRA_GAMESIZE, gameSizes[gameSizeSpinner.getSelectedItemPosition()]);
        intent.putExtra(EXTRA_IMGURI, imageUri);

        int orientation;	//Determining screen orientation.
        orientation = (selectedImage.getWidth()>selectedImage.getHeight()) ?
                GameBoard.ORIENTATION_HORIZONTAL : GameBoard.ORIENTATION_PORTRAIT;
        intent.putExtra(EXTRA_BOARD_ORIENTATION, orientation);

        shouldSwitch = true;
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == PHOTO_FROM_MEMORY_REQUESTED && resultCode == RESULT_OK){
            updateSelectedPicture(data.getData());
            playButton.setEnabled(true);
        }

        if(requestCode == PHOTO_FROM_CAMERA_REQUESTED && resultCode == RESULT_OK){
            updateSelectedPicture(tempPictureUri);
            playButton.setEnabled(true);
        }
    }

    /**
     * Updates selected picture by choosing proper thumbnail and
     * preparing everything to be passed with an intent to start the game.
     * @param uri URI containing picture that should be used
     */
    private void updateSelectedPicture(Uri uri){
        try{
            //Writing uri to the variable that will be later passed with intent.
            imageUri = uri;
            Context context = this.getApplicationContext();
            //Opening the input stream and receiving Bitmap. is the bug here ???
            InputStream imageStream = getContentResolver().openInputStream(imageUri);
            selectedImage = BitmapFactory.decodeStream(imageStream);

            //And setting the thumbnail.
            ImageView iv = (ImageView) findViewById(R.id.selectedImageView);
            //iv.setImageDrawable(new BitmapDrawable(selectedImage)); //works? but deprecated
            iv.setImageDrawable(new BitmapDrawable(context.getResources(),selectedImage));

        }catch(FileNotFoundException ex){
            Log.e("File not found", "Cannot find a file under received URI");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(shouldSwitch)
            if(menuViewSwitcher.getDisplayedChild()==1)
                menuViewSwitcher.showPrevious();
    }
}
