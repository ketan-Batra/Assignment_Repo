Feature: Verify the New is valid

  @news11
  Scenario: Verify new article with other resources
    Given user visits "https://www.theguardian.com/tone/news"
    Then user reads 1st article present in the page
    And validates the same article in other sources in "https://www.google.com"