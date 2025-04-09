Feature: Navigate to Project from Dashboard

  Background:
    Given the user is logged in and on the dashboard page

    @positive
  Scenario: User navigates to project page from dashboard
    When the user clicks on the project titled "Test Project"
    Then the user should be navigated to the project page
