# Formula
Project Instruction

This application is developed using Spring Boot, Spring Data, Spring RESTful web services, Maven, PostgreSql, Docker, Amazon SQS, Amazon S3.

Assumptions
Users are provided player and team informations based on the authorities.
The users information need to be created before searching.
The relation between team and player is "One to Many", the relationship between player and player statistics is "One to One".
Approach
Created User, Team, Player, and Player Statistics object, and created related table and col in the database.
The relation between Team and Player is One to Many, the team_id will be the foregin key and will be stored in the player table.
The relation between Player and Player Statistics is One to One, the player_id will be the foregin key and will be stored in the player statistics table.
