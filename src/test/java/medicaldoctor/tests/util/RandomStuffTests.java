package medicaldoctor.tests.util;

import junit.framework.Assert;
import medicaldoctor.entities.LogRecord;
import org.junit.Test;

/**
 * Tests because I can't figure out java.
 */
public class RandomStuffTests {
    
    @Test
    public void stringFormatting() {
        Assert.assertEquals("apple banana cherry",
                String.format("apple %s cherry", "banana"));
    }
    
    @Test
    public void classEquals() {
        Assert.assertTrue((new LogRecord()).getClass().equals(LogRecord.class));
    }
    
}
