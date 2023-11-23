package org.example.exercicehotel.dao;

import org.example.exercicehotel.Hotel;
import org.hibernate.Session;

public class HotelDAO extends BaseDAO<Hotel, Long> {
    public HotelDAO(Session session) {
        super(session);
    }
}
