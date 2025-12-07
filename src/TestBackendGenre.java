import backend.*;

public class TestBackendGenre {
    public static void main(String[] args) {
        Genre kat1 = new Genre("RPG", "Role Playing Game");
        Genre kat2 = new Genre("FPS", "First Person Shooter");
        Genre kat3 = new Genre("Adventure", "Petualangan dan Eksplorasi");

        kat1.save();
        kat2.save();
        kat3.save();

        kat2.setDescription("Tembak-tembakan sudut pandang orang pertama");
        kat2.save();

        kat3.delete();

        System.out.println("=== TAMPILKAN SEMUA GENRE ===");
        for(Genre g : new Genre().getAll()) {
            System.out.println("Nama: " + g.getName() + ", Ket: " + g.getDescription());
        }

        System.out.println("\n=== PENCARIAN GENRE 'RPG' ===");
        for(Genre g : new Genre().search("RPG")) {
            System.out.println("Nama: " + g.getName() + ", Ket: " + g.getDescription());
        }
    }
}