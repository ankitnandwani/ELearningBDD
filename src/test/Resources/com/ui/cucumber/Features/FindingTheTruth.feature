Feature: FINDING THE TRUTH

  Background: Go to homepage
    Given I'm at elucidat_learning homepage

  @ui
  Scenario: Verify Current Score
    When I fetch current score
    Then it should have a valid integer value

  @ui
  Scenario: Verify correct case is opened
    Given I click on Who is to blame? case
    When I fetch case description
    Then it should show A young man has been in an accident in the warehouse where he worked and has subsequently died. description

  @ui
  Scenario: Verify correct vote is registered
    Given I click on Making a case against Kevin case
    When I vote Kevin as Guilty
    Then popup should show GUILTY! in the popup header