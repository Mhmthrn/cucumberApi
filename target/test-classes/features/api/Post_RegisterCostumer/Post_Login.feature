
Feature: You can log in to the system with your email and password

  Scenario: Success Response

    Given Api kullanicisi "api/login" path parametrelerini set eder
    Then Login icin "email" ve "password" hazirla
    Then Login icin Post request gonderilir