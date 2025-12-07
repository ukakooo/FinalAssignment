package backend; // Tambahkan ini jika file dipindah ke dalam folder backend


public class TestBackendCustomer {
    public static void main(String[] args) {
        Customer cust1 = new Customer(1, "Luffy", "08123456789");
        Customer cust2 = new Customer(2, "Bachiko", "08987654321");
        Customer cust3 = new Customer(3, "Bambang", "08111222333");

        cust1.save();
        cust2.save();
        cust3.save();

        cust1.setCustomerTelp("0812345678");
        cust1.save();

        cust3.delete();

        System.out.println("=== TAMPILKAN SEMUA CUSTOMER ===");
        for(Customer c : new Customer().getAll()) {
            System.out.println("Nama: " + c.getCustomerName() + ", Telp: " + c.getCustomerTelp());
        }

        System.out.println("\n=== PENCARIAN CUSTOMER 'Budi' ===");
        // Pastikan method search di Customer.java mengembalikan ArrayList<Customer>
        for(Customer c : new Customer().search("Budi")) {
            // DISINI YANG ERROR TADI: Ganti getAddress() jadi getCustomerTelp()
            System.out.println("Nama: " + c.getCustomerName() + ", Telp: " + c.getCustomerTelp());
        }
    }
}