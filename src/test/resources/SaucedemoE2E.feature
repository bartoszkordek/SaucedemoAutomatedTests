Feature: Saucedemo.com (https://www.saucedemo.com/) Website E2E automated tests

  Background:
    Given Navigate to "https://www.saucedemo.com/"

  @E2E @StandardUser
  Scenario: Log in as a Standard User
    When Log in as a "standard_user"
    Then Login is successful

  @E2E @PerformanceGlitchUser
  Scenario: Log in as a Performance glitch User
    When Log in as a "performance_glitch_user"
    Then Login is successful

  @E2E @LockedOutUser
  Scenario: Log in as a Locked out User
    When Log in as a "locked_out_user"
    Then Login is failed