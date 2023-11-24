package org.example.exercicehotel.dao;

import org.example.exercicehotel.Reservation;
import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.Session;

public class ReservationDAO extends BaseDAO<Reservation, Long> {
    public ReservationDAO(Session session) {
        super(session);
    }

    public void makeReservation(Reservation reservation, Session session) {
        //Verouillage pessimiste sur la session
        session.buildLockRequest(new LockOptions(LockMode.PESSIMISTIC_READ));

        //Créer la réservation ...

    }
}
