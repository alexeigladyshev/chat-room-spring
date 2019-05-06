### Instructions
From the directory containing the src and pom.xml files, the application can be started with the command "mvn spring-boot:run".
 
After startup completes, the application will be accessible at localhost:8080.

Your task is to determine the cause and suggest fixes, if possible, for the reported bugs below.

### Known issues
* When some users send messages, the wrong room is shown next to their name.
  * Medium-low difficulty   
* When a user loses their connection after subsequently connecting to the server, they see the "Disconnected from the server" message multiple times.
  * Medium-low difficulty
* Users should be able to access rooms they created without needing the passcode. However, users report that they must always enter the passcode.
  * Medium-low difficulty
* When a new user enters a room, they are the only one which receives the room entry notification.
  * low difficulty
* If users put the wrong password in for room 100, it loads for a minute or so, but does nothing.  (Reporter used firefox.)  chatroom control line 48, first parameter for status code, it was passed in a room id
  * High difficulty