# Use cases (version 1).

### Use case 1
#### Join lobby

1. Player press join the lobby button
   - 1.1 If there are no lobby, player creates the lobby
   - 1.2 Player waits until second player joins his lobby
2. Player joins the lobby and starts to place battleships.

### Use case 2
#### Place battleships
1. Player joins the lobby and starts to place battleships.
2. Player mark cell as "Ship Placed".
    - 2.1.1 If player places cell in incorrect way, he gets notification about it
    - 2.2.1 Player mark cell from "Placed" to empty (remove cell)
3. Do 2 until all battleships are ready.
4. Press ready button.

### Use case 3
#### Game
1. 2 Players press ready button.
2. Player starts the turn.
    - 2.1 Enemy start the turn.
    - 2.2 Move to the point 7.
3. Players select empty cell
    - 3.1 Player can change selected cell, as long as it empty
4. Player shoots at the selected cell on enemy's board
5. Player misses
    - 5.1 Player hits enemy's battleship 
      - 5.1.1 Player kills enemy's battleship
        - 5.1.1.1 Player killed all the enemy's battleships
        - 5.1.1.2 Move to the point 10.
    - 5.2 Move to the point 3.
6. Enemy starts the turn
7. Enemy shoots
8. Enemy misses
    - 5.1 Enemy hits player's battleship
        - 5.1.1 Enemy kills player's battleship
            - 5.1.1.1 Enemy killed all the player's battleships
            - 5.1.1.2 Move to the point 10.1.
    - 5.2 Move to the point 7.
9. Move to the point 2.
10. Player wins the game
    - 10.1 Enemy wins the game