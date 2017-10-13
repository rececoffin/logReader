import com.company.logMessage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by ReceCoffin on 10/12/2017.
 * Unit tests for the logMessage and logReader classes
 */
public class tests {

    @Test
    public void testMessageParsing(){
        //create a logMessage given a single message string, check values are extracted correctly
        logMessage lm = new logMessage("[INFO] [20170901 135729] [index.go:125] Starting search");
        assertTrue(lm.level.equalsIgnoreCase("info"));
        assertTrue(lm.timeStamp.equalsIgnoreCase("2017-09-01 13:57:29"));
        assertTrue(lm.file.equalsIgnoreCase("index.go"));
        assertTrue(lm.fileLine == 125);
        assertTrue(lm.message.equalsIgnoreCase("starting search "));
    }

    @Test
    public void testObjectEquivalence(){
        //create multiple logMessage objects and test that they compare correctly
        logMessage lm = new logMessage("[INFO] [20170901 135729] [index.go:125] Starting search");
        logMessage lm2 = new logMessage("[INFO] [20170901 135729] [index.go:125] Starting search");
        logMessage lm3 = new logMessage("[INFO] [20170901 135729] [file.go:120] Starting search");
        //equal objects
        assertTrue(lm.equals(lm2));
        //not equal objects
        assertTrue(!lm.equals(lm3));
    }
}
