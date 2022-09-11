Feature: Verify the New is valid

  @mobileNews11
  Scenario: Verify new article with other resources on mobile
    Given user visits "https://www.theguardian.com/tone/news" on mobile
    Then user reads 1st article present in the page on mobile browser
    And validates the same article in other sources in "https://www.google.com" on mobile browser