# Glamora - Online Fashion Shopping Application (Backend)

### Table of Contents

1. [Project Overview](#project-overview)
2. [Features](#features)
3. [Installation](#installation)

---
### Project Overview

Glamora is an application that helps customers view and purchase products. Improving retailer productivity by optimizing fashion shop management procedures.

### Main Features

  ### Authentication
  
  - **User Registration**: Users can create an account using their email and password.
  - **User Login**: Users can log in to their account using their email and password.
  - **Password Reset**: Users can reset their password if they forget it.
  - **JWT Authentication**: Secure authentication using JSON Web Tokens.
  
  ### Product Management
  
  - **CRUD Operations for Products**: Administrators can create, read, update, and delete products.
  - **Category Management**: Products can be categorized for better organization and searchability.
  
  ### Customer Management
  - **CRUD Operations for Customers**: Administrators can create, read, update, and delete products.
  - **View Customer's Order History:**  Administrators can view a comprehensive list of all orders placed by a specific customer. This includes details such as order dates, statuses, and total amounts.
  
  ### Order Management
  
  - **Order Creation and Tracking**: Users can place orders and track their status from processing to delivery.
  
  ### Statistics
   - **Revenue Tracking**:
       + Daily/Weekly/Monthly Revenue: Display total revenue on a daily, weekly, and monthly basis. This helps administrators easily monitor revenue fluctuations over time.
       + Revenue by Product: Provides statistics on revenue distribution by product, allowing identification of high and low-performing products.
   - **Expense Tracking:** Daily/Weekly/Monthly Expenses: Display total expenses on a daily, weekly, and monthly basis. Helps in continuous expense tracking and budget control.
  
  ### Installation
  
  To set up the project locally, follow these steps:

1. **Clone the repository**:
   
     git clone https://github.com/SONLE03/ClothingStoreManagement_BE.git
     
     cd ClothingStoreManagement_BE

2. **Set up environment variables in application.properties file:**
   
    spring.datasource.url=<your_MYSQL_uri>
    
    spring.datasource.username=<your_username>
    
    spring.datasource.password=<your_password>
    
    cloudinary.cloud-name=<your_cloudinary_cloud_name>
    
    cloudinary.api-key=<your_cloudinary_api_key>
    
    cloudinary.api-secret=<your_cloudinary_api_secret>
    
    security.jwt.secret-key=<your_secret_key>

3. **Start the server:**
   
   mvn spring-boot:run
