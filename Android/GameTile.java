package theancients.provolasuite;

/**
 * Created by TheAncients.
 */
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class GameTile extends ImageView {

    public Dimension pos; // position on the board, not on the screen!
    public RelativeLayout.LayoutParams params;
    private int checkNumber;
    public GameTile(Context context, Drawable source, int n) {
        super(context);
        this.setImageDrawable(source);
        checkNumber = n;

    }
    public int getCheckNumber() {
        return checkNumber;
    }
}