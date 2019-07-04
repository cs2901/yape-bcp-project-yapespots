# Developer Guide

## 1. Introduction

Yape spots is a project that focuses on giving users the capacity to find more businesses that use Yape as a payment method and increasing the customers of these businesses. Yape spots uses a map that shows the location of all the businesses with Yape. Also, it provides the user the capacity to filter these businesses and search for others, making easier to find the location of your preference.  

**Value proposal:** Make businesses that use Yape as a payment method visible to Yape users.

## 2. Features

- Implement data base  Jorge Rios/Jonathan Loza

  To implement the data base we are going to use firebase, because it let us save on time and abstracting the data layer to a certain extend, also give to us the opportunity to query for information to the API of Google. Other option considered was create our own API res  offering us more customization however this approach require more time to be realized.

- Move the map feature  Jorge Mayna

  This feature has been implemented using the SDK of Google Maps because it let us have access to the implementation related to Google Maps, so the functionality of the app is going to be similar to Google Maps app. Other option considered was use mapbox; however, if we choose it we can't have access to all the data of Google Maps.

- Show Yape spots in map feature  Jorge Mayna/Jonathan Loza/Jorge Rios

  This feature is going to be implemented using the API of Google Maps in order to query all the places that exist in a particular area, and using the SDK of Google Maps to draw them. Other option considered was use mapbox; however, it was dismissed because the idea mentioned before.

- Report a Yape spots feature  Jorge Rios

  This feature has been implemented using firebase, because it let use CRUD our data base in an easy way. Other option considered was create our own API res to manage all the data included the reports; however, it was dismissed because the idea mentioned before.

- Directions feature - Dylan Castro/Ian Arias Schreiber

  This feature is about send information of a place to external apps of navigation (e.g. Google Maps, Waze, Uber, etc.), it is going to be implemented using implicit intents because it let us doesn't specify the exact app where the directions will go. Other approach considered was use explicit intents, however it implies create specifics button for each app.

- Like Yape spots feature  Jorge Rios

  Similar to Report a Yape spots feature, this feature is going to be implemented using firebase, also for the same reason. Other option considered was also create our own API res; however, it was dismissed because the idea mentioned before.

- Center the map feature  Jorge Mayna

  This feature is going to be implemented using the SDK of Google Maps, because it already has implementation related to that point. Other option was use mapbox; however, it was dismissed because we need access to the database of Google Maps.

- Search feature  - Dylan Castro/Jonathan Loza

This feature has been implemented using the API of Google Maps, because it give to us the opportunity to query for information, in this case a search by text. Other option considered was create our own API res  offering us more customization however this approach require more time to be realized.

- See closest Yape spots feature  Jorge Rios

  Similar to Show Yape spots feature, this feature is going to be implemented using the API of Google Maps in order to query all the places that exist in a particular area, and using the SDK of Google Maps to draw them. Other option considered was use mapbox; however, it was dismissed because it doesn't give to us access to a data base of places.

- Filter Yape spots feature - Dylan Castro/Ian Arias Schreiber/Jonathan Loza

  Similar to all features related to query information this feature has been implemented using the API of Google Maps. Other proposition considered was implemented our own API res; however, as discussed above it require a lot of time.

- Filter in map feature - Jonathan Loza

  Similar to the Filte Yape spots feature, this feature has been implemented using the API of Google Maps to query the information, and the SDK of Google Maps to draw it. Other option considered was create our own API res and use mapbox; however, it was dismissed because the reason mentioned before.

- Favorite Yape spots feature  Jonathan Loza/Jorge Rios

  This feature has been implemented using the shared preference interface of Android, because it let us save that info in the cache of the application. Other option considered was add the list of favorite Yape spots to our database; however, it was dismissed because with that approach our database will grow exponentially.

- Not log in Yape Spots feature - Jorge Mayna

  This feature has been implemented creating an additional view and presenter of the map component for the guess user, because this approach isolate the features related to a logged user. Other option considered was adapt the originals views and presenter of the map component; however, it was dismissed because could create security gaps.

- Call Yape Spots feature - Dylan Castro/Ian Arias Schreiber

  This feature has been implemented by retrieving the YapeSpot's phone number from the Google API using its id to do a query. Then, we use the call indent to switch the screen to the call screen and have the phone number already input.

- Share Yape Spots feature - Ian Arias Schreiber

  This feature has been implemented by using the share intent and sending a custom message to an application of choice. In the custom message, the Yape Spot's google map URL is displayed.

- Yape Spot's details - Dylan Castro/Carlos Guerrero/Jonathan Loza

  This feature has been implemented by doing a query to Google API and retrieving the Yape Spot's details (status, photos, name, coordinates, phone number and category).

- Geolocalization feature  -Jorge Mayna

This feature has been implemented by requesting the geolocalization permission from the user and getting the gps coordinates from the user’s cellphone.

- Make requests policies  -Jorge Mayna / Carlos Guerrero

This feature has been implemented by finding a formula that makes sure to make a request just when it's needed, saving resources from the system. we make each request by listening the movement and zoom of the camera.

- Login feature  -Jorge Mayna

This feature has been implemented by creating buttons for the numbers and saving in a string each number preseed and comparing the resulting string with the password.


## 3. Design

### Architecture

#### Class Diagram

Because of the size of the diagram we divided it into components and we draw the class diagram of each one.

#### Architecture Diagram

![Architecture diagram](/documentation/developer_guide/Architecture_Diagram/architecture_diagram.png)

#### Components List

* **Database Interactor component**  
  **i. Description of component**  
  The data base component is the responsable for receive queries and respond them with data obtained from the different sources Google API, Local or firebase.
  It are going to be used by other components.  
  **ii. Class diagram of component**  
  ![Database_Interactor_Component](/documentation/developer_guide/Class_Diagrams/Database_Interactor_Component.png)

* **Login component**  
  **i. Description of component**  
  This component is the responsible of showing the login screen and allow you to access as an existing or guest user.  
  **ii. Class diagram of component**  
  ![Login Component](/documentation/developer_guide/Class_Diagrams/Login_Component.png)

* **Filters component**  
  **i. Description of component**  
  This component is the responsable for made the filters of the information that are going to be displayed to the user in the map, that mean this component need to made the queries to the place component, who have to made it to Database Interactor component.  
  **ii. Class diagram of component**  
  ![Filters_Component](/documentation/developer_guide/Class_Diagrams/Filters_Component.png)

* **Advanced Filters component**  
  **i. Description of component**  
  This component is the responsable for made the filters of the information that are going to be displayed on a list to the user, in order to do that it are going to extends some of the classes of the Filters Component. due to the advanced filters component need to return information of all the Yape Spots and not only of the nearest in to the user, this component needs to connect directly with the Database Interactor Component.  
  **ii. Class diagram of component**  
  ![Advanced_Filters_Component](/documentation/developer_guide/Class_Diagrams/Advanced_Filters_Component.png)

* **Home component**  
  **i. Description of component**  
  This component is the responsable of emulate the view of the base Yape app and the new button that let the user access to the Yape Spot extension.  
  **ii. Class diagram of component**  
  ![Home_Component](/documentation/developer_guide/Class_Diagrams/Home_Component.png)

* **Map component**  
  **i. Description of component**  
  This component is the responsable of draw and show the map to the user, in a certain sense this is the main component of our proposal.  
  It also connect with the Location class of Google Maps because we are going to use the SDK of Google in order to take a similar interface as Google Maps.  
  **ii. Class diagram of component**  
  ![Map_Component](/documentation/developer_guide/Class_Diagrams/Map_Component.png)

* **Search component**  
  **i. Description of component**  
  This component is the responsable of made queries related to Name of Yape Spots to the Database Interactor component and shows the result to the user. Also the user can apply filters to specify the search, so this component connect with the Advanced Filters component.  
  **ii. Class diagram of component**  
  ![Search_Component](/documentation/developer_guide/Class_Diagrams/Search_Component.png)

* **Model component**  
  **i. Description of component**  
  This component is the container component of the individual class we are going to use in the project. Such as Place, User, LatLng (localization system of Google) and Login.  
  **ii. Class diagram of component**  
  ![Search_Component](/documentation/developer_guide/Class_Diagrams/Model_Component.png)

#### Interactions Between Components

![Add to favorites](/documentation/developer_guide/Components_interaction/AddFavorite.png)


![Filter By Type In Map](/documentation/developer_guide/Components_interaction/FilterByTypeInMap.png)


![Search using search bar](/documentation/developer_guide/Components_interaction/SearchBar.png)

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
