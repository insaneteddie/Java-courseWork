package theancients.provolasuite;

/**
 * Created by TheAncients.
 */
public class Dimension {

    public final int x;
    public final int y;

    public Dimension(){
        x=0; y=0;
    }

    public Dimension(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "x:" + x + " y:" + y;
    }

    @Override
    public boolean equals(Object o) {
        Dimension other = (Dimension) o;
        return other.x == this.x && other.y == this.y;
    }
    public boolean equals(int x, int y) {
        return x == this.x && y == this.y;
    }
}
