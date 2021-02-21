Feature: Access to Petstore orders

#POST/store/order
#GET/store/order/{orderId}
Scenario: Place an order for a pet
    Given an order with orderid 5 for pet with id 100 and quantity 1
    When placing the order
    Then the order is placed in the petstore

#DELETE/store/order/{orderId}
Scenario: Delete purchase order by ID
    When deleting a purchase order with id 5
    Then the order with id 5 is not longer in the petstore

#DELETE/store/order/{orderId}
Scenario: Delete purchase order by not existing ID
    When deleting a purchase order with a non existing id 5 the message Order Not Found is expected

#GET/store/inventory
Scenario: Returns pet inventories by status
    Given 3 pets in the petstore with status available pending and sold
    When asking the inventory of the petstore i expect a map with quantities
     


