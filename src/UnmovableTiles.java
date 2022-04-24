import java.util.ArrayList;
import java.util.Arrays;

public class UnmovableTiles {
    public static final String END = "End";
    public static final String PIPE_STATIC = "PipeStatic";
    public static final String STARTER = "Starter";

    public static ArrayList<String> getUnmovableTiles() {
        return new ArrayList<>(
                Arrays.asList(
                        END, PIPE_STATIC, STARTER
                )
        );
    }
}
