package io.redbarn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoggingTest {

    @Test(groups = "Fast")
    public void Logger_InstantiatedViaFacade_IsNotNull() {
        Logger logger = LoggerFactory.getLogger(LoggingTest.class);
        Assert.assertNotNull(logger);
    }
}
