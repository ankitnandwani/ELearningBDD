package com.ui.cucumber;

import com.backend.Constants;
import com.backend.RequestUtils;
import com.backend.entities.request.CreateUserRequest;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by mac on 25/08/17.
 */
public class Hooks {

    RequestUtils requestUtils = new RequestUtils();
    Browser browser = new Browser();

    public void explicitWait(WebDriver driver, int time, WebElement element){
        (new WebDriverWait(driver, time)).until(ExpectedConditions.visibilityOf(element));
    }

    public Response sendRequest(String requestType, String endpoint) {
        switch (requestType){
            case "GET":
                return requestUtils.sendGet(browser.getApiPath(Constants.REQRES, endpoint));
            case "POST":
                return requestUtils.sendPostWithBody(browser.getApiPath(Constants.REQRES, endpoint), browser.getReqresHeaders(), CreateUserRequest.builder().build());
            default:
                throw new AssertionError("Invalid request type.");
        }
    }
}
