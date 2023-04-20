@shop
  Feature: Purchasing

    @Purchase
    Scenario: The procedure of purchasing an item
      Given Logged user with email "nrdxqialralogvutte@bbitq.com" and password "qwerty123" to shop
      When User add "Hummingbird printed sweater" with discount "-20%"
      And choose "L" size and choose 1 pieces of sweater
      And add to cart with size "L" and 1 pieces
      And Confirm address with alias "address"
      And Choose a pick up in store
      And Choose a payment option - Pay By Check
      Then Take a print-screen with confirm order
      And In history order check order with status "Awaiting check payment"
      And check total price
      And close browser
