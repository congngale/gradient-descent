import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by congngale on 4/17/17.
 */
public class ReadInput {

    private static final String DATA_SAMPLE_PATTERN = "Data samples";
    private static final String START_A_PATTERN = "trn_params.start_a";
    private static final String START_B_PATTERN = "trn_params.start_b";
    private static final String NUMBER_FOLD_PATTERN = "trn_params.num_folds";
    private static final String ITERATION_PATTERN = "trn_params.num_iterations";
    private static final String LEARNING_RATE_PATTERN = "trn_params.learning_rate";

    private double a;
    private double b;
    private int fold;
    private double rate;
    private int iteration;
    private List<Input> trn;
    private List<Input> tst;
    private List<Input> input;

    public ReadInput(String path) {
        //init array
        input = new ArrayList<>();
        //read input
        readFile(path);
    }

    private void readFile(String path) {
        try {
            //init buffer
            String line;
            boolean foundData = false;
            BufferedReader br = new BufferedReader(new FileReader(path));

            //read data
            while ((line = br.readLine()) != null) {
                if (foundData) {
                    if (line.contains(" ")) {
                        double x = 0.0;
                        double t = 0.0;
                        String[] its = line.split(" ");
                        if (its.length > 0) {
                            x = Double.parseDouble(its[0]);
                        }
                        for (int i = 1; i < its.length; i++) {
                            String c = its[i];
                            if (!c.isEmpty()) {
                                t = Double.parseDouble(c);
                            }
                        }
                        //add data into input
                        input.add(new Input(x, t));
                    }
                } else {
                    if (line.contains(DATA_SAMPLE_PATTERN)) {
                        foundData = true;
                    } else {
                        String[] its = line.replace(" ", "").split(":");
                        if (line.contains(ITERATION_PATTERN)) {
                            if (its.length > 1) {
                                iteration = Integer.parseInt(its[1]);
                            }
                        } else if (line.contains(LEARNING_RATE_PATTERN)) {
                            if (its.length > 1) {
                                rate = Double.parseDouble(its[1]);
                            }
                        } else if (line.contains(START_A_PATTERN)) {
                            if (its.length > 1) {
                                a = Double.parseDouble(its[1]);
                            }
                        } else if (line.contains(START_B_PATTERN)) {
                            if (its.length > 1) {
                                b = Double.parseDouble(its[1]);
                            }
                        } else if (line.contains(NUMBER_FOLD_PATTERN)) {
                            if (its.length > 1) {
                                fold = Integer.parseInt(its[1]);
                            }
                        }
                    }
                }
            }

            //close buffer
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void splitInput(int position) {

        //clear input
        trn = new ArrayList<>();
        tst = new ArrayList<>();

        //check input
        if (position > 0 && position <= fold) {
            //init
            int end;
            int start;

            //get total
            int total = input.size();

            //get modulo number
            int mod = total % fold;

            //update total
            total -= mod;

            //get split number
            int split = total / fold;

            //check position
            if (position < fold) {
                //get end
                end = position * split;

                //get start
                start = end - split;
            } else {
                //get end
                end = total + mod;

                //get start
                start = end - split - mod;
            }

            //zero index
            end -= 1;

            //update trn and tst
            for (int i = 0; i < input.size(); i++) {
                //check position
                if (i < start || i > end) {
                    trn.add(input.get(i));
                } else {
                    tst.add(input.get(i));
                }
            }
        }
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public int getFold() {
        return fold;
    }

    public double getRate() {
        return rate;
    }

    public int getIteration() {
        return iteration;
    }

    public List<Input> getTrn() {
        return trn;
    }

    public List<Input> getTst() {
        return tst;
    }

    public int getInputSize() {
        return input.size();
    }
}
