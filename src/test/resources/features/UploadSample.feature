Feature: Upload sample(s) with metadata on the project page

  Background:
    Given the user is logged in and on the project page

  @upload
  Scenario: User uploads a single sample with metadata
    When the user clicks the "Upload Sample" section
    And the user selects a file named "sample1.txt" from testdata
    And the user clicks the Upload samples button
    Then the file should be uploaded successfully


