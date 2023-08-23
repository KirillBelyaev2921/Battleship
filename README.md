# Battleship
Battleship on Swing that allows plays two players on the same local network

This is snapshot of version 2 of program.

Compared to the first version, this is definitely much better. After many code 
refactoring it`s became better to work with. I was reading "Head First OOA&D" while working on this version.
It was really helpful and interesting, and when I will implement new big feature, I will return to it and do it steps by steps.

Tasks that have been completed:
- add better UML documentation (class diagram only. I will return to this after some good UML book);
- delete JTextArea from game and add label to show results;
- add different shapes/colors to ships on both sides, if they hit or killed;
- remove all logic from host thread, now it will only exchange commands and messages;
- add dots around the ship, if it killed;
- create cell classes and some subclasses;
- //change BattleshipBuilder and Battleship classes for changes above (it was unnecessary, encapsulation did it for me);
- //delete button for creating lobby - first player will create it automatically (I changed my mind);
- delete lobby name and player name.

As for the big feature, it will be playing with the bot. It was obvious to add it, but I came up with this idea only few days ago.
But I understand, that I messed up with MVC pattern, so it will be not so easy. So, version 3 will be about Design patterns (another Head First book),
where I will implement in this project as many patterns as I can, and also continue redesign and refactoring code.
Then, in version 4, I will try to implement 1 player game with bot enemy, starting with simple enemy AI and try to implement some bigger ML stuff and methods.