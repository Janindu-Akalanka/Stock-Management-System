import java.util.*; // Import the java.util package to use the Scanner class

// Main class for the Stock Management System
public class StockManagementSystem {
    
    // ---------------------------------------------------------
    // GLOBAL VARIABLES AND DATA STORAGE
    // ---------------------------------------------------------

    // Hardcoded credentials for login authentication
    // Note: 'final' means these values are constants and cannot be changed programmatically in this scope
    private static final String USERNAME = "sumu356";
    private static final String PASSWORD = "356";

    // Supplier Data Storage
    // row 0: id, row 1: name
    private static final int SUPPLIER_DETAILS = 2; 
    // 2D Array to store up to 100 suppliers
    private static String[][] suppliers = new String[100][SUPPLIER_DETAILS];
    // Counter to track the current number of suppliers stored
    private static int supplierCount = 0;

    // Item Data Storage
    // id, name, category id, price, quantity
    private static final int ITEM_DETAILS = 5; 
    // 2D Array to store up to 200 items
    private static String[][] items = new String[200][ITEM_DETAILS];
    // Counter to track the current number of items stored
    private static int itemCount = 0;

    // Item Category Data Storage
    // Parallel arrays to store Category IDs and Names separately
    private static String[] categoryIds = new String[50];
    private static String[] categoryNames = new String[50];
    // Counter to track the current number of categories stored
    private static int categoryCount = 0;

    // ---------------------------------------------------------
    // UTILITY METHODS
    // ---------------------------------------------------------

    // Custom method to compare two strings manually (character by character)
    // Returns true if strings are identical, false otherwise
    private static boolean isEqual(String s1, String s2) {
        // First check: if lengths differ, they cannot be the same
        if (s1.length() != s2.length()) {
            return false;
        }

        // Loop through every character
        for (int i = 0; i < s1.length(); i++) {
            // If any character at the specific index differs, return false
            if (s1.charAt(i) != s2.charAt(i)) {
                return false;
            }
        }

        // If loop finishes without returning false, the strings are equal
        return true;
    }

    // ---------------------------------------------------------
    // MAIN MENU / HOME PAGE
    // ---------------------------------------------------------

    private static void showHomePage(Scanner scanner) {
        // Infinite loop to keep the menu running until the user chooses to Exit or Logout
        while (true) {
            System.out.println();
            System.out.println("+---------------------------------------------------+");
            System.out.println("| WELCOME TO PIGEON SERVICE STOCK MANAGEMENT SYSTEM |");
            System.out.println("+---------------------------------------------------+\n");

            // Display Menu Options
            System.out.print("[1] Change Credentials");
            System.out.println("\t\t[2] Supplier Manage");
            System.out.print("[3] Stock Manage");
            System.out.println("\t\t[4] Logout");
            System.out.println("[5] Exit\n");
            System.out.print("Enter option: ");

            // Read user input
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume the leftover newline character from nextInt()

            // Handle user choice
            switch (option) {
                case 1:
                    changeCredentials(scanner); // Go to Change Password screen
                    break;
                case 2:
                    showSupplierManageMenu(scanner); // Go to Supplier menu
                    break;
                case 3:
                    showStockManageMenu(scanner); // Go to Stock/Item menu
                    break;
                case 4:
                    System.out.println("Logged out.\n");
                    return; // Break the loop and return to main() (which effectively ends the program or asks for login again depending on flow)
                case 5:
                    System.out.println("Exiting system.\n");
                    System.exit(0); // Forcefully terminate the Java program
                default:
                    System.out.println("Invalid option. Try again.\n"); // Handle invalid integers
            }
        }
    }

    // ---------------------------------------------------------
    // CREDENTIAL MANAGEMENT
    // ---------------------------------------------------------

    private static void changeCredentials(Scanner scanner) {
        System.out.println();
        System.out.println("+---------------------------------------------------+");
        System.out.println("|                 CHANGE CREDENTIALS                |");
        System.out.println("+---------------------------------------------------+\n");
        
        // Verify current user
        System.out.print("Enter current username to verify it's you: ");
        String currentUsername = scanner.nextLine();
        if (!currentUsername.equals(USERNAME)) {
            System.out.println("Invalid username. Try again.");
            return;
        }

        System.out.println("Hello " + USERNAME);

        // Verify current password
        System.out.print("Enter current password: ");
        String currentPassword = scanner.nextLine();
        if (!currentPassword.equals(PASSWORD)) {
            System.out.println("Invalid password. Try again.");
            return;
        }

        // Ask for new password
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();

        // Note: Logic to actually update the 'final' static variable is not possible here 
        // without removing the 'final' keyword, but the UI flow simulates it.
        System.out.println("Password changed successfully.");
    }

