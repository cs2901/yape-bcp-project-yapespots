# Developer Guide

## 1. Introduction

Yape spots is a project that focuses on giving users the capacity to find more businesses that use Yape as a payment method and increasing the customers of these businesses. Yape spots uses a map that shows the location of all the businesses with Yape. Also, it provides the user the capacity to filter these businesses and search for others, making easier to find the location of your preference.

## 2. Features

- Implement data base  
  To implement the data base we are going to use firebase, because it let us save on time and abstracting the data layer to a certain extend, also give to us the opportunity to query for information to the API of Google. Other option considered was create our own API res  offering us more customization however this approach require more time to be realized.

- Move the map feature  
  This feature is  going to be implemented using the SDK of Google Maps because it let us have access to the implementation related to Google Maps, so the functionality of the app is going to be similar to Google Maps app. Other option considered was use mapbox; however, if we choose it we can't have access to all the data of Google Maps.

- Show Yape spots in map feature  
  This feature is going to be implemented using the API of Google Maps in order to query all the places that exist in a particular area, and using the SDK of Google Maps to draw them. Other option considered was use mapbox; however, it was dismissed because the idea mentioned before.

- Report a Yape spots feature  
  This feature is going to be implemented using firebase, because it let use CRUD our data base in an easy way. Other option considered was create our own API res to manage all the data included the reports; however, it was dismissed because the idea mentioned before.

- Directions feature  
  This feature is about send information of a place to external apps of navigation (e.g. Google Maps, Waze, Uber, etc.), it is going to be implemented using implicit intents because it let us doesn't specify the exact app where the directions will go. Other approach considered was use explicit intents, however it implies create specifics button for each app.

- Like Yape spots feature  
  Similar to Report a Yape spots feature, this feature is going to be implemented using firebase, also for the same reason. Other option considered was also create our own API res; however, it was dismissed because the idea mentioned before.

- Center the map feature  
  This feature is going to be implemented using the SDK of Google Maps, because it already has implementation related to that point. Other option was use mapbox; however, it was dismissed because we need access to the database of Google Maps.

- Search feature  
  This feature is going to be implemented using the API of Google Maps, because it give to us the opportunity to query for information, in this case a search by text. Other option considered was create our own API res  offering us more customization however this approach require more time to be realized.

- See closest Yape spots feature  
  Similar to Show Yape spots feature, this feature is going to be implemented using the API of Google Maps in order to query all the places that exist in a particular area, and using the SDK of Google Maps to draw them. Other option considered was use mapbox; however, it was dismissed because it doesn't give to us access to a data base of places.

- Filter Yape spots feature  
  Similar to all features related to query information this feature is going to be implemented using the API of Google Maps. Other proposition considered was implemented our own API res; however, as discussed above it require a lot of time.

- Filter in map feature  
  Similar to the Filte Yape spots feature, this feature is going to be implemented using the API of Google Maps to query the information, and the SDK of Google Maps to draw it. Other option considered was create our own API res and use mapbox; however, it was dismissed because the reason mentioned before.

- Favorite Yape spots feature  
  This feature is going to be implemented using the shared preference interface of Android, because it let us save that info in the cache of the application. Other option considered was add the list of favorite Yape spots to our database; however, it was dismissed because with that approach our database will grow exponentially.

- Not log in Yape Spots feature  
  This feature is going to be implemented creating an additional view and presenter of the map component for the guess user, because this approach isolate the features related to a logged user. Other option considered was adapt the originals views and presenter of the map component; however, it was dismissed because could create security gaps.

## 3. Design

### Architecture

#### Class Diagrams

#### Architecture Diagram

![Architecture diagram](/documentation/developer_guide/Architecture_Diagram/architecture_diagram.png)

#### Components Diagram

#### Interactions Between Components

## 4. FAQ

1. What is the recommended IDE to use to develop the Android app?
* Android Studio, you can download it from [here](https://developer.android.com/studio).

2. How to generate an API key to use Google Maps API?
* Use this [information](https://developers.google.com/maps/documentation/javascript/get-api-key).

3. How the data is going to maintain updated?
*  We are going to maintain the data updated by a concept called crowdsourcing. In simple words, It means that the community is going to maintain the data updated by themselves. Every time you find a Yape spot that does not longer uses Yape as a payment method, you can report it and if one place gets many reports it is going to get removed from the database.

4. How much larger would Yape be with the map implementation?
*  By reviewing other applications with similar functionalities we got that the app would possibly increase from 34Mb to 47Mb.

## 5. Glossary

  - Yape: A free mobile app powered by Banco de Credito del Perú (BCP) developed in 2017, where you can transfer money to another account without know the account number. Also, you can pay in merchants. You can pay through QR (Quick Response) code.

  - Yapear: Transfer money to another account through Yape’s app.

  - Transfer: A transfer is sending money to a personal or business account.

  - Yape spot: It is a business that accepts payments through Yape’s app, they have a unique QR code to identify them.

  - Yape’s User: It is a user that has access to the Yape’s app, they are logged in.

  - Guest: It is a user who has not logged in to the application, but they can use some restricted features (e.g. search look for nearby Yape Spots).

  - QR (Quick Response): It is a bidimensional code that codifies and storage data (e.g. a identifier number, a website address, a plain number, a text, etc).

  - GPS (Global Positioning System): Is a localization position satellite-based that provides latitude, longitude, and altitude.
