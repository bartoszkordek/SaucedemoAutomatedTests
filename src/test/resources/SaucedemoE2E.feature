Feature: Saucedemo.com (https://www.saucedemo.com/) Website E2E automated tests

  Background:
    Given Navigate to "https://www.saucedemo.com/"

  @WebTest @LockedOutUser
  Scenario: Validation 4
    When Log in as a "locked_out_user"
    Then Log in is failed