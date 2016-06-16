Feature: CheckWordster
  For the hipster bank customer
  Who is unhappy with traditional bank check issuing services, because the checks written don’t have that retro feel,
  The CheckWordster is a microservice of a bank check writing service
  That provides the words for the amounts on a check that used to be written by people.
  Unlike ECPS (Enterprise Check Production System),
  Our product gives checks that old time feeling, with amounts in both numbers and words.

  Scenario Outline: Convert numbers into words when all goes well
    Given I am doing "no" server testing of the CheckWordster microservice core logic
    When I convert "<number>" into words
    Then it should be "<words>"

    Examples:
      |number |words                               |
      |22     |Twenty two                          |
      |65     |Sixty five                          |
      |1954   |One thousand nine hundred fifty four|
      |2.25   |Two and 25/100                      |
      |3.1    |Three and 10/100                    |

  Scenario Outline: Convert numbers into words when there are errors
    Given I am doing "no" server testing of the CheckWordster microservice core logic
    When I convert "<number>" into words
    Then an exception "<exception>" should be thrown

    Examples:
      |number          |exception              |
      |                |Null number            |
      |8x20            |Invalid characters     |
      |1,000.00        |Invalid format         |
      |-20             |Signed number          |
      |+2.25           |Signed number          |
      |1000000000000000|Too many digits        |
      |846.217         |Too many decimal places|

