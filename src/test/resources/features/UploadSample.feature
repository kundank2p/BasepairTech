Feature: Upload sample(s) with metadata on the project page

  Background:
    Given the user is logged in and on the project page

  @upload
  Scenario: User uploads a single sample with metadata
    When the user clicks the "Upload Sample" section
    And the user selects a file named "sample1.txt" from testdata
    And the user clicks the Upload samples button
    Then the file should be uploaded successfully


  @upload
  Scenario: User uploads multiple samples
    When the user clicks the "Upload Sample" section
    And the user selects files named "sample1.txt,sample2.txt" from testdata
    And the user clicks the Upload samples button
    Then the files should be uploaded successfully

#  @upload @negative
#  Scenario: User tries to upload without selecting a file
#    When the user clicks the "Upload Sample" section
#    And the user clicks the Upload samples button
#    Then a validation error should be displayed
#
#  @upload @negative
#  Scenario: User uploads a file with unsupported format
#    When the user clicks the "Upload Sample" section
#    And the user selects a file named "invalid_file.exe" from testdata
#    And the user clicks the Upload samples button
#    Then an error message should be displayed for unsupported format