# Battleship
Battleship on Swing that allows plays two players on the same local network

First version was just working Battleship game, but there were many problems.
This version 2 is going to make user interface more friendly and to refactor some code,
that became a bit too bad at the end.

I will do next tasks:
- add better UML documentation;
- delete JTextArea from game and add label to show results;
- add different shapes/colors to ships on both sides, if they hit or killed;
- remove all logic from host thread, now it will only exchange commands and messages, and also list;
- add dots around the ship, if it killed;
- create cell classes and some subclasses;
- change BattleshipBuilder and Battleship classes for changes above;
- delete button for creating lobby - first player will create it automatically;
- delete lobby name and player name.

I want to implement some tasks (in this version or in next ones):
- hide/show players board if he doesn't need it;
- create lobby list;
- making "miss" dots by player itself and undo them (for some reason) (they will be different colour/shape);
- show tips on cell statuses (what colour and shape means);
- add ship list on preparation panel (how much you need and how many you placed.

  This lists can be updated anytime, and many tasks was not included due to lack of knowledge.
