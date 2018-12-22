# Udacity-Project-8-News-App
A news application that utilizes the Guardian API to populate a list of articles based on the user's preferences

![](news_app_video.gif)

## Requirements

#### Layout
- [x] Settings Activity allows users to see the value of all the preferences right below the preference name, and when the value is changed, the summary updates immediate.
- [x] App contains a main screen which displays multiple news stories
- [x] The title of the article and the name of the section that it belongs to are required field. 
     -If available, author name and date published should be included. Please note not all responses will contain these pieces of data, but it is required to include them if they are present.
     -Images are not required.
- [x] The code adheres to all of the following best practices:
     - Text sizes are defined in sp
     - Lengths are defined in dp
     - Padding and margin is used appropriately, such that the views are not crammed up against each other.

#### Functionality
- [x] Settings Activity is accessed from the Main Activity via a Navigation Drawer or from the toolbar menu.
     -In accordance with Material Design Guidelines, it should be reached via the "Settings" label. Do not use synonyms such as "Options" or "Preferences."
- [x] The code runs without errors.
- [x] App queries the content.guardianapis.com API to fetch news stories related to the topic chosen by the user, using either the ‘test’ api key or the student’s key.
     -The query is narrowed down by the preferences selected by the user in the Settings.
- [x] Networking operations are done using a Loader rather than an AsyncTask.
- [x] The intent of this project is to give you practice writing raw Java code using the necessary classes provided by the Android framework; therefore, the use of external libraries for the core functionality will not be permitted to complete this project.

 #### Code Readability
- [x] Code is easily readable so that a fellow programmer can understand the purpose of the app.
- [x] All variables, methods, and resource IDs are descriptively named so that another developer reading the code can easily understand their function.
- [x] The code is properly formatted:
     - No unnecessary blank lines
     - No unused variables or methods
     - No commented out code
- [x] The code also has proper indentation when defining variables and methods.
