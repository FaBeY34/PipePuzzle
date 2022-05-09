import java.util.ArrayList;
import java.util.Arrays;

// this class will be used to access the list of unmovableTiles
// we know that the tile that has type  end, pipe-static or starter cannot move. So we declared them static final variables.
public class UnmovableTiles {
    public static final String END = "End";
    public static final String PIPE_STATIC = "PipeStatic";
    public static final String STARTER = "Starter";

    // this method wil return the list of these unmovable tiles.
    public static ArrayList<String> getUnmovableTiles() {
        return new ArrayList<>(
                Arrays.asList(
                        END, PIPE_STATIC, STARTER
                )
        );
    }
}
