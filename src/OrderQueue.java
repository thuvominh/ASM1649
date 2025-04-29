class OrderQueue {
    private Queue<Order> orderQueue = new Queue<>();

    public void addOrder(Order order) {
        orderQueue.offer(order);
    }

    public Order processOrder() {
        if (orderQueue.isEmpty()) return null;
        return orderQueue.poll();
    }

    public Order searchOrder(int orderID) {
        for (Order order : orderQueue) {
            if (order.getOrderID() == (orderID)) {
                return order;
            }
        }
        return null;
    }
}