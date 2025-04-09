Feature: Upload sample(s) with metadata on the project page

  Background:
    Given the user is logged in and on the project page

  @upload
  Scenario: User uploads a single sample with metadata
    When the user clicks the "Upload Sample" section
    And the user clicks the upload icon
    And the user selects a file named "sample1.txt" from testdata
    And the user fills in the metadata
      | Platform | Illumina             |
      | DataType | RNA-seq              |
      | Stranded | None / Don't know    |
      | SpikeIn  | None                 |
      | Genome   | GRCh38               |
      | Pipeline |                      |
    And the user clicks the Upload samples button
    Then the file should be uploaded successfully

  @multipleUpload
  Scenario: User uploads multiple samples with metadata
    When the user clicks the "Upload Sample" section
    And the user clicks the upload icon
    And the user selects multiple files from testdata "sample1.txt,sample2.txt"
    And the user fills in the metadata
      | Platform | Illumina             |
      | DataType | RNA-seq              |
      | Stranded | None / Don't know    |
      | SpikeIn  | None                 |
      | Genome   | GRCh38               |
      | Pipeline |                      |
    And the user clicks the Upload samples button
    Then all selected files should be uploaded successfully
