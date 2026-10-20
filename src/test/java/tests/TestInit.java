package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import listeners.Listener;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import utils.SuiteConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.*;

@Listeners(Listener.class)
public class TestInit {

    private String headless;

    @BeforeSuite
    public void setParam() throws IOException {
        SuiteConfiguration conf = new SuiteConfiguration();
        headless = conf.getProperty("headless");

        Configuration.timeout = 10000; // 10 seconds
        Configuration.pollingInterval = 500; // 500 milliseconds
        Configuration.screenshots = true;
    }

    @BeforeMethod
    public void setup() {
        boolean isHeadless = Boolean.parseBoolean(headless);
        Configuration.headless = isHeadless;
        Listener.logger.info("Set up browser, headless: " + isHeadless);
        ChromeOptions chromeOptions = new ChromeOptions();

        if (isHeadless) {
            chromeOptions.addArguments("--headless=new");
        }
        chromeOptions.addArguments("--user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");

        Configuration.browserCapabilities = chromeOptions;
        open("https://rozetka.com.ua");

        if (!isHeadless) {
            WebDriverRunner.getWebDriver().manage().window().maximize();
        }
    }

    @AfterMethod
    public void closeBrowser(ITestResult result) {
        if (result.getStatus() == ITestResult.SKIP || result.getStatus() == ITestResult.FAILURE) {
            addScreenshotAttachment(result.getMethod().getMethodName());
        }
        Selenide.closeWebDriver();
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] addScreenshotAttachment(String methodName) {
        try {
            File screenshot = Screenshots.takeScreenShotAsFile();
            return FileUtils.readFileToByteArray(Objects.requireNonNull(screenshot));
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }
}


