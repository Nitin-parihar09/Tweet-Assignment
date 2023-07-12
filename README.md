# Tweet-Assignment

Steps to Run

1. CLone the Project at your system
2. Open the application.properties file
3. Change the MySQL configuration according to your system and create a database named as "TweetApplication"
4. Run the project as Java application
5. Hibernate will automatically create all the tables

Run The following APIs
1. Create User - POST : http://localhost:8080/createUser Params("name" : "name")
2. Create Tweet - POST : http://localhost:8080/createTweet Params("content" : "content", "user_id" : "user_id")
3. Get Tweet By UserId - GET: http://localhost:8080/getTweetsByUser/{Pass the user id}
4. get Tweet By TweetId - GET: http://localhost:8080/getTweets/{Pass the tweet id}
5. Update Tweet - PUT : http://localhost:8080/updateTweet/{Pass the tweet id} Params("content" : "content")
6. Delete Tweet - DELETE: http://localhost:8080/delete/{Pass the tweet id}