    // ---------------------------------------------------------
    // SUPPLIER MANAGEMENT MODULE
    // ---------------------------------------------------------

    private static void showSupplierManageMenu(Scanner scanner) {
        // Loop for the Supplier Sub-menu
        while (true) {
            System.out.println();
            System.out.println("+---------------------------------------------------+");
            System.out.println("|                SUPPLIER MANAGEMENT                |");
            System.out.println("+---------------------------------------------------+\n");

            System.out.print("[1] Add Supplier");
            System.out.println("\t\t[2] Update Supplier");
            System.out.print("[3] Delete Supplier");
            System.out.println("\t\t[4] View Suppliers");
            System.out.print("[5] Search Supplier");
            System.out.println("\t\t[6] Back to Home\n");
            System.out.print("Enter option: ");

            int option = scanner.nextInt();
            scanner.nextLine(); 

            switch (option) {
                case 1:
                    addSupplier(scanner);
                    break;
                case 2:
                    updateSupplier(scanner);
                    break;
                case 3:
                    deleteSupplier(scanner);
                    break;
                case 4:
                    viewSuppliers();
                    break;
                case 5:
                    searchSupplier(scanner);
                    break;
                case 6:
                    return; // Returns to showHomePage loop
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void addSupplier(Scanner scanner) {
        System.out.println();
        System.out.println("+---------------------------------------------------+");
        System.out.println("|                    ADD SUPPLIER                   |");
        System.out.println("+---------------------------------------------------+\n");
    
        while (true) {
            System.out.print("Enter Supplier ID: ");
            String supplierId = scanner.nextLine();

            // Check if Supplier ID already exists in the array
            for (int i = 0; i < supplierCount; i++) {
                if (suppliers[i][0] != null && suppliers[i][0].equals(supplierId)) {
                    System.out.println("Supplier ID already exists. Try again.");
                    return;
                }
            }

            System.out.print("Enter Supplier Name: ");
            String supplierName = scanner.nextLine();

            // Store new supplier data in the next available index
            suppliers[supplierCount][0] = supplierId;
            suppliers[supplierCount][1] = supplierName;
            supplierCount++; // Increment count

            System.out.println("Supplier added successfully.");
            viewSuppliers(); // Show list to confirm addition

            // Logic to repeat the action
            System.out.print("\nDo you want to add another supplier? (y/n): ");
            String response;
            while (true) {
                response = scanner.nextLine();
                if (response.equals("y") || response.equals("n")) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter 'y' or 'n'.");
                    System.out.print("\nDo you want to add another supplier? (y/n): ");
                }
            }
            
            // If 'n', break the loop and return to menu
            if (response.equals("y")) {
                // loop continues
            } else {
                break;
            }
        }
    }

    private static void updateSupplier(Scanner scanner) {
        System.out.println();
        System.out.println("+---------------------------------------------------+");
        System.out.println("|                  UPDATE SUPPLIER                  |");
        System.out.println("+---------------------------------------------------+\n");
        
        boolean updateAnotherSupplier = true;
        while (updateAnotherSupplier) {
            System.out.print("Enter Supplier ID to update: ");
            String supplierId = scanner.nextLine();

            // Search for the index of the supplier ID
            int index = -1;
            for (int i = 0; i < supplierCount; i++) {
                if (suppliers[i][0] != null && suppliers[i][0].equals(supplierId)) {
                    index = i;
                    break;
                }
            }

            // If index remains -1, ID was not found
            if (index == -1) {
                System.out.println("Supplier ID not found. Try again.");
                return;
            }

            System.out.print("Enter new Supplier Name: ");
            String newSupplierName = scanner.nextLine();
            
            // Update the name at the found index
            suppliers[index][1] = newSupplierName;

            System.out.println("Supplier updated successfully.");
            viewSuppliers();

            // Logic to repeat
            System.out.print("\nDo you want to add another supplier? (y/n): "); // Typo in prompt text "add", logic is update
            String response;
            while (true) {
                response = scanner.nextLine();
                if (response.equals("y") || response.equals("n")) {
                    break;
                } else {
                    System.out.println("\nInvalid input. Please enter 'y' or 'n'.");
                    System.out.print("Do you want to add another supplier? (y/n): ");
                }
            }
            
            if (response.equals("y")) {
                // loop continues
            } else {
                break;
            }
        }
    }

    private static void deleteSupplier(Scanner scanner) {
        System.out.println();
        System.out.println("+---------------------------------------------------+");
        System.out.println("|                  DELETE SUPPLIER                  |");
        System.out.println("+---------------------------------------------------+\n");
        
        System.out.print("Enter Supplier ID to delete: ");
        String supplierId = scanner.nextLine();

        // Find the index to delete
        int index = -1;
        for (int i = 0; i < supplierCount; i++) {
            if (suppliers[i][0] != null && suppliers[i][0].equals(supplierId)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("Supplier ID not found. Try again.");
            return;
        }

        // Shift elements to the left to fill the gap left by deletion
        for (int i = index; i < supplierCount - 1; i++) {
            suppliers[i][0] = suppliers[i + 1][0];
            suppliers[i][1] = suppliers[i + 1][1];
        }
        
        // Nullify the last element (which is now a duplicate after shifting)
        suppliers[supplierCount - 1][0] = null;
        suppliers[supplierCount - 1][1] = null;
        supplierCount--; // Decrease count

        System.out.println("Supplier deleted successfully.");
        viewSuppliers();
    
        // Logic to repeat deletion
        System.out.print("\nDo you want to delete another supplier? (y/n): ");
        String response;
        while (true) {
            response = scanner.nextLine();
            if (response.equals("y") || response.equals("n")) {
                break;
            } else {
                System.out.println("\nInvalid input. Please enter 'y' or 'n'.");
                System.out.print("Do you want to delete another supplier? (y/n): ");
            }
        }
    
        if (response.equals("y")) {
            // This logic block is empty in original code, so it might fall through or need recursive call if inside a loop
            // In current structure, it exits the method because there is no outer loop in deleteSupplier
        } else {
            return;
        }
    }

    private static void viewSuppliers() {
        System.out.println();
        System.out.println("+---------------------------------------------------+");
        System.out.println("|                    VIEW SUPPLIERS                 |");
        System.out.println("+---------------------------------------------------+");
        
        System.out.println("\nSuppliers List:");
        
        // Table Header
        System.out.printf("+-----------------------+---------------------------+%n");
        System.out.printf("|   Supplier Id         |     Supplier Name         |%n");
        System.out.printf("+-----------------------+---------------------------+%n");
       
        // Loop through array and print valid entries
        for (int i = 0; i < supplierCount; i++) {
            if (suppliers[i][0] != null) {
                System.out.printf("| %-21s | %-25s |%n",suppliers[i][0], suppliers[i][1]);
            }
        }
        System.out.printf("+-----------------------+---------------------------+%n");
    }

    private static void searchSupplier(Scanner scanner) {
        System.out.println();
        System.out.println("+---------------------------------------------------+");
        System.out.println("|                  SEARCH SUPPLIER                  |");
        System.out.println("+---------------------------------------------------+\n");
                
        while (true) {
            System.out.print("Enter Supplier ID to search: ");
            String supplierId = scanner.nextLine();
            
            System.out.println("");
            System.out.printf("+-----------------------+---------------------------+%n");
            System.out.printf("|   Supplier Id         |     Supplier Name         |%n");
            System.out.printf("+-----------------------+---------------------------+%n");

            // Linear search for the ID
            boolean found = false;
            for (int i = 0; i < supplierCount; i++) {
                if (suppliers[i][0] != null && suppliers[i][0].equals(supplierId)) {
                    System.out.printf("| %-21s | %-25s |%n",suppliers[i][0], suppliers[i][1]);
                    found = true;
                    break;
                }
            }
            System.out.printf("+-----------------------+---------------------------+%n");
    
            if (!found) {
                System.out.println("Supplier ID not found.");
            }

            // Logic to repeat search
            System.out.print("\nDo you want to search another supplier? (y/n): ");
            String response;
            while (true) {
                response = scanner.nextLine();
                if (response.equals("y") || response.equals("n")) {
                    break;
                } else {
                    System.out.println("\nInvalid input. Please enter 'y' or 'n'.");
                    System.out.print("Do you want to search another supplier? (y/n): ");
                }
            }
    
            if (response.equals("y")) {
                // continues loop
            } else {
                return;
            }
        }
    }

    // ---------------------------------------------------------
    // STOCK MANAGEMENT MENU (Categories & Items)
    // ---------------------------------------------------------

    private static void showStockManageMenu(Scanner scanner) {
        while (true) {
            System.out.println();
            System.out.println("+---------------------------------------------------+");
            System.out.println("|                    STOCK MANAGEMENT               |");
            System.out.println("+---------------------------------------------------+\n");

            System.out.print("[1] Item Category Manage");
            System.out.println("\t[2] Item Manage");
            System.out.println("[3] Back to Home\n");
            System.out.print("Enter option: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    showCategoryManageMenu(scanner); // Manage Categories
                    break;
                case 2:
                    showItemManageMenu(scanner); // Manage Items
                    break;
                case 3:
                    return; // Go back to Home
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    // ---------------------------------------------------------
    // CATEGORY MANAGEMENT
    // ---------------------------------------------------------

    private static void showCategoryManageMenu(Scanner scanner) {
        while (true) {
            System.out.println();
            System.out.println("+---------------------------------------------------+");
            System.out.println("|                ITEM CATEGORY MANAGEMENT           |");
            System.out.println("+---------------------------------------------------+\n");

            System.out.print("[1] Add Category");
            System.out.println("\t\t[2] Update Category");
            System.out.print("[3] Delete Category");
            System.out.println("\t\t[4] View Categories");
            System.out.println("[5] Back to Stock Management\n");
            System.out.print("Enter option: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    addCategory(scanner);
                    break;
                case 2:
                    updateCategory(scanner);
                    break;
                case 3:
                    deleteCategory(scanner);
                    break;
                case 4:
                    viewCategories();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void addCategory(Scanner scanner) {
        System.out.println();
        System.out.println("+---------------------------------------------------+");
        System.out.println("|                    ADD CATEGORY                   |");
        System.out.println("+---------------------------------------------------+\n");
        
        while (true) {
            System.out.print("Enter Category ID: ");
            String categoryId = scanner.nextLine();

            // Check duplicate Category ID
            for (int i = 0; i < categoryCount; i++) {
                if (categoryIds[i] != null && categoryIds[i].equals(categoryId)) {
                    System.out.println("Category ID already exists. Try again.");
                    return;
                }
            }

            System.out.print("Enter Category Name: ");
            String categoryName = scanner.nextLine();

            // Save to arrays
            categoryIds[categoryCount] = categoryId;
            categoryNames[categoryCount] = categoryName;
            categoryCount++;

            System.out.println("Category added successfully.");
            viewCategories();

            // Repeat logic
            System.out.print("\nDo you want to add another category? (y/n): ");
            String response;
            while (true) {
                response = scanner.nextLine();
                if (response.equals("y") || response.equals("n")) {
                    break;
                } else {
                    System.out.println("\nInvalid input. Please enter 'y' or 'n'.");
                    System.out.print("Do you want to add another category? (y/n): ");
                }
            }
    
            if (response.equals("y")) {
            } else {
                return;
            }
        }
    }

    private static void updateCategory(Scanner scanner) {
        System.out.println();
        System.out.println("+---------------------------------------------------+");
        System.out.println("|                    UPDATE CATEGORY                |");
        System.out.println("+---------------------------------------------------+\n");
        
        while (true) {
            System.out.print("Enter Category ID to update: ");
            String categoryId = scanner.nextLine();

            // Find index
            int index = -1;
            for (int i = 0; i < categoryCount; i++) {
                if (categoryIds[i] != null && categoryIds[i].equals(categoryId)) {
                    index = i;
                    break;
                }
            }

            if (index == -1) {
                System.out.println("Category ID not found. Try again.");
                return;
            }

            System.out.print("Enter new Category Name: ");
            String newCategoryName = scanner.nextLine();
            categoryNames[index] = newCategoryName;

            System.out.println("Category updated successfully.");
            viewCategories();

            // Repeat logic
            System.out.print("\nDo you want to update another category? (y/n): ");
            String response;
            while (true) {
                response = scanner.nextLine();
                if (response.equals("y") || response.equals("n")) {
                    break;
                } else {
                    System.out.println("\nInvalid input. Please enter 'y' or 'n'.");
                    System.out.print("Do you want to update another category? (y/n): ");
                }
            }
    
            if (response.equals("y")) {
            } else {
                return;
            }
        }
    }

    private static void deleteCategory(Scanner scanner) {
        System.out.println();
        System.out.println("+---------------------------------------------------+");
        System.out.println("|                    DELETE CATEGORY                |");
        System.out.println("+---------------------------------------------------+\n");
        
        while (true) {
            System.out.print("Enter Category ID to delete: ");
            String categoryId = scanner.nextLine();

            // Find index
            int index = -1;
            for (int i = 0; i < categoryCount; i++) {
                if (categoryIds[i] != null && categoryIds[i].equals(categoryId)) {
                    index = i;
                    break;
                }
            }

            if (index == -1) {
                System.out.println("Category ID not found. Try again.");
                return;
            }

            // Shift both ID and Name arrays to the left
            for (int i = index; i < categoryCount - 1; i++) {
                categoryIds[i] = categoryIds[i + 1];
                categoryNames[i] = categoryNames[i + 1];
            }
            categoryIds[categoryCount - 1] = null;
            categoryNames[categoryCount - 1] = null;
            categoryCount--;

            System.out.println("Category deleted successfully.");
            viewCategories();

            // Repeat logic
            System.out.print("\nDo you want to delete another category? (y/n): ");
            String response;
            while (true) {
                response = scanner.nextLine();
                if (response.equals("y") || response.equals("n")) {
                    break; 
                } else {
                    System.out.println("\nInvalid input. Please enter 'y' or 'n'.");
                    System.out.print("Do you want to delete another category? (y/n): ");
                }
            }
        
            if (response.equals("y")) {
            } else {
                return;
            }
        }
    }

    private static void viewCategories() {
        System.out.println();
        System.out.println("+---------------------------------------------------+");
        System.out.println("|                   VIEW CATEGORIES                 |");
        System.out.println("+---------------------------------------------------+");       
        
        System.out.println("\nCategories List:");
        
        System.out.printf("+-----------------------+---------------------------+%n");
        System.out.printf("|   Category Id         |     Category Name         |%n");
        System.out.printf("+-----------------------+---------------------------+%n");        
        
        for (int i = 0; i < categoryCount; i++) {
            if (categoryIds[i] != null) {
                System.out.printf("| %-21s | %-25s |%n", categoryIds[i], categoryNames[i]);
            }
        }
        System.out.printf("+-----------------------+---------------------------+%n");
    }

    // ---------------------------------------------------------
    // ITEM MANAGEMENT
    // ---------------------------------------------------------

    private static void showItemManageMenu(Scanner scanner) {
        while (true) {
            System.out.println();
            System.out.println("+---------------------------------------------------+");
            System.out.println("|                    ITEM MANAGEMENT                |");
            System.out.println("+---------------------------------------------------+\n");

            System.out.print("[1] Add Item");
            System.out.println("\t\t[2] Update Item");
            System.out.print("[3] Delete Item");
            System.out.println("\t\t[4] View Items");
            System.out.print("[5] Rank Items by Price");
            System.out.println("\t[6] Back to Stock Management\n");
            System.out.print("Enter option: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    addItem(scanner);
                    break;
                case 2:
                    updateItem(scanner);
                    break;
                case 3:
                    deleteItem(scanner);
                    break;
                case 4:
                    viewItems();
                    break;
                case 5:
                    rankItemsByPrice();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void addItem(Scanner scanner) {
        System.out.println();
        System.out.println("+---------------------------------------------------+");
        System.out.println("|                       ADD ITEM                    |");
        System.out.println("+---------------------------------------------------+\n");
        
        while (true) {
            System.out.print("Enter Item ID: ");
            String itemId = scanner.nextLine();

            // Check duplicate Item ID
            for (int i = 0; i < itemCount; i++) {
                if (items[i][0] != null && items[i][0].equals(itemId)) {
                    System.out.println("Item ID already exists. Try again.");
                    return;
                }
            }

            System.out.print("Enter Item Name: ");
            String itemName = scanner.nextLine();

            System.out.print("Enter Item Category ID: ");
            String itemCategoryId = scanner.nextLine();

            // Check if the entered Category ID actually exists in the categories array
            boolean validCategory = false;
            for (int i = 0; i < categoryCount; i++) {
                if (categoryIds[i] != null && categoryIds[i].equals(itemCategoryId)) {
                    validCategory = true;
                    break;
                }
            }
            if (!validCategory) {
                System.out.println("Invalid Category ID. Try again.");
                return;
            }

            System.out.print("Enter Item Price: ");
            double itemPrice = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Enter Quantity: ");
            int itemQuantity = scanner.nextInt();
            scanner.nextLine(); 

            // Save details to the item array
            items[itemCount][0] = itemId;
            items[itemCount][1] = itemName;
            items[itemCount][2] = itemCategoryId;
            items[itemCount][3] = String.valueOf(itemPrice); // Convert double to string for storage
            items[itemCount][4] = String.valueOf(itemQuantity); // Convert int to string for storage
            itemCount++;

            System.out.println("Item added successfully.");
            viewItems();

            // Repeat logic
            System.out.print("Do you want to add another item? (y/n): ");
            String response;
            while (true) {
                response = scanner.nextLine();
                if (response.equals("y") || response.equals("n")) {
                    break; 
                } else {
                    System.out.println("\nInvalid input. Please enter 'y' or 'n'.");
                    System.out.print("Do you want to add another item? (y/n): ");
                }
            }
    
            if (response.equals("y")) {
            } else {
                return;
            }
        }
    }

    private static void updateItem(Scanner scanner) {
        System.out.println();
        System.out.println("+---------------------------------------------------+");
        System.out.println("|                     UPDATE ITEM                   |");
        System.out.println("+---------------------------------------------------+\n");       
        
        while (true) {
            System.out.print("Enter Item ID to update: ");
            String itemId = scanner.nextLine();

            // Find item index
            int index = -1;
            for (int i = 0; i < itemCount; i++) {
                if (items[i][0] != null && items[i][0].equals(itemId)) {
                    index = i;
                    break;
                }
            }

            if (index == -1) {
                System.out.println("Item ID not found. Try again.");
                return;
            }

            System.out.print("Enter new Item Name: ");
            String newItemName = scanner.nextLine();
            items[index][1] = newItemName;

            System.out.print("Enter new Item Category ID: ");
            String newItemCategoryId = scanner.nextLine();

            // Validate new Category ID
            boolean validCategory = false;
            for (int i = 0; i < categoryCount; i++) {
                if (categoryIds[i] != null && categoryIds[i].equals(newItemCategoryId)) {
                    validCategory = true;
                    break;
                }
            }
            if (!validCategory) {
                System.out.println("Invalid Category ID. Try again.");
                return;
            }

            items[index][2] = newItemCategoryId;

            System.out.print("Enter new Item Price: ");
            double newItemPrice = scanner.nextDouble();
            scanner.nextLine();
            items[index][3] = String.valueOf(newItemPrice);

            System.out.println("Item updated successfully.");
            viewItems();

            // Repeat logic
            System.out.print("Do you want to update another item? (y/n): ");
            String response;
            while (true) {
                response = scanner.nextLine();
                if (response.equals("y") || response.equals("n")) {
                    break; 
                } else {
                    System.out.println("\nInvalid input. Please enter 'y' or 'n'.");
                    System.out.print("Do you want to update another item? (y/n): ");
                }
            }
    
            if (response.equals("y")) {
            } else {
                return;
            }
        }
    }

    private static void deleteItem(Scanner scanner) {
        System.out.println();
        System.out.println("+---------------------------------------------------+");
        System.out.println("|                     DELETE ITEM                   |");
        System.out.println("+---------------------------------------------------+\n");       
        
        while (true) {
            System.out.print("Enter Item ID to delete: ");
            String itemId = scanner.nextLine();

            // Find index
            int index = -1;
            for (int i = 0; i < itemCount; i++) {
                if (items[i][0] != null && items[i][0].equals(itemId)) {
                    index = i;
                    break;
                }
            }

            if (index == -1) {
                System.out.println("Item ID not found. Try again.");
                return;
            }

            // Shift all properties of the item array to the left
            for (int i = index; i < itemCount - 1; i++) {
                items[i][0] = items[i + 1][0];
                items[i][1] = items[i + 1][1];
                items[i][2] = items[i + 1][2];
                items[i][3] = items[i + 1][3];
            }
    
            // Nullify last element
            items[itemCount - 1][0] = null;
            items[itemCount - 1][1] = null;
            items[itemCount - 1][2] = null;
            items[itemCount - 1][3] = null;
            itemCount--;

            System.out.println("Item deleted successfully.");
            viewItems();

            // Repeat logic
            System.out.print("Do you want to delete another item? (y/n): ");
            String response;
            while (true) {
                response = scanner.nextLine();
                if (response.equals("y") || response.equals("n")) {
                    break;
                } else {
                    System.out.println("\nInvalid input. Please enter 'y' or 'n'.");
                    System.out.print("Do you want to delete another item? (y/n): ");
                }
            }
    
            if (response.equals("y")) {
            } else {
                return;
            }
        }
    }

    private static void viewItems() {
        System.out.println();
        System.out.println("+---------------------------------------------------+");
        System.out.println("|                       VIEW ITEMS                  |");
        System.out.println("+---------------------------------------------------+");            
        
        System.out.println("\nItems List:");
                
        System.out.printf("+-----------------------+---------------------------+%n");
        System.out.printf("| Item Id | Item Name  |Category ID|Price(Rs.) |Qty |%n");
        System.out.printf("+-----------------------+---------------------------+%n");
            
        for (int i = 0; i < itemCount; i++) {
            if (items[i][0] != null) {
                System.out.printf("|%-9s|%-12s|%-10s|%-11s|%-5s|%n", items[i][0], items[i][1], items[i][2], items[i][3], items[i][4]);
            }
            // Note: This prints the separator line after every item, which might look cluttered
            System.out.printf("+-----------------------+---------------------------+%n");
        }
    }
    
    // Sorts items using Bubble Sort logic based on Price
    private static void rankItemsByPrice() {
        for (int i = 0; i < itemCount - 1; i++) {
            for (int j = i + 1; j < itemCount; j++) {
                // Parse price strings to double for numerical comparison
                if (items[i][0] != null && items[j][0] != null && Double.parseDouble(items[i][3]) > Double.parseDouble(items[j][3])) {
                    // Swap the entire row (string array) if the first item is more expensive than the second
                    String[] temp = items[i];
                    items[i] = items[j];
                    items[j] = temp;
                }
            }
        }

        System.out.println("\nItems ranked by price:");
        viewItems(); // Display sorted list
    }
    
    // ---------------------------------------------------------
    // MAIN ENTRY POINT
    // ---------------------------------------------------------

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("+---------------------------------------------------+");
        System.out.println("|                       LOGIN PAGE                  |");
        System.out.println("+---------------------------------------------------+\n");

        // Loop for Login Authentication
        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.print("Username: ");
            String inputUsername = scanner.nextLine();
            // Using custom isEqual method
            if (!isEqual(inputUsername, USERNAME)) {
                System.out.println("Invalid username. Please Try again.");
                continue;
            }

            System.out.print("Password: ");
            String inputPassword = scanner.nextLine();
            // Using custom isEqual method
            if (!isEqual(inputPassword, PASSWORD)) {
                System.out.println("Invalid password. Please Try again.");
            } else {
                loggedIn = true; // Exit loop if credentials match
            }
        }

        System.out.println("Login successful!");

        // Start the application flow
        showHomePage(scanner);
    }
    
    
    // Helper method to clear console screen (Implementation depends on OS)
    private final static void clearConsole() {
        final String os = System.getProperty("os.name");
        try {
            if (os.equals("Linux")) {
                // ANSI escape code for Linux
                System.out.print("\033\143");
            } else if (os.equals("Windows")) {
                // Execute CMD command for Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Standard ANSI escape for other systems
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }  
        } catch (final Exception e) {
            // Handle any IO exceptions during clearing
            System.err.println(e.getMessage());
        }
    }
}