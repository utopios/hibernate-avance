package org.example.exercicehotel.dao;

import org.example.exercicehotel.Room;
import org.hibernate.Session;

public class RoomDAO extends BaseDAO<Room, Long> {
    public RoomDAO(Session session) {
        super(session);
    }
}
