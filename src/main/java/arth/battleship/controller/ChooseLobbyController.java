package arth.battleship.controller;

import arth.battleship.connection.PlayerConnection;
import arth.battleship.database.SessionFactoryProvider;
import arth.battleship.gui.BattleshipFrame;
import arth.battleship.gui.PlaceShipsPanel;
import arth.battleship.model.Lobby;
import arth.battleship.model.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ChooseLobbyController {
    public void chooseLobby(Lobby lobby) {
        Player player = new Player();
        PlayerConnection playerConnection = new PlayerConnection(player, lobby.getPort());
        lobby.setPlayerTwo(playerConnection);
        BattleshipFrame frame = BattleshipFrame.getInstance();
        frame.setMainPanel(new PlaceShipsPanel(lobby, playerConnection));
    }

    public Lobby[] showLobbyList() {
        SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Lobby> criteriaQuery = criteriaBuilder
                .createQuery(Lobby.class);

        Root<Lobby> root = criteriaQuery.from(Lobby.class);
        criteriaQuery.select(root);

        Query<Lobby> query = session.createQuery(criteriaQuery);
        List<Lobby> list = query.getResultList();
        transaction.commit();

        sessionFactory.close();

        Lobby[] lobbyList = new Lobby[list.size()];
        System.out.println(list);
        return list.toArray(lobbyList);
    }
}
