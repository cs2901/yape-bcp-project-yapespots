# Use Cases:
## **UC01** - Filter Restaurant & Bar
### **System:** YapeSpots
### **User:** Yape User
### **MSS:**
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
12. Yape displays all near restaurants.
13. User presses Bar button.
14. Yape displays all near restaurants and bars.
#### End User Case UC01
#### Extensions:
7. 1 User does not enable GPS service.
7.a.1 Yape displays random location.
Resume at step 9

## **UC02** - “Guest” user search for a YapeSpot
### **System:** YapeSpots
### **User:** Yape User
### **MSS:**
1. User opens Yape app.
2. User opens YapeSpots
3. Yape loads map at random location
4. User presses search button.
5. Yape shows search/filter screen.
6. User presses search bar.
7. User inputs restaurant’s keyword.
8. Yape displays all restaurants with the keyword.

#### End User Case UC02

## **UC03** - Recommend YapeSpot
### **System:** YapeSpots
### **User:** Yape User
##### **Preconditions:** User must have consumed something on the YapeSpot and have enough balance to pay it.
##### **MSS:**
1. User opens Yape app.
2. User logs in.
3. User presses on QR scanner.
4. Yape scans the YapeSpot’s QR code.
5. User inputs the amount of money to pay.
6. Yape transfers the money to the YapeSpots.
7. Yape displays Payment Confirmation screen.
8. User presses on like button.
9. User presses on review button.
10. User writes a review and presses send.

#### End User Case UC03

## **UC04** - Locate near hotels
### **System:** YapeSpots
### **User:** Yape User
### **MSS:**
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

#### End User Case UC04
#### Extensions:
7. 1 User does not enable GPS service.
7.a.1 Yape displays random location.
Resume at step 9

## **UC05** - Locate near hotels
### **System:** YapeSpots
### **User:** Yape User
### **MSS:**
1. User opens Yape app.
2. User logs in
3. User opens YapeSpots
4. Yape loads map at random location
5. User presses “Center GPS” button
6. Yape requests to enable your GPS service.
7. User enables GPS Service.
8. Yape centers map at current position.
9.  User selects YapeSpot.
10. Yape displays YapeSpot information.
11. User presses favorite button.


#### End User Case UC05
#### Extensions:
7. 1 User does not enable GPS service.
7.a.1 Yape displays random location.
Resume at step 9


