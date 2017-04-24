import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by congngale on 4/21/17.
 */
public class WriteOutput {

    private List<Result> results;

    public WriteOutput(List<Result> results) {
        this.results = results;
    }

    public void write(String path) {
        try {
            //init buffer writer
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path));

            //loop all result
            for (Result result : results) {
                bufferedWriter.write(result.toString());
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }

            //close buffer write
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
