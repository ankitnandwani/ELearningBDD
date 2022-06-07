package com.ui.cucumber;


import com.ui.cucumber.Repo.ElucidatLearningPage;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Reporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Browser {

    private WebDriver driver;
    private Properties prop;
    private ElucidatLearningPage elucidatLearningPage;

    @Before
    public void setUp(){

        loadProperties();

        String env = Reporter.getCurrentTestResult() == null ? "chrome" : Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("env");

        switch (env){
            case "chrome" :
                System.out.println("Chromedriver path " + prop.getProperty("driverExecutable") + "/chromedriver");
                System.setProperty("webdriver.chrome.driver",prop.getProperty("driverExecutable") + "/chromedriver");
                driver = new ChromeDriver();
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                break;

            case "firefox" :
                System.setProperty("webdriver.gecko.driver",prop.getProperty("driverExecutable") + "/geckodriver");
                driver = new FirefoxDriver();
                break;

            case "safari" :
                driver = new SafariDriver();
                break;

            default:
                break;

        }

        elucidatLearningPage = new ElucidatLearningPage(driver);

    }

    public WebDriver getDriver(){
        return driver;
    }

    public Properties getProp(){
        return prop;
    }

    public ElucidatLearningPage getElucidatLearningPage(){
        return elucidatLearningPage;
    }

    @After
    public void tearDown() {
        if(driver != null) driver.quit();

    }

    private void loadProperties() {
        FileInputStream f = null;
        try{
            f = new FileInputStream(new File("src/test/Resources/com/ui/cucumber/Config.properties"));
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        prop = new Properties();
        try{
            prop.load(f);
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
