package org.example.exercicehotel;

import org.example.exercicehotel.service.BusinessService;
import org.example.exercicehotel.util.HibernateUtil;

import java.math.BigDecimal;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        // Configuration et création de la SessionFactory
        BusinessService service = new BusinessService(HibernateUtil.getSessionFactory());

        // Test de création d'un hôtel avec des chambres aléatoires
        //service.createRandomHotelWithRooms("Hotel Test", 5);

        // Affichage et rafraîchissement des détails d'un hôtel
        Hotel hotel = service.getHotelDetails(1L); // Remplacer par un ID d'hôtel valide
        System.out.println("Hotel Details: " + hotel.getName());
        Hotel refreshedHotel = service.refreshHotelDetails(1L);
        System.out.println("Refreshed Hotel Details: " + refreshedHotel.getName());

        // Test du cache de second niveau
        service.testSecondLevelCache(1L);

        // Recherche de chambres
        List<Room> rooms = service.searchRooms(BigDecimal.valueOf(50.0), BigDecimal.valueOf(200.0));
        System.out.println("Found " + rooms.size() + " rooms");

        // Test du cache de requête
        service.testQueryCache(BigDecimal.valueOf(50.0), BigDecimal.valueOf(200.0));

        // Test de la modification du prix d'une chambre
        Long roomId = 7L; // Remplacer par un ID de chambre valide
        service.updateRoomPrice(roomId, BigDecimal.valueOf(150.0));

        // Vérification de la mise à jour du prix dans la recherche
        List<Room> roomsAfterUpdate = service.searchRooms(BigDecimal.valueOf(100.0), BigDecimal.valueOf(200.0));
        roomsAfterUpdate.forEach(room -> System.out.println("Room: " + room.getNumber() + ", Price: " + room.getPrice()));


        // Fermeture de la SessionFactory
        HibernateUtil.getSessionFactory().close();
    }
}
