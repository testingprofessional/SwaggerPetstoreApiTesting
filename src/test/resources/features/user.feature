Feature: Operations about user

#POST/user
#GET/user/{username}
Scenario Outline: Create user
    Given an user with name <name>
    When creating the user
    Then the new user is stored in the petstore
    Examples:
    | name     |
    | testname |

#PUT/user/{username}
Scenario Outline: Updated user
    When updating the user <user> with phonenumber <phonenumber> 
    Then the user is updated with the new phonenumber
    Examples:
    | user     | phonenumber |
    | testname | 0698765432  | 

#DELETE/user/{username}
Scenario: Delete user
    When deleting the user testname
    Then the user is not longer in the store

#TODO
#POST/user/createWithArray
#Scenario: Create list of users with given input array

#POST/user/createWithList
#Scenario: Creates list of users with given input array

#GET/user/login
#Scenario: Logs user into the system

#GET/user/logout
#Scenario: Logs out current logged in user session