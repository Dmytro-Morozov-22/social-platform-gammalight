# Description the project "social-platform-gammalight"
In this project, the user can register using a login and password. After registration, the user needs to authenticate. 
Entering the system, the user can create posts, and write comments to his posts and to the posts of other users. 
It is also possible to like posts, comments, and avatars. When creating a post, you can add several photos for which a folder will be automatically created, 
as well as write a description for the post. If the user adds an avatar, a folder will also be created for it in the project, 
but if the user does not add an avatar, then the default avatar will be pulled.

# How to run
1. download the project 
2. create a database and tables (MySQL) using the file "queriesOfGammaLight.sql"
3. add to the file "application.properties. parameters of your MySQL database (URL, username, password)
4. import file 'GammaLight.postman_collection.json" with a collection of requests to your Postman
5. use requests to interact with the application by means of Postman
