package theancients.provolasuite;

import android.graphics.drawable.Drawable;

/**
 * Created by TheAncients.
 */
public class PuzzleTile {

    private Drawable drawable;
    private int number;

    public PuzzleTile(Drawable d, int n){
        drawable = d;
        number = n;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public int getNumber() {
        return number;
    }

}
