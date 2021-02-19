Feature: Pet store actions

#POST/pet
Scenario Outline: Add a new pet to the store
    Given having a new pet defined with id <id> categoryid <categoryid> categoryname <categoryname> name <name> tagid <tagid> tagname <tagname> and status <status>
    When adding the pet to the petstore
    Then expecting the pet to be added in the petstore
    Examples:
    | id | categoryid | categoryname | name  | tagid | tagname | status   |
    | 1  | 1 	      | categoryVido | Vido  | 1     | Vidotag | available|
    | 2  | 2 	      | categoryVida | Vida  | 2     | Vidatag | pending  |
    | 3  | 3 	      | categoryVide | Vide  | 2     | Videtag | sold     |

# #POST/pet/{petId}/uploadImage
# Scenario Outline: Uploads an image
#     Given having an image <image> that i want to upload to the pet with id <id>
#     When the image is uploaded
#     Then the pet has an image in its profile
#     Examples:
#     | id | image |
#     | 1  | URL   | 

# #GET/pet/{petId}
# Scenario:Find pet by id
#     When looking for the pet with id 1
#     Then getting the right information about the pet

# #GET/pet/findByStatus
# Scenario Outline: Finds pets by status
#     When looking for the pet with status <status>
#     Then getting the right information about the pet
#     Examples:
#     | status    |
#     | available |
#     | pending   |
#     | sold      |

# #PUT/pet
# Scenario Outline: Update an existing pet
#     Given a pet with id <id>
#     When updating the name of the pet to name <name> and status <status>
#     Then has the pet an updated profile
#     Examples:
#     | id  | name | status |
#     | 15  | Vidi | sold   |

# #PUT/pet
# Scenario: Update an existing pet with a not existing id
#     Given having a pet with a non existing id 100
#     When updating the name of the pet to name VidaNonExistingId and status StatusNonExisting
#     Then expecting a error message

# #POST/pet/{petId}
# Scenario: Updates a pet in the store with form data


# #DELETE/pet/{petId}
# Scenario Outline: Deletes a pet
#     Given having some pets in the store and i want to delete them
#     When deleting the pet with id <id>
#     Then the pet does not longer exist in the store
#     Examples:
#     | id |
#     | 1  |
#     | 2  |
#     | 3  |