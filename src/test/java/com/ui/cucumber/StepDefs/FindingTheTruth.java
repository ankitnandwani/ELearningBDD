package com.ui.cucumber.StepDefs;

import com.ui.cucumber.Browser;
import com.ui.cucumber.Repo.ElucidatLearningPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.Properties;

public class FindingTheTruth {

    private final Browser browser;
    private String score;
    String caseDesc;
    private ElucidatLearningPage elucidatLearningPage;

    public FindingTheTruth(Browser browser){
        this.browser = browser;
    }

    @Given("^I'm at (.+) homepage$")
    public void iMAtHomepage(String page) {
        WebDriver driver = browser.getDriver();
        Properties prop = browser.getProp();
        elucidatLearningPage = browser.getElucidatLearningPage();
        driver.get(prop.getProperty(page));
        elucidatLearningPage.clickStartBtn();
    }

    @When("I fetch current score")
    public void iFetchCurrentScore() {
        score = elucidatLearningPage.getScore();
    }


    @Then("it should have a valid integer value")
    public void itShouldHaveAValidIntegerValue() {
        try {
            Integer.parseInt(score);
        }catch (NumberFormatException e){
            throw new AssertionError("Invalid Score found.");
        }
    }

    @Given("^I click on (.+) case$")
    public void iClickOnCase(String name) {
        elucidatLearningPage.clickCase(name);
    }

    @When("I fetch case description")
    public void iFetchCaseDescription() {
        caseDesc = elucidatLearningPage.getCaseDesc();
    }

    @Then("^it should show (.+) description$")
    public void itShouldShowDescription(String expectedDesc) {
        Assert.assertEquals(caseDesc, expectedDesc);
    }

    @When("I vote Kevin as Guilty")
    public void iVoteKevinAsGuilty() {
        elucidatLearningPage.clickJudgeThisBtn();
        elucidatLearningPage.clickGuiltyBtn();
        elucidatLearningPage.clickVoteBtn();
    }

    @Then("^popup should show (.+) in the popup header$")
    public void popupShouldShowGUILTYInThePopupHeader(String expectedTxt) {
        String judgementText = elucidatLearningPage.getJudgementHeading();
        Assert.assertEquals(judgementText, expectedTxt);
    }
}
