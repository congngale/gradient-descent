/**
 * Created by congngale on 4/19/17.
 */
public class Input {

    private double x;

    private double t;

    public Input(double x, double t) {
        this.x = x;
        this.t = t;
    }

    public double getX() {
        return x;
    }

    public double getT() {
        return t;
    }

    @Override
    public String toString() {
        return "Input{" +
                "x=" + x +
                ", t=" + t +
                '}';
    }
}
