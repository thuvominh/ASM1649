class Order {
    private String customerName;
    private String shippingAddress;
    private ArrayList<Book> books;
    private int orderID;

    public Order(String customerName, String shippingAddress, ArrayList<Book> books, int orderID) {
        this.customerName = customerName;
        this.shippingAddress = shippingAddress;
        this.books = books;
        this.orderID = orderID;
    }

    private String getCustomerName() {
        return customerName;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public int getOrderID() {
        return orderID;
    }

    public void sortBooksByTitle(boolean increasing) {
        // Sử dụng thuật toán Insertion Sort để sắp xếp danh sách sách theo tiêu đề
        if (increasing) {
            for (int i = 1; i < books.size(); i++) {
                Book key = books.get(i);
                int j = i - 1;
                while (j >= 0 && books.get(j).getTitle().compareTo(key.getTitle()) > 0) {
                    books.set(j + 1, books.get(j));
                    j = j - 1;
                }
                books.set(j + 1, key);
            }
        }else{
            for (int i = 1; i < books.size(); i++) {
                Book key = books.get(i);
                int j = i - 1;
                while (j >= 0 && books.get(j).getTitle().compareTo(key.getTitle()) < 0) {
                    books.set(j + 1, books.get(j));
                    j = j - 1;
                }
                books.set(j + 1, key);
            }
        }
    }
    public void sortBooksByAuthor(boolean increasing) {
        // Sử dụng thuật toán Insertion Sort để sắp xếp danh sách sách theo tác giả
        if (increasing) {
            for (int i = 1; i < books.size(); i++) {
                Book key = books.get(i);
                int j = i - 1;
                while (j >= 0 && books.get(j).getAuthor().compareTo(key.getAuthor()) > 0) {
                    books.set(j + 1, books.get(j));
                    j = j - 1;
                }
                books.set(j + 1, key);
            }
        }else{
            for (int i = 1; i < books.size(); i++) {
                Book key = books.get(i);
                int j = i - 1;
                while (j >= 0 && books.get(j).getAuthor().compareTo(key.getAuthor()) < 0) {
                    books.set(j + 1, books.get(j));
                    j = j - 1;
                }
                books.set(j + 1, key);
            }
        }
    }
    public void showOrderInfor(){
        System.out.println("-------------------");
        System.out.println("Mã đơn hàng: "+ getOrderID());
        System.out.println("Tên khách hàng: " + getCustomerName());
        System.out.println("Địa chỉ giao hàng: "+ getShippingAddress());
        if (books.size()==0){
            System.out.println("Chưa đặt hàng sách.");
        }else {
            System.out.println("Sách đã đặt:");
            for (int i = 0; i < books.size(); i++) {
                books.get(i).showBookInfor();
                System.out.println(". Số lượng: " + books.get(i).getQuantity());
            }
        }
        System.out.println("-------------------");
    }
}