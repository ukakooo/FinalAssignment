import backend.*;

public class TestBackendRenting {
    public static void main(String[] args) {
        // Persiapan Data Foreign Key (Asumsi ID 1 ada di tabel Customer dan Game)
        Customer cust = new Customer();
        cust.setIdCustomer(1); // Pastikan ID Customer 1 ada di database
        
        Game game = new Game();
        game.setIdGame(1); 

        Renting rent1 = new Renting(0, cust, game, "2025-11-01", 50000, "2025-11-01", "2025-11-04");
        Renting rent2 = new Renting(0, cust, game, "2025-11-05", 80000, "2025-11-05", "2025-11-08");
        Renting rent3 = new Renting(0, cust, game, "2025-11-10", 100000, "2025-11-10", "2025-11-13");

        rent1.save();
        rent2.save();
        rent3.save();

        rent2.setTotalPrice(95000);
        rent2.setReturnDate("2025-11-09");
        rent2.save();

        rent3.delete();

        System.out.println("=== TAMPILKAN SEMUA DATA (Setelah Insert & Delete) ===");
        for(Renting r : new Renting().getAll()) {
            System.out.println("ID: " + r.getIdRenting() + ", " +
                               "Cust: " + r.getCustomer().getCustomerName() + ", " +
                               "Game: " + r.getGame().getGameTitle() + ", " +
                               "Total: " + r.getTotalPrice() + ", " +
                               "Kembali: " + r.getReturnDate());
        }

        System.out.println("\n=== HASIL PENCARIAN (Keyword: '2025-11-01' atau Nama Customer/Game) ===");
        for(Renting r : new Renting().search("a")) {
             System.out.println("ID: " + r.getIdRenting() + ", " +
                               "Cust: " + r.getCustomer().getCustomerName() + ", " +
                               "Game: " + r.getGame().getGameTitle() + ", " +
                               "Total: " + r.getTotalPrice());
        }
    }
}