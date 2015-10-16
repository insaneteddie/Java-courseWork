package theancients.provolasuite;

/**
 * Created by TheAncients.
 */
import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

public class GameBoard implements View.OnClickListener {

    public final static int ORIENTATION_PORTRAIT = 0;
    public final static int ORIENTATION_HORIZONTAL = 1;

    private RelativeLayout screen;
    private Context context;
    private Dimension screenResolution; // e. g. 480 x 854 like defy
    private Dimension gameSize = null; // e. g. 3 x 5
    private int tileSize; // length of the side of tile in px, e. g. 160
    private int orientation;

    private GameTile[][] tiles;

    public GameBoard(Dimension gameSize, RelativeLayout scr, int orientation, Context con){
        this.gameSize = gameSize;
        this.screen = scr;
        this.context = con;
        this.orientation = orientation;

        //If orientation is horizontal, we need to flip gameSize by passing the y value to x, and vice versa.
        if(this.orientation==ORIENTATION_HORIZONTAL){
            this.gameSize = new Dimension(gameSize.y, gameSize.x);
        }
        tiles = new GameTile[this.gameSize.x][this.gameSize.y];
        calculateTileSize();
    }

    public void loadTiles(PuzzleTile[][] res){
        for(int y=0; y<gameSize.y; y++){
            for(int x=0; x<gameSize.x; x++){
                if(res[x][y]==null)
                    tiles[x][y] = null;
                else{
                    tiles[x][y] = new GameTile(this.context, res[x][y].getDrawable(), res[x][y].getNumber());
                    tiles[x][y].setClickable(true);
                    tiles[x][y].setOnClickListener(this);
                    tiles[x][y].pos = new Dimension(x,y);
                }
            }
        }
    }

    public void calculateTileSize(){

        //Getting screen resolution.
        screenResolution = new Dimension(context.getResources().getDisplayMetrics().widthPixels,
                context.getResources().getDisplayMetrics().heightPixels);

        //And having the resolution, we can now calculate the size of the tile.
        int tSize = screenResolution.x/gameSize.x;
        if(tSize*gameSize.y>screenResolution.y){
            //That means height of the screen is too small to fit all the tiles.
            //In this case tile size needs to be recalculated, taking
            //height as important size, instead of width.
            tSize = screenResolution.y/gameSize.y;
        }
        tileSize = tSize;
    }

    public void drawBoard(){
        for(int x=0; x<gameSize.x; x++){
            for(int y=0; y<gameSize.y; y++){
                if(tiles[x][y]==null) continue;
                tiles[x][y].params = new RelativeLayout.LayoutParams(tileSize, tileSize);
                Dimension onScreen = getOnScreenCord(new Dimension(x,y));
                tiles[x][y].params.setMargins(onScreen.x, onScreen.y, 0, 0);
                screen.addView(tiles[x][y], tiles[x][y].params);
            }
        }
    }

    public void reDrawBoard(){
        screen.removeAllViews();
        for(int x=0; x<gameSize.x; x++){
            for(int y=0; y<gameSize.y; y++){
                if(tiles[x][y]==null) continue;
                screen.addView(tiles[x][y], tiles[x][y].params);
            }
        }
    }

    public Dimension getOnScreenCord(Dimension input){
        return new Dimension(tileSize*input.x, tileSize*input.y);
    }

    private boolean canBeMoved(GameTile tile){
        Dimension empty = getEmptyPosition();
        if(tile.pos.x==empty.x){
            if(empty.y==tile.pos.y-1 || empty.y==tile.pos.y+1) return true;
        }
        if(tile.pos.y==empty.y){
            if(empty.x==tile.pos.x-1 || empty.x==tile.pos.x+1) return true;
        }
        return false;
    }

    public Dimension getEmptyPosition(){

        for(int x=0; x<gameSize.x; x++){
            for(int y=0; y<gameSize.y; y++){
                if(tiles[x][y]==null) {
                    return new Dimension(x,y);
                }
            }
        }

        throw new RuntimeException("No empty space! Not a single cell is null!");
    }

    //when a tile is clicked
    synchronized public void onClick(View v) {
        //counter++;
        GameTile clickedTile = (GameTile) v;
        if(!canBeMoved(clickedTile)){
            return;
        }

        moveTileToEmpty(clickedTile);

        if(isSolved()){
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("You solved the puzzle! Congratulations!")
                    .setCancelable(false)
                    .setPositiveButton("Thanks.", null);
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    synchronized public void moveTileToEmpty(GameTile toMove){
        Dimension empty = getEmptyPosition(); // getting pos of the empty spot before change
        Dimension destOnScreen = getOnScreenCord(getEmptyPosition());
        tiles[toMove.pos.x][toMove.pos.y]=null; // old place
        tiles[empty.x][empty.y] = toMove; //new place


        toMove.pos = empty;

        toMove.params.setMargins(destOnScreen.x, destOnScreen.y, 0, 0);
        toMove.setClickable(true);
        toMove.setOnClickListener(this);

        reDrawBoard();

    }

    boolean isSolved(){
        int n = 0;
        for(int y=0; y<gameSize.y; y++){
            for(int x=0; x<gameSize.x; x++){
                if(tiles[x][y]==null){
                    return x == gameSize.x - 1 && y == gameSize.y - 1;
                }
                if(tiles[x][y].getCheckNumber()!=n) return false;
                n++;
            }
        }
        return true;
    }

    public Dimension getGameSize() {
        return gameSize;
    }

    public int getTileSize() {
        return tileSize;
    }

}
