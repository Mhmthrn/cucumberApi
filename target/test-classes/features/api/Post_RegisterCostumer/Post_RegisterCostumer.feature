
  Feature: Used to create a new user

    Scenario: Success Response

      Given Api kullanicisi "api/register" path parametrelerini set eder
      Then Register Costumer icin gerekli Request Body "Ayse","Hanim","ertyundgy@gmail.com","ertyundgy@gmail.com","1234.abc","1234.abc","customer","1455","44546545464546" hazirla
      Then Register Costumer icin Post request gonder