# User Guide

## 1. Introduction

Yape spots is a project that focuses on giving users the capacity to find more businesses that use Yape as a payment method and increasing the customers of these businesses. Yape spots uses a map that shows the location of all the businesses with Yape. Also, it provides the user the capacity to filter these businesses and search for others, making easier to find the location of your preference.  

**Value proposal:** Make businesses that use Yape as a payment method visible to Yape users.

## 2. Requirements

A. Functional

-  The system will show a map using the Google maps API

- The system will allow the user to move around the map in the app, zoom in and zoom out

- The system will show the closest Yape spots on the map

- The system will show Yape spots on the map with a pin

- The system will present a button to report a Yape spot if it isn't using Yape so that it can be removed from the map

- The system will show some basic information to the user if he select a Yape spot. If the user may want more information he can click the Google map button to see more information

- The system will show a icon to enter the map in the log in screen of Yape. If the user enters by this way to the map, he can only see Yape spots on the map, features related with user personal information will be unavailable until he enters to his account

- The system will present a search screen to look for specific Yape spots

- The system will allow the user to filter by type of place, if its open or not, and distance.


B. Non-Functional


-  The access to the map feature should be easy to find in the UI of Yape

-  The system should work for smartphones with Android 4.4 or later

-  The system, in order to respect the privacy of the user, should ask for permission to activate the GPS

-  The system should not overcome 21MB of user memory space

-  The features should be written in java programming language

-  The system should use the API of google maps to recreate the map

## 3. Features

- Implement data base

- Move the map feature

- Show Yape spots in map feature

- Report a Yape spots feature

- Directions feature

- Like Yape spots feature

- Center the map feature

- Search feature

- See closest Yape spots feature

- Filter Yape spots feature

- Filter in map feature

- Favorite Yape spots feature

- Not log in Yape Spots feature

- Call Yape Spots feature

- Share Yape Spot feature

- Yape Spot's details feature

- Geolocalization feature

- Make request policies feature

- Login feature

## 4. FAQ

1. What is going to show me the map of Yape Spots?
  -  The map will show you business that use Yape as payment method

2. Should I be logged in to see the map?
  - No, if you are a user you can access without logging in.  

3. What types of filters can I apply in Yape Spots?
 - Youc can filter by category (restaurant, bar, market, etc), by distance and by if its open or not

4. What happend if a Yape spot does not use Yape?
 - If a Yape Spot doesn't use Yape, you can report it. If a Yape spot is reported many times it will be removed from the database

5. How can I report a Yape spot?
 - You just need to search for the Yape spot, click on its information and the button to report will be there.

6. How many Yape spots exist today?
 - There are at least 5000 Yape spots and this quantity is increasing everyday

7. What is the minimum required version of Android?
 - You need at least the version 4.4 of Android to run the map of Yape spot. However, this is the minimum required version for Yape, so if you can run Yape, you can use Yape spots

8. How complicated is it to use Yape Spots?
 - Yape spots is very easy to use because it uses a friendly interface similar to important applications such as Google Maps, Waze, etc.

## 5. Glossary

  - Yape: A free mobile app powered by Banco de Credito del Perú (BCP) developed in 2017, where you can transfer money to another account without know the account number. Also, you can pay in merchants. You can pay through QR (Quick Response) code.

  - Yapear: Transfer money to another account through Yape’s app.

  - Transfer: A transfer is sending money to a personal or business account.

  - Yape spot: It is a business that accepts payments through Yape’s app, they have a unique QR code to identify them.

  - Yape’s User: It is a user that has access to the Yape’s app, they are logged in.

  - Guest: It is a user who has not logged in to the application, but they can use some restricted features (e.g. search look for nearby Yape Spots).

  - QR (Quick Response): It is a bidimensional code that codifies and storage data (e.g. a identifier number, a website address, a plain number, a text, etc).

  - GPS (Global Positioning System): Is a localization position satellite-based that provides latitude, longitude, and altitude.


## 6. Anexo A
###  6.1 User Stories
##### 6.1.1 Must Have

- As a user, I can move the map so that I can explore new places

- As a user, I can center the map on my position

- As a user, I can see the closest Yape Spot  

- As a user, I can see Yape Spots on map so that, I can easily locate the Yape spots of my interest.

- As a user, I can report a place so that It is no longer available in the map

- As a user, I can see information of a Yape spot

- As a user, I can enter to the Yape map without log in so that I can see Yape spots

- As a suser, I can zoom in and zoom out so that I can filter by distance

- As a user, I can search for Yape spots so that I can find the place of my preference.

- As a user, I can filter types of spots so that I can find the spot of my preference.
 - Conditions:
   - Places share their info in google maps.
   - It shows on the map after being altered

##### 6.1.1 Nice to Have

- As a user, I can see if a Yape spot is open or close

- As a user, I can call a Yape spot

- As a user, I can search faster by looking at my recent searches

- As a user, I can show a list of my favorite spots so that I can see them quickly.

- As a user, I can delete a Yape spot from my favourite list so that I can keep my list of favorites updated.

- As user, I can connect to apps like Waze so that I know how to get there.

- As a user, I can filter the Yape spots directly from the map so that I can remove what I am not interested in seeing.

- As a user, I can mark my favorites Yape spots so that I can remember my best places.

- As a user, I can like a Yape spot so that other people can see it.

- As user, I can share Yape spots with my contacts so that I can set up meetings.
  - Conditions:
    - Share on social media
    - Open Yape spot's shared link on Yape, Uber, Maps, etc.

##### 6.1.1 Out of Scope

- As a user, I can offer deals to gain clients

-  As a user, I can filter friends reviews so that I can see trusted reviews.

- As a user, I can recommend Yape spots so that other users feel safer to go to a new place

- As a user, I can post my spot on the map so that, I can increase my visibility.

- As a user, I can customize the Yape spot pin

- As a user, I can write a review of a Yape spot so that other user can see it

- As a user, I can filter spots by price range so that I can accommodate to my economy.

- As a user, I can look for similar Yape spots

###  6.2 User Cases

### Prose version

#### **UC01** - Filter Restaurant
##### **System:** YapeSpots
##### **User:** Yape User
##### **MSS**
1. User opens Yape app.
2. User logs in
3. User opens YapeSpots
4. Yape loads map at random location
5. User presses “Center GPS” button
6. Yape requests to enable your GPS service.
7. User enables GPS Service.
8. Yape centers map at current position.
9. User presses search button.
10. Yape shows search/filter screen.
11. User presses Restaurant button.
12. Yape displays a list with all near restaurants.

###### End User Case UC01
###### Extensions:
7. 1 User does not enable GPS service.

  7.1.1 Yape displays random location.
Resume at step 9

#### **UC02** - “Guest” user search for a YapeSpot
##### **System:** YapeSpots
##### **User:** Yape User
##### **MSS:**
1. User opens Yape app.
2. User opens YapeSpots
3. Yape loads map at random location
4. User presses search button.
5. Yape shows search/filter screen.
6. User presses search bar.
7. User inputs restaurant’s keyword.
8. Yape displays a list with all restaurants with the keyword.

###### End User Case UC02

#### **UC03** - Like a YapeSpot
##### **System:** YapeSpots
##### **User:** Yape User
##### **MSS:**
1. User opens Yape app.
2. User logs in
3. User opens YapeSpots
4. Yape loads map at random location
5. User presses “Center GPS” button
6. Yape requests to enable your GPS service.
7. User enables GPS Service.
8. Yape centers map at current position.
9.  User selects a YapeSpot.
10. Yape displays YapeSpot information.
11. User presses like button.


###### End User Case UC03
###### Extensions:
7. 1 User does not enable GPS service.

 7.1. 1 Yape displays random location.
Resume at step 9

#### **UC04** - Locate near hotels
##### **System:** YapeSpots
##### **User:** Yape User
##### **MSS:**
1. User opens Yape app.
2. User logs in
3. User opens YapeSpots
4. Yape loads map at random location
5. User presses “Center GPS” button
6. Yape requests to enable your GPS service.
7. User enables GPS Service.
8. Yape centers map at current position.
9. User opens filters.
10. User presses hotel filter button.
11. Yape displays only hotels on the map.

###### End User Case UC04
###### Extensions:

  7. 1 User does not enable GPS service.

    7.1.1 Yape displays random location.
Resume at step 9

#### **UC05** - Add a YapeSpot to favorite
##### **System:** YapeSpots
##### **User:** Yape User
##### **MSS:**
1. User opens Yape app.
2. User logs in
3. User opens YapeSpots
4. Yape loads map at random location
5. User presses “Center GPS” button
6. Yape requests to enable your GPS service.
7. User enables GPS Service.
8. Yape centers map at current position.
9.  User selects a YapeSpot.
10. Yape displays YapeSpot information.
11. User presses favorite button.


###### End User Case UC05
###### Extensions:
7. 1 User does not enable GPS service.

 7.1. 1 Yape displays random location.
Resume at step 9

### UML version

#### Filter on map use case
![Filter_On_Map_Use_Case](use_cases/Filter_On_Map_Use_Case.png)

#### Search use case
![Search_Use_Case](use_cases/Search_Use_Case.png)
