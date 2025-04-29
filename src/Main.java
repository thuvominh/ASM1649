import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final Scanner input = new Scanner(System.in);
    private static final ArrayList<Book> books = new ArrayList<>();
    private static final OrderQueue orderQueue = new OrderQueue();
    private static int nextOrderId = 1;

    public static void main(String[] args) {
        initializeSampleBooks();
        boolean isRunning = true;

        while (isRunning) {
            showMenu();
            try {
                int option = Integer.parseInt(input.nextLine());

                switch (option) {
                    case 1 -> addNewOrder();
                    case 2 -> processFirstOrder();
                    case 3 -> sortBooksInOrder();
                    case 4 -> searchOrderById();
                    case 5 -> displayAllBooks();
                    case 6 -> addBooksToCatalog();
                    case 7 -> {
                        isRunning = false;
                        System.out.println("Cảm ơn bạn đã sử dụng chương trình!");
                    }
                    default -> System.out.println("Vui lòng chọn số từ 1 đến 7!");
                }

            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập đúng định dạng số.");
            }
        }

        input.close();
    }


    private static void showMenu() {
        System.out.println("""
------- HỆ THỐNG QUẢN LÝ ĐƠN HÀNG -------
1. Tạo đơn hàng mới
2. Xử lý đơn hàng đầu tiên
3. Sắp xếp sách trong đơn hàng
4. Tìm kiếm đơn hàng theo mã
5. Xem danh sách sách hiện có
6. Thêm sách mới vào danh mục
7. Thoát chương trình
""");
        System.out.print("Nhập lựa chọn của bạn: ");
    }


    private static void initializeSampleBooks() {
        books.add(new Book("Tắt đèn", "Ngô Tất Tố"));
        books.add(new Book("Lão Hạc", "Nam Cao"));
        books.add(new Book("Chí Phèo", "Nam Cao"));
        books.add(new Book("Tuổi thơ dữ dội", "Phùng Quán"));
        books.add(new Book("Dế Mèn phiêu lưu ký", "Tô Hoài"));
    }


    private static void addNewOrder() {
        System.out.print("Nhập tên khách hàng: ");
        String customerName = input.nextLine();

        System.out.print("Nhập địa chỉ giao hàng: ");
        String shippingAddress = input.nextLine();

        ArrayList<Book> orderBooks = new ArrayList<>();
        boolean addingBooks = true;

        while (addingBooks) {
            displayAllBooks();
            System.out.print("Nhập ID sách muốn đặt (ví dụ: 1): ");
            int bookId = Integer.parseInt(input.nextLine()) - 1;

            if (bookId < 0 || bookId >= books.size()) {
                System.out.println("ID sách không hợp lệ!");
                continue;
            }

            System.out.print("Nhập số lượng: ");
            int quantity = Integer.parseInt(input.nextLine());

            Book selectedBook = books.get(bookId);
            orderBooks.add(new Book(selectedBook.getTitle(), selectedBook.getAuthor(), quantity));

            System.out.print("Thêm sách khác? (y/n): ");
            String userAnswer = input.nextLine().trim();
            addingBooks = userAnswer.equalsIgnoreCase("y");
        }

        Order newOrder = new Order(customerName, shippingAddress, orderBooks, nextOrderId++);
        orderQueue.addOrder(newOrder);
        System.out.println("Đơn hàng đã được thêm thành công!");
    }


    private static void processFirstOrder() {
        Order processedOrder = orderQueue.processOrder();
        if (processedOrder != null) {
            System.out.println("Đã xử lý đơn hàng: " + processedOrder.getOrderID());
            processedOrder.showOrderInfor();
        } else {
            System.out.println("Không có đơn hàng nào trong hàng đợi!");
        }
    }


    private static void sortBooksInOrder() {
        System.out.print("Nhập mã đơn hàng cần sắp xếp: ");
        int orderId = Integer.parseInt(input.nextLine());

        Order order = orderQueue.searchOrder(orderId);
        if (order == null) {
            System.out.println("Không tìm thấy đơn hàng.");
            return;
        }

        System.out.print("Sắp xếp theo:\n1. Tiêu đề\n2. Tác giả\nChọn: ");
        int fieldOption = Integer.parseInt(input.nextLine());

        System.out.print("Thứ tự:\n1. Tăng dần (A-Z)\n2. Giảm dần (Z-A)\nChọn: ");
        int orderOption = Integer.parseInt(input.nextLine());

        boolean ascending = orderOption == 1;

        if (fieldOption == 1) {
            order.sortBooksByTitle(ascending);
        } else {
            order.sortBooksByAuthor(ascending);
        }

        System.out.println("Đã sắp xếp xong danh sách sách!");
    }


    private static void searchOrderById() {
        System.out.print("Nhập mã đơn hàng cần tìm: ");
        int orderId = Integer.parseInt(input.nextLine());

        Order order = orderQueue.searchOrder(orderId);
        if (order != null) {
            order.showOrderInfor();
        } else {
            System.out.println("Không tìm thấy đơn hàng!");
        }
    }

        private static void displayAllBooks() {
        System.out.println("Danh sách sách hiện tại:");
        for (int i = 0; i < books.size(); i++) {
            System.out.print((i + 1) + ". ");
            books.get(i).showBookInfor();
            System.out.println();
        }
    }

    private static void addBooksToCatalog() {
        System.out.print("Nhập số lượng sách cần thêm: ");
        int count = Integer.parseInt(input.nextLine());

        for (int i = 0; i < count; i++) {
            System.out.print("Tiêu đề sách: ");
            String title = input.nextLine();

            System.out.print("Tác giả: ");
            String author = input.nextLine();

            books.add(new Book(title, author));
            System.out.println("Đã thêm sách thành công!");
        }
    }
}