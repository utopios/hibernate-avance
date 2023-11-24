package org.example.exercicehotel.dao;

import org.example.exercicehotel.Room;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.LockModeType;
import java.math.BigDecimal;
import java.util.List;
public class RoomDAO extends BaseDAO<Room, Long> {
    public RoomDAO(Session session) {
        super(session);
    }

    public List<Room> findByHotelId(Long hotelId, Session session) {
        String hql = "FROM Room r WHERE r.hotel.id = :hotelId";
        Query<Room> query = session.createQuery(hql, Room.class);
        query.setParameter("hotelId", hotelId);
        return query.list();
    }


    public List<Room> findByCriteria(BigDecimal minPrice, BigDecimal maxPrice, Session session) {
        String hql = "FROM Room r WHERE r.price >= :minPrice AND r.price <= :maxPrice";
        Query<Room> query = session.createQuery(hql, Room.class);
        query.setLockMode(LockModeType.PESSIMISTIC_READ);
        query.setParameter("minPrice", minPrice);
        query.setParameter("maxPrice", maxPrice);
        query.setCacheable(true); // Activez le cache de requête
        return query.list();
    }

    public void updateRoomPrice(Long roomId, BigDecimal newPrice, Session session) {
        Room room = session.get(Room.class, roomId);
        if (room != null) {
            room.setPrice(newPrice);
            session.update(room);
        }
    }
}
