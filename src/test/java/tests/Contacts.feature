#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
Feature: Contacts Feature
  Test all components for interacting with Contacts
  
  Background:
    * url 'http://localhost:8080/demo/contacts'
    
  Scenario: Create Contact
    * json contact = read('CreateContact_Valid.json')
    
    Given request contact
    When method post
    Then status 200
    And match response ==
    """
		{
		  id: 1,
		  name: {
		    first: "Harold",
		    middle: "Francis",
		    last: "Gilkey"
		  },
		  address: {
		    street: "8360 High Autumn Row",
		    city: "Cannon",
		    state: "Delaware",
		    zip: "19797"
		  },
		  phone: [
		    {
		      number: "302-611-9148",
		      type: "home"
		    },
		    {
		      number: "302-532-9427",
		      type: "mobile"
		    }
		  ],
		  email: "harold.gilkey@yahoo.com"
		}    
    """
        
  Scenario: Invalid Create - Name Missing
    * json contact = read('NameMissing.json')
    
    Given request contact
    When method post
    Then status 409
    And match response == 'Does not meet name requirements'
    
  Scenario: Invalid Create - incomplete name
    * json contact = read('InvalidName.json')
    
    Given request contact
    When method post
    Then status 409
    And match response == 'Does not meet name requirements'            

  Scenario: Invalid Create - Address Missing
    * json contact = read('AddressMissing.json')
    
    Given request contact
    When method post
    Then status 409
    And match response == 'Does not meet address requirements'
      
  Scenario: Invalid Create - incomplete address
    * json contact = read('InvalidAddress.json')
    
    Given request contact
    When method post
    Then status 409
    And match response == 'Does not meet address requirements'
    
    
  Scenario: Invalid Create - invalid email
    * json contact = read('InvalidEmail.json')

    Given request contact
    When method post
    Then status 409
    And match response == 'Email provided is invalid'

  Scenario: Create - Email Missing
    * json contact = read('EmailMissing.json')

    Given request contact
    When method post
    Then status 200

  Scenario: Invalid Create - invalid phone
    * json contact = read('InvalidPhone.json')

    Given request contact
    When method post
    Then status 409
    And match response == 'Phone Number provided is invalid.'

  Scenario: Invalid Create - invalid phone type
    * json contact = read('InvalidPhoneType.json')

    Given request contact
    When method post
    Then status 400
        
  Scenario: Create - Phone Missing
    * json contact = read('PhoneMissing.json')

    Given request contact
    When method post
    Then status 409
    And match response == 'At least one phone number must be included with phone listing.'
  
  Scenario: Invalid Create - pre-existing contact
    * json contact = read('CreateContact_Valid.json')
    * set contact.name.first = 'Marsha'
    * set contact.name.last = 'Landing'
    * set contact.email = 'marsha.landing@yahoo.com'
    
    Given request contact
    When method post
    Then status 200
    And def newContact = response
    And print newContact
    
    Given request newContact
    When method post
    Then status 409
    And match response == 'Cannot create new contact with existing contact information.'
    
  Scenario: Update Contact
    * json contact = read('ContactTemplate.json')
    * set contact.name.first = 'John'
    * set contact.name.last = 'Carpenter'
    * set contact.email = 'john.carpenter@gmail.com'
    
    Given request contact
    When method post
    Then status 200
    And def newContact = response
    And set newContact.address.street = 'Downey Street'
    And set newContact.address.zipcode = '90210'
    And set newContact.address.state = 'California'
    And set newContact.name.middle = 'Loring'
    And set newContact.phone[0].number = '(757)201-3267'
    
    Given path '/', newContact.id
    And request newContact
    When method put
    Then status 200
    
  Scenario: Invalid Update - incomplete address
    * json contact = read('ContactTemplate.json')
    * set contact.name.first = 'Joshua'
    * set contact.name.last = 'Balling'
    * set contact.email = 'josh.balling@gmail.com'
  
    Given request contact
    When method post
    Then status 200
    And def newContact = response
    And set newContact.address.street = ''
    And set newContact.address.zipcode = ''
    And set newContact.address.state = ''

    Given path '/', newContact.id
    And request newContact
    When method put
    Then status 409
    
  Scenario: Invalid Update - incomplete name
    * json contact = read('ContactTemplate.json')
    * set contact.name.first = 'Janet'
    * set contact.name.last = 'Sardine'
    * set contact.email = 'janet.sardine@gmail.com'
    
    Given request contact
    When method post
    Then status 200
    And def newContact = response
    And remove newContact.name.first
    And remove newContact.name.last

    Given path '/', newContact.id
    And request newContact
    When method put
    Then status 409
  
  Scenario: Invalid Update - invalid email
    * json contact = read('ContactTemplate.json')
    * set contact.name.first = 'Matilda'
    * set contact.name.last = 'Waltz'
    * set contact.email = 'waltzing.matilda@gmail.com'
    
    Given request contact
    When method post
    Then status 200
    And def newContact = response
    And set newContact.email = 'invalid'

    Given path '/', newContact.id
    And request newContact
    When method put
    Then status 409  
    
  Scenario: Invalid Update - invalid phone number
    * json contact = read('ContactTemplate.json')
    * set contact.name.first = 'Falcone'
    * set contact.name.last = 'Maronie'
    * set contact.email = 'falcone.maronie@gmail.com'
    
    Given request contact
    When method post
    Then status 200
    And def newContact = response
    And set newContact.phone[0].number = '9878'
  
    Given path '/', newContact.id
    And request newContact
    When method put
    Then status 409 
      
  Scenario: Invalid Update - invalid phone type

    * json contact = read('ContactTemplate.json')
    * set contact.name.first = 'Gerald'
    * set contact.name.last = 'Fitzcott'
    * set contact.email = 'geralid.fitz@gmail.com'
    
    Given request contact
    When method post
    Then status 200
    And def newContact = response
    And set newContact.phone[0].type = 'invalid'
  
    Given path '/', newContact.id
    And request newContact
    When method put
    Then status 400 
    
  Scenario: Invalid Update - not-pre-existing contact
    * json contact = read('ContactTemplate.json')
    * set contact.name.first = 'Marcus'
    * set contact.name.last = 'Aurelias'
    
    Given path '/21'
    And request contact
    When method put
    Then status 409
    
  Scenario: Delete Contact
    * json contact = read('ContactTemplate.json')
    * set contact.name.first = 'John'
    * set contact.name.last = 'Barton'
    * set contact.email = 'john.barton@gmail.com'
    
    Given request contact
    When method post
    Then status 200
    And def newContact = response
    
    # perform deletion
    Given path '/', newContact.id
    When method delete
    Then status 200
    
    #confirm deletion
    Given path '/', newContact.id
    When method get
    Then status 404
    
  Scenario: Invalid Delete - non-existent contact
    # perform deletion
    Given path '/123456'
    When method delete
    Then status 404
       
  Scenario: Find Contact
    * json contact = read('ContactTemplate.json')
    * set contact.name.first = 'John'
    * set contact.name.last = 'Barton'
    * set contact.email = 'john.barton@gmail.com'
    
    Given request contact
    When method post
    Then status 200
    And def newContact = response
    
    #confirm deletion
    Given path '/', newContact.id
    When method get
    Then status 200
    And match response.name.last == 'Barton'
    And match response.name.first == 'John'
      
  Scenario: Invalid Find - non-existant contact
    Given path '/123456'
    When method get
    Then status 404
    
  Scenario: List All Contacts
    # create several contacts
    * json iron = read('ContactTemplate.json')
    * set iron.name.first = 'Tony'
    * set iron.name.last = 'Stark'
    * set iron.email = 'ironman@avengers.com'
    * json cap = read('ContactTemplate.json')
    * set cap.name.first = 'Steve'
    * set cap.name.last = 'Rogers'
    * set cap.email = 'captain.america@avengers.com'
    * json spider = read('ContactTemplate.json')
    * set spider.name.first = 'Peter'
    * set spider.name.last = 'Parker'
    * set spider.email = 'spider.man@avengers.com'
        
    Given request iron
    When method post
    Then status 200    

    Given request cap
    When method post
    Then status 200     
    
    Given request spider
    When method post
    Then status 200 
    
    #check for larger sizes than 3 as other scenarios may have run
    Given path ''
    When method get
    Then status 200
    And assert response.length >= 3