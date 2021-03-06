package com.epam.AirBaltic.util;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.apache.commons.io.FileUtils;

public class SessionHelper {
    public static DesiredCapabilities getBrowserCaps(String browser) {
        DesiredCapabilities capabilities = null;

        final String CHROMEDRIVER_WIN_PATH=PropertyLoader.getProperty("chromedriver.win.path");
        final String GECKODRIVER_WIN_PATH=PropertyLoader.getProperty("geckodriver.win.path");

        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_WIN_PATH);
                ChromeOptions chrOpt = new ChromeOptions();
                chrOpt.addArguments("test-type");
                chrOpt.addArguments("disable-plugins");
                chrOpt.addArguments("disable-extensions");
                chrOpt.addArguments("start-maximized");
                capabilities = DesiredCapabilities.chrome();
                capabilities.setCapability(ChromeOptions.CAPABILITY, chrOpt);
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", GECKODRIVER_WIN_PATH);
                capabilities = DesiredCapabilities.firefox();
                capabilities.setCapability("marionette", true);
                capabilities.setBrowserName("firefox");
                LoggingPreferences logPrefs = new LoggingPreferences();
                logPrefs.enable("driver", Level.SEVERE);
                logPrefs.enable("server", Level.SEVERE);
                logPrefs.enable("browser", Level.SEVERE);
                capabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
//               System.out.println(capabilities.toString());
                break;
            default:
                System.out.println("Browser is not supported");
        }
        return capabilities;
    }

    // set JVM option -Dbrowser=chrome in Run/Debug configuration settings to run chrome instead of firefox
    public static String selectBrowser() {
        String browserName;
        String browserFromProperty = PropertyLoader.getProperty("browser.name");
        String browserFromCommandLine = System.getProperty("browser");
            if (browserFromProperty == null && browserFromCommandLine == null) {
                browserName = "firefox";
            } else if (browserFromProperty == null) {
                browserName = browserFromCommandLine;
            } else if (browserFromCommandLine == null) {
                browserName = browserFromProperty;
            } else {
                browserName = browserFromCommandLine;
            }
        return browserName;
    }

    public  static void captureScreenShot(WebDriver webDriver, String pageClassName) throws IOException {
        File screenShotFile=((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenShotFile,new File("screenshots/"+ pageClassName +
                                        "--" + DateGenerator.getTimeStamp()+".png"));

    }

}
