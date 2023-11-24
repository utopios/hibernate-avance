package org.example.exercicehotel.service;

import org.example.exercicehotel.Hotel;
import org.example.exercicehotel.Reservation;
import org.example.exercicehotel.Room;
import org.example.exercicehotel.dao.BaseDAO;
import org.example.exercicehotel.dao.HotelDAO;
import org.example.exercicehotel.dao.ReservationDAO;
import org.example.exercicehotel.dao.RoomDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Cacheable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

public class BusinessService {

    private final SessionFactory sessionFactory;
    private Session session;
    private final BaseDAO<Hotel, Long> hotelDAO;
    private final BaseDAO<Reservation, Long> reservationDAO;
    private final BaseDAO<Room, Long> roomDAO;

    public BusinessService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        session = sessionFactory.openSession();
        hotelDAO = new HotelDAO(session);
        reservationDAO = new ReservationDAO(session);
        roomDAO = new RoomDAO(session);
    }

    public Hotel getHotelDetails(Long hotelId) {
        Hotel hotel;
        try (Session session = sessionFactory.openSession()) {
            hotel = hotelDAO.findById(hotelId, session);
        }
        return hotel;
    }

    public Hotel refreshHotelDetails(Long hotelId) {
        Hotel hotel;
        try (Session session = sessionFactory.openSession()) {
            session.clear(); // Efface le cache de session
            hotel = hotelDAO.findById(hotelId, session);
        }
        return hotel;
    }

    public List<Room> getRoomsByHotelId(Long hotelId) {
        List<Room> rooms;
        try (Session session = sessionFactory.openSession()) {
            // Utilisation du roomDAO pour récupérer les chambres
            rooms = ((RoomDAO)roomDAO).findByHotelId(hotelId, session);
        }
        return rooms;
    }

    public void testSecondLevelCache(Long hotelId) {
        List<Room> firstAttempt = getRoomsByHotelId(hotelId);
        List<Room> secondAttempt = getRoomsByHotelId(hotelId);
    }

    public void createRandomHotelWithRooms(String hotelName, int numberOfRooms) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            // Création d'un nouvel hôtel
            Hotel hotel = new Hotel();
            hotel.setName(hotelName);
            hotelDAO.save(hotel, session);

            // Création de chambres aléatoires
            for (int i = 0; i < numberOfRooms; i++) {
                Room room = createRandomRoom(hotel);
                roomDAO.save(room, session);
            }

            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback(); // Rollback en cas d'exception
            }
            throw e; // Propager l'exception pour signaler l'échec
        }
    }

    private Room createRandomRoom(Hotel hotel) {
        Random random = new Random();
        Room room = new Room();
        room.setHotel(hotel);
        room.setNumber("Room " + (random.nextInt(100) + 1)); // Numéro de chambre aléatoire
        room.setPrice(BigDecimal.valueOf(random.nextDouble() * 100 + 50)); // Prix aléatoire entre 50 et 150
        return room;
    }


    public List<Room> searchRooms(BigDecimal minPrice, BigDecimal maxPrice) {
        List<Room> rooms;
        try (Session session = sessionFactory.openSession()) {
            rooms = ((RoomDAO)roomDAO).findByCriteria(minPrice, maxPrice, session);
        }
        return rooms;
    }

    public void testQueryCache(BigDecimal minPrice, BigDecimal maxPrice) {
        List<Room> firstSearch = searchRooms(minPrice, maxPrice);
        List<Room> secondSearch = searchRooms(minPrice, maxPrice);

    }

    public void updateRoomPrice(Long roomId, BigDecimal newPrice) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            ((RoomDAO) roomDAO).updateRoomPrice(roomId, newPrice, session);

            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback(); // Rollback en cas d'exception
            }
            throw e; // Propager l'exception pour signaler l'échec
        }
    }

}
