# WordSweeper

This is a final client version for task 2 from team Libra.

Notes:
1.We finished the connection and sent the requests about create game, join game and practice(although we know maybe practice won't need to send request, just put it there).
2.We replaced the application with login interface, because we think login is the indeed right "application" for us.
3.We used the model class to contain most of the entity classes and when game start, we make them into instances through model.
4.About the random letter, we decided to use arraylist to implement the structure of Board. 
5.For now, we defined the player cannot create with a empty name and a joiner cannot join with a empty name and empty game id.
We will return a warning.
