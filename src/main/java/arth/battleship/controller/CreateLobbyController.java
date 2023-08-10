package arth.battleship.controller;

import arth.battleship.connection.HostPlayerConnection;
import arth.battleship.connection.PlayerConnection;
import arth.battleship.database.SessionFactoryProvider;
import arth.battleship.gui.BattleshipFrame;
import arth.battleship.gui.PlaceShipsPanel;
import arth.battleship.model.Lobby;
import arth.battleship.model.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CreateLobbyController {
    BattleshipFrame frame;

    public CreateLobbyController() {
        this.frame = BattleshipFrame.getInstance();
    }

    public void createLobby(String name) {
        HostPlayerConnection host = new HostPlayerConnection();
        Player player = new Player();
        PlayerConnection playerConnection = new PlayerConnection(player);
        Lobby lobby = new Lobby(name, host, playerConnection, host.getPort());
        saveLobbyToDatabase(lobby);
        frame.setMainPanel(new PlaceShipsPanel());
    }

    private void saveLobbyToDatabase(Lobby lobby) {
        SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(lobby);
        transaction.commit();

        sessionFactory.close();
    }
}
