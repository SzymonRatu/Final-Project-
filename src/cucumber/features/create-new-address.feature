@shop
Feature: Create new address

  @CreateAddress
  Scenario Outline: User adds address to the account
    Given Logged user with email "nrdxqialralogvutte@bbitq.com" and password "qwerty123"
    When User is on my address page and User add new address
    And User enter new address "<alias>", "<address>", "<city>", "<zip/postal code>", "<country>", "<phone>"
    Then User sees a added address
    And User verify created address "<alias>", "<address>", "<city>", "<zip/postal code>", "<country>", "<phone>"
    And User remove the address
    And User sees the address was removed
    And User close the browser
    Examples:
      | alias    | address   | city       | zip/postal code | country        | phone     |
      | address1 | Street 77 | Montgomery | 20104           | United Kingdom | 123456789 |
      | address2 | Street 22 | LasVegas   | 10205           | United Kingdom | 123321123 |
