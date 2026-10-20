package listeners;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener {

    public static Logger logger = Logger.getLogger(Listener.class);

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("====================================================");
        logger.info(result.getMethod().getMethodName() + " -Start");
        logger.info("====================================================");
        logger.info(result.getMethod().getDescription());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.info("====================================================");
        logger.info(result.getMethod().getMethodName() + " -Failed");
        logger.info("====================================================");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("====================================================");
        logger.info(result.getMethod().getMethodName() + " -Finish");
        logger.info("====================================================");
    }

    @Override
    public void onStart(ITestContext context) {
        logger.info("====================================================");
        logger.info("On start " + context.getName() + " ==========================");
        logger.info("====================================================");
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("====================================================");
        logger.info("On Finish " + context.getName() + " =========================");
        logger.info("====================================================");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }
}