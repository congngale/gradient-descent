import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Created by congngale on 4/19/17.
 */
public class GradientDescent {

    private double a;

    private double b;

    private double rate;

    private int iteration;

    private List<Input> input;

    public GradientDescent(double a, double b, double rate, int iteration, List<Input> input) {
        this.a = a;
        this.b = b;
        this.rate = rate;
        this.input = input;
        this.iteration = iteration;
    }

    public Output run() {
        //loop number of iteration
        for (int i = 0; i < iteration; i++) {
            startDescent();
        }

        return new Output(a, b);
    }

    private void startDescent() {
        //init gradient
        double gVector;
        double ga = 0.0;
        double gb = 0.0;

        //loop all input
        for (Input in : input) {
            //compute a gradient
            ga += (((a * in.getX()) + b - in.getT()) * in.getX());

            //compute b gradient
            gb += ((a * in.getX()) + b - in.getT());
        }


        //sum of ga and gb
        gVector = Math.sqrt(Math.pow(ga, 2) + Math.pow(gb, 2));

        //normalization gradient a
        ga = ga / gVector;

        //normalization gradient b
        gb = gb / gVector;

        //compute new a
        a = a - (rate * ga);

        //compute new b
        b = b - (rate * gb);
    }
}
