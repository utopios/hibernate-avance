package org.example.exercicehotel.dao;

import org.example.exercicehotel.Reservation;
import org.hibernate.Session;

public class ReservationDAO extends BaseDAO<Reservation, Long> {
    public ReservationDAO(Session session) {
        super(session);
    }
}
