# AstroPing - Skynet Hackathon Submission by Lucy Chen and Sheldon Jian
## Inspiration
#### Before the opening ceremony, our team already wanted to include an email and desktop notification feature as it was something neither of us has done before. Once we found out it was space themed, we thought of using an API to gather current astronomical data. Combined with our initial idea of email and desktop notifications, we decided we wanted to make our project informative, educational and interesting for people who want to learn more about astronomy or just want a convenient way to access current astronomical data.
## What it does
- You choose a timer interval of your choice depending on how often you want desktop notifications, containing information (name of object, current distance from Earth, current position of the object using the right ascension and declination system, and the constellation of the object) on a random celestial body from the API, to pop up. Note that this infomation changes/updates depending on what date it is. Our program uses the current date.
- After choosing an interval (in seconds) and clicking on "Start Timer", the timer will start counting down and when it reaches 0, the notification will be fired and the timer will reset again, counting from the interval to zero.
- You can pause the timer by clicking "Stop Timer" and resume the timer from where it left off by clicking "Start Timer" again.
- There is also a "Send Email" button that sends an email to whatever email you put in the message dialog. The email gives information on the different bodies in our solar system like the constellation, distance from Earth, current position, current moon phase (in the form of a web image), and current star chart (in the form of a web image).
## How we built it
- Tools used: IntelliJ, Visual Studio Code, and GitHub
- API used: https://docs.astronomyapi.com/
- JAR files: [Java Mail API](https://www.oracle.com/java/technologies/javamail-api.html), [JavaBeans Activation Framework](https://www.oracle.com/java/technologies/downloads.html), [JSON](https://mvnrepository.com/artifact/org.json/json)
- Design tool: [Figma](https://www.figma.com/design/)
- Output: Java Graphical User Interface
## Challenges we ran into
- We had trouble trying to make a working timer that would be able to pause and still be able to keep the program running.
- We had a small issue trying to load in the images from our files
- Extracting data from JSON was challenging as there was nested JSON objects and JSON arrays
## Accomplishments that we're proud of
- The visuals of the GUI
- Setting a timer that pauses and starts based on user interaction
- Figuring out how to send desktop notifications and emails
- Retrieving data from the Astronomy API

## What we learned
- Retrieving values from nested JSON Objects and JSON Arrays
- Java Timer Class
- Sending desktop notifications using Java
- Sending emails using Java
- Cool astronomy facts from the API

## What's next for AstroPing
- We want to add a feature where the user can view past astronomy data, not just the current.
- Our data only consists of planets, Earth's moon, and the sun. We would like to add data on stars and galaxies as well.
- We want to add a feature where clicking the desktop notification will bring you to a website with more information on the topic.
- A tutorial button on our GUI that explains astronomy terminologies such as right ascension, declination, and constellation.
