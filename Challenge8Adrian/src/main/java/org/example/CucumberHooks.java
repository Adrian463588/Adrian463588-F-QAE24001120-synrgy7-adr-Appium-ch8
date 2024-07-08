package org.example;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;

public class CucumberHooks {

    @BeforeAll
    public static void beforeAll() {
        BaseTest.setUp();
    }

    @AfterAll
    public static void afterAll() {
        BaseTest.tearDown();
    }

    @After
    public static void after() {
        BaseTest.resetApp();
    }
}
