package arth.battleship.controller;

import arth.battleship.database.SessionFactoryProvider;
import arth.battleship.model.Lobby;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ChooseLobbyController {
    public void chooseLobby(Lobby selected) {
        System.out.println("selected " + selected);
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
