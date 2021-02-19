Feature: Pet store actions

#POST/pet
#GET/pet/{petId}
Scenario Outline: Add a new pet to the store
    Given having a new pet defined with id <id> name <name> and status <status>
    When adding the pet to the petstore
    Then expecting the pet to be added in the petstore
    Examples:
    | id   | name  | status   |
    | 100  | Vido  | available|
    | 200  | Vida  | pending  |
    | 300  | Vide  | sold     |

#GET/pet/{petId}
Scenario: Find a pet which not exist
    When looking for a pet an id 600001 that does not exist results in a status 404 and message Pet not found

#GET/pet/findByStatus
Scenario Outline: Finds pets by status
    When looking for the pet with status <status> and name <name> i expect it to be in de results
    Examples:
    | status    | name |
    | available | Vido |
    | pending   | Vida |
    | sold      | Vide |

#PUT/pet
Scenario Outline: Update an existing pet
    Given an updated pet with id <id> to name <name> and status <status>
    When updating the pet
    Then has the pet an updated profile
    Examples:
    | id   | name | status |
    | 100  | Vidi | sold   |

# #POST/pet/{petId}
Scenario Outline: Updates a pet with in the store with form data
    Given a pet with id <id> that needs to be updated by form data with name <name> and status <status>
    Examples:
    | id  | name       | status |
    | 200 | DogChanged | sold   |


#DELETE/pet/{petId}
Scenario Outline: Deletes a pet
    When deleting the pet with id <id>
    Then the pet does not longer exist in the store
    Examples:
    | id   |
    | 100  |
    | 200  |
    | 300  |