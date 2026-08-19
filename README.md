🏪 Shop Inventory Management System

A full-stack inventory and order management system built with Spring Boot and vanilla JavaScript, designed to help small shop owners track products, manage orders, and stay on top of low-stock alerts in real time.

📌 Features
Product Management — Add, edit, and delete products with live stock tracking
Order Placement — Place multi-item orders with automatic stock deduction
Low Stock Alerts — Products with quantity below 10 are automatically flagged
Live Dashboard — At-a-glance stats: total products, total orders, and low-stock count
Order History — View all past orders with item counts and totals
🛠️ Tech Stack

Backend

Java
Spring Boot
Spring Data JPA / Hibernate
MySQL

Frontend

HTML, CSS, JavaScript (Fetch API)
🗂️ Project Structure
src/main/java/com/Inventory/Inventory1/
├── controller/         # REST controllers
├── Productservice/      # Business logic (Product, Order services)
├── Repository/          # Spring Data JPA repositories
├── model/                # Entity classes (Product, Order, OrderItem, OrderRequest)
└── Inventory1Application.java
🔌 API Endpoints
Method	Endpoint	Description
GET	/products	Get all products
POST	/products	Add a new product
PUT	/products/{id}	Update a product
DELETE	/products/{id}	Delete a product
GET	/products/lowstock	Get products with quantity < 10
GET	/orders	Get all orders (with items)
POST	/orders	Place a new order
⚙️ Setup & Run Locally
Clone the repo
bash
git clone https://github.com/saravanabhavank23/Inventory-Billing-System.git
Configure your MySQL database in src/main/resources/application.properties
properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_db_name
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
Run the Spring Boot application
bash
mvn spring-boot:run
Open index.html in your browser (make sure BASE_URL in the script matches your backend port, default http://localhost:8081)
🐞 A Debugging Highlight

While building the Orders feature, serializing Order → OrderItems as JSON triggered a classic Hibernate LazyInitializationException since @OneToMany collections default to lazy loading. Fixed it by writing a custom JPQL query with JOIN FETCH in the repository layer instead of relying on default lazy fetch behavior — a good reminder that ORM defaults don't always match what an API response needs.

java
@Query("SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.items")
List<Order> findAllWithItems();
🚀 Future Improvements
Deploy live demo (Render/Railway)
Add authentication for admin actions
Add pagination for products/orders
Add search and filter by category
👤 Author

Saravanabhavan K. GitHub