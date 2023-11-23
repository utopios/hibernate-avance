package org.example.exercicehotel.service;

import org.example.exercicehotel.Hotel;
import org.example.exercicehotel.Reservation;
import org.example.exercicehotel.Room;
import org.example.exercicehotel.dao.BaseDAO;
import org.example.exercicehotel.dao.HotelDAO;
import org.example.exercicehotel.dao.ReservationDAO;
import org.example.exercicehotel.dao.RoomDAO;
import org.hibernate.Session;

import javax.persistence.Cacheable;
import java.util.List;

public class BusinessService {

    private Session session;
    private final BaseDAO<Hotel, Long> hotelDAO;
    private final BaseDAO<Reservation, Long> reservationDAO;
    private final BaseDAO<Room, Long> roomDAO;

    public BusinessService() {
        hotelDAO = new HotelDAO(session);
        reservationDAO = new ReservationDAO(session);
        roomDAO = new RoomDAO(session);
    }


    public void saveRoom() {
        //
    }

    public  List<Room> getRooms() {
        return null;
    }

}
