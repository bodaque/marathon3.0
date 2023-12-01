package com.epam.marathon;

import com.epam.marathon.auto.core.ui.DriverManager;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterMethod;

@Slf4j
public abstract class UITestBase {

    @AfterMethod
    public void tearDown() {
        DriverManager.getInstance().tearDown();
    }
}
