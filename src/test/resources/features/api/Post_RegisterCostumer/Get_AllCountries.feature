@API
  Feature: This folder is using Bearer Token from collectiontlb_e-commerce_project
    Scenario: Success Response
      Given Api kullanicisi "api/profile/allCountries" path parametrelerini set eder
      Then AllCountries icin Get request gonderilir