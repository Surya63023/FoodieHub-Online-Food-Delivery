# 🏗️ Architecture Overview – FoodieHub

FoodieHub follows a layered MVC + DAO architecture to ensure clean separation of concerns and maintainability.

## Layers

### View Layer (JSP)
- Handles UI rendering
- Displays data received from servlets
- No business logic

### Controller Layer (Servlets)
- Handles HTTP requests and responses
- Controls application flow
- Communicates with DAO layer

### DAO Layer
- Encapsulates database access logic
- Uses JDBC for persistence
- Keeps servlets database-agnostic

### Model Layer
- Represents business entities
- Maps closely to database tables

### Database Layer
- MySQL relational database
- Normalized schema
- Uses views for reporting

## Request Flow Example

menu.jsp  
→ MenuServlet  
→ MenuDAO  
→ MenuDAOImpl  
→ MySQL  
→ Model Objects  
→ JSP response

This architecture makes the application easy to extend, debug, and explain during interviews.
