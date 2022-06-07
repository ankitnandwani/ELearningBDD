package com.ui.cucumber.Repo;

import com.ui.cucumber.Hooks;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ElucidatLearningPage extends BasePage{
    WebDriver driver;
    Hooks hooks = new Hooks();

    public ElucidatLearningPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[text()='START']")
    private WebElement startBtn;

    @FindBy(xpath = "//div[@id='pa_5c9126fe3f4fb_p1552ed09ccb-text']/p")
    private WebElement scoreTxt;

    @FindBy(xpath = "//div[@id='paw']/div/div/p")
    private WebElement caseDescTxt;

    @FindBy(xpath = "//span[text()='JUDGE THIS']")
    private WebElement judgeThisBtn;

    @FindBy(xpath = "//i[@class='ti ti-answer ti-radio']")
    private WebElement guiltyRadioBtn;

    @FindBy(xpath = "//span[text()='VOTE']")
    private WebElement voteBtn;

    @FindBy(xpath = "(//div[@class='col__inner']/h2)[2]")
    private WebElement judgementHeadingTxt;

    @FindBy(xpath = "(//span[text()='CONTINUE'])[2]")
    private WebElement continueBtn;

    public void clickStartBtn(){
        clickBtn(startBtn);
    }

    public String getScore(){
        return scoreTxt.getText().split(":")[1];
    }

    public void clickCase(String name){
        driver.findElement(By.xpath("//span[text()='"+name+"']")).click();
    }

    public String getCaseDesc(){
        waitForJudgeThisBtn();
        return caseDescTxt.getText();
    }

    public void waitForJudgeThisBtn(){
        waitForBtnVisibility(judgeThisBtn);
    }

    private void waitForBtnVisibility(WebElement element) {
        hooks.explicitWait(driver, 10, element);
    }

    public void clickJudgeThisBtn() {
        clickBtn(judgeThisBtn);
    }

    public void clickGuiltyBtn() {
        clickBtn(guiltyRadioBtn);
    }

    public void clickVoteBtn() {
        clickBtn(voteBtn);
    }

    public String getJudgementHeading(){
        waitForContinueBtn();
        return judgementHeadingTxt.getText();
    }

    public void waitForContinueBtn(){
        waitForBtnVisibility(continueBtn);
    }
}
