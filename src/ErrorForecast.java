import java.util.ArrayList;
import java.util.List;

/**
 * Created by congngale on 4/20/17.
 */
public class ErrorForecast {

    private double a;

    private double b;

    private List<Input> input;

    public ErrorForecast(double a, double b, List<Input> input) {
        this.a = a;
        this.b = b;
        this.input = input;
    }

    public double getERMSD() {
        double rs = 0.0;

        //loop all input
        for (Input in : input) {
            rs += Math.pow(((a * in.getX()) + b - in.getT()), 2);
        }

        //compute ermsd
        rs = Math.sqrt(rs / input.size());

        return rs;
    }

    public List<Double> getErrorFrequency() {
        //init variables
        double vMin;
        double vMax;
        double sigma = 0.0;
        double errorSegment;
        double errorLevel1 = 0.0;
        double errorLevel2 = 0.0;
        double errorLevel3 = 0.0;
        double errorLevel4 = 0.0;
        double errorLevel5 = 0.0;
        double errorLevel6 = 0.0;
        double errorLevel7 = 0.0;
        double errorLevel8 = 0.0;
        double errorLevel9 = 0.0;
        double errorLevel10 = 0.0;
        double errorAverage = 0.0;
        List<Double> rs = new ArrayList<>();
        List<Double> errors = new ArrayList<>();
        List<Double> fullErrors = new ArrayList<>();

        //loop all input
        for (Input in : input) {
            //compute error
            double error = (a * in.getX() + b) - in.getT();
            //sum error
            errorAverage += error;
            //add error to list
            fullErrors.add(error);
        }

        //compute error average
        errorAverage = errorAverage / input.size();

        //loop all input
        for (Double err : fullErrors) {
            //sum error difference
            sigma += Math.pow(err - errorAverage, 2);
        }

        //compute epsilon
        sigma = Math.sqrt(sigma / input.size());

        //compute V min
        vMin = errorAverage - (3.0 * sigma);

        //compute V max
        vMax = errorAverage + (3.0 * sigma);

        //update error list
        for (Double err : fullErrors) {
            //check error in range
            if (err >= vMin && err <= vMax) {
                //add to error list
                errors.add(err);
            }
        }

        //error segment
        errorSegment = ((vMax - vMin) / 10);

        //loop all error
        for (Double err : errors) {
            //compute vMin - L1
            if (err >= vMin && err < (vMin + errorSegment)) {
                errorLevel1 += 1.0;
            } else if (err >= (vMin + errorSegment) && err < (vMin + (errorSegment * 2))) {
                errorLevel2 += 1.0;
            } else if (err >= (vMin + (errorSegment * 2)) && err < (vMin + (errorSegment * 3))) {
                errorLevel3 += 1.0;
            } else if (err >= (vMin + (errorSegment * 3)) && err < (vMin + (errorSegment * 4))) {
                errorLevel4 += 1.0;
            } else if (err >= (vMin + (errorSegment * 4)) && err < (vMin + (errorSegment * 5))) {
                errorLevel5 += 1.0;
            } else if (err >= (vMin + (errorSegment * 5)) && err < (vMin + (errorSegment * 6))) {
                errorLevel6 += 1.0;
            } else if (err >= (vMin + (errorSegment * 6)) && err < (vMin + (errorSegment * 7))) {
                errorLevel7 += 1.0;
            } else if (err >= (vMin + (errorSegment * 7)) && err < (vMin + (errorSegment * 8))) {
                errorLevel8 += 1.0;
            } else if (err >= (vMin + (errorSegment * 8)) && err < (vMin + (errorSegment * 9))) {
                errorLevel9 += 1.0;
            } else if (err >= (vMin + (errorSegment * 9)) && err <= vMax) {
                errorLevel10 += 1.0;
            }
        }

        //update result
        rs.add(errorLevel1 / errors.size());
        rs.add(errorLevel2 / errors.size());
        rs.add(errorLevel3 / errors.size());
        rs.add(errorLevel4 / errors.size());
        rs.add(errorLevel5 / errors.size());
        rs.add(errorLevel6 / errors.size());
        rs.add(errorLevel7 / errors.size());
        rs.add(errorLevel8 / errors.size());
        rs.add(errorLevel9 / errors.size());
        rs.add(errorLevel10 / errors.size());

        return rs;
    }
}
