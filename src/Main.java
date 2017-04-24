import java.util.ArrayList;
import java.util.List;

/**
 * Created by congngale on 4/17/17.
 */
public class Main {

    private static final String INPUT_PATH = "data.0.txt";
    private static final String OUTPUT_PATH = "std.0.txt";

    public static void main(String[] args) {
        //init results
        List<Result> results = new ArrayList<>();
        //read information
        ReadInput input = new ReadInput(INPUT_PATH);

        //loop all fold number
        for (int i = 1; i <= input.getFold(); i++) {
            //split input
            input.splitInput(i);
            //init gradient descent
            GradientDescent gradientDescent = new GradientDescent(input.getA(), input.getB(), input.getRate(),
                    input.getIteration(), input.getTrn());
            //run gradient descent
            Output rs = gradientDescent.run();
            //compute error forecast
            ErrorForecast errorForecast = new ErrorForecast(rs.getA(), rs.getB(), input.getTst());
            //create new result
            Result result = new Result(rs.getA(), rs.getB(), errorForecast.getERMSD(), errorForecast.getErrorFrequency());
            //add result
            results.add(result);
        }

        //write output
        WriteOutput writeOutput = new WriteOutput(results);
        writeOutput.write(OUTPUT_PATH);
    }
}
