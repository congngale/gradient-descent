import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Created by congngale on 4/21/17.
 */
public class Result {

    private static final String PADDING_LENGTH = "%1$-10s";

    private static final double ROUND_EXPECTATION = 100000.0;

    private double a;

    private double b;

    private double ermsd;

    private List<Double> errorFrequency;

    public Result(double a, double b, double ermsd, List<Double> errorFrequency) {
        this.a = Math.round(a * ROUND_EXPECTATION) / ROUND_EXPECTATION;
        this.b = Math.round(b * ROUND_EXPECTATION) / ROUND_EXPECTATION;
        this.ermsd = Math.round(ermsd * ROUND_EXPECTATION) / ROUND_EXPECTATION;
        this.errorFrequency = errorFrequency;
    }

    @Override
    public String toString() {
        //init result
        StringBuilder rs = new StringBuilder();
        //append a
        rs.append(String.format(PADDING_LENGTH, new BigDecimal(a).setScale(5, RoundingMode.HALF_UP).toString()));
        //append b
        rs.append(String.format(PADDING_LENGTH, new BigDecimal(b).setScale(5, RoundingMode.HALF_UP).toString()));
        //append ermsd
        rs.append(String.format(PADDING_LENGTH, new BigDecimal(ermsd).setScale(5, RoundingMode.HALF_UP).toString()));

        //loop all error
        for (Double err : errorFrequency) {
            err = Math.round(err * ROUND_EXPECTATION) / ROUND_EXPECTATION;
            rs.append(String.format(PADDING_LENGTH, new BigDecimal(err).setScale(5, RoundingMode.HALF_UP).toString()));
        }

        return rs.toString();
    }
}
