import java.util.*;

public class StockManagementSystem {
    //credentials
    private static final String USERNAME = "sumu356";
    private static final String PASSWORD = "356";

    //suplier data
    private static final int SUPPLIER_DETAILS = 2; //id, name
    private static String[][] suppliers = new String[100][SUPPLIER_DETAILS];
    private static int supplierCount = 0;

    //item data
    private static final int ITEM_DETAILS = 5; //id, name, category id, price, quantity
    private static String[][] items = new String[200][ITEM_DETAILS];
    private static int itemCount = 0;

    //item category data
    private static String[] categoryIds = new String[50];
    private static String[] categoryNames = new String[50];
    private static int categoryCount = 0;

    // method to compare two strings 
    private static boolean isEqual(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }

        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    private static void showHomePage(Scanner scanner) {
        //clearConsole(); 
        //didn't work for my win11 terminal.
        while (true) {
            System.out.println();
            System.out.println("+---------------------------------------------------+");
            System.out.println("| WELCOME TO PIGEON SERVICE STOCK MANAGEMENT SYSTEM |");
            System.out.println("+---------------------------------------------------+\n");


            System.out.print("[1] Change Credentials");
            System.out.println("\t\t[2] Supplier Manage");
            System.out.print("[3] Stock Manage");
            System.out.println("\t\t[4] Logout");
            System.out.println("[5] Exit\n");
            System.out.print("Enter option: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    changeCredentials(scanner);
                    break;
                case 2:
                    showSupplierManageMenu(scanner);
                    break;
                case 3:
                    showStockManageMenu(scanner);
                    break;
                case 4:
                    System.out.println("Logged out.\n");
                    return;
                case 5:
                    System.out.println("Exiting system.\n");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Try again.\n");
            }
        }

    }

    private static void changeCredentials(Scanner scanner) {

        System.out.println();
        System.out.println("+---------------------------------------------------+");
        System.out.println("|                 CHANGE CREDENTIALS                |");
        System.out.println("+---------------------------------------------------+\n");
        System.out.print("Enter current username to verify it's you: ");
        String currentUsername = scanner.nextLine();
        if (!currentUsername.equals(USERNAME)) {
            System.out.println("Invalid username. Try again.");
            return;
        }

        System.out.println("Hello " + USERNAME);

        System.out.print("Enter current password: ");
        String currentPassword = scanner.nextLine();
        if (!currentPassword.equals(PASSWORD)) {
            System.out.println("Invalid password. Try again.");
            return;
        }

        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();

        System.out.println("Password changed successfully.");
    }

    private static void showSupplierManageMenu(Scanner scanner) {
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
                    return; // Back to Home
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

            for (int i = 0; i < supplierCount; i++) {
                if (suppliers[i][0] != null && suppliers[i][0].equals(supplierId)) {
                    System.out.println("Supplier ID already exists. Try again.");
                    return;
                }
            }

            System.out.print("Enter Supplier Name: ");
            String supplierName = scanner.nextLine();

            suppliers[supplierCount][0] = supplierId;
            suppliers[supplierCount][1] = supplierName;
            supplierCount++;

            System.out.println("Supplier added successfully.");
            viewSuppliers();

		//asikng for another one
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
				
				if (response.equals("y")) {
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

			System.out.print("Enter new Supplier Name: ");
			String newSupplierName = scanner.nextLine();
			suppliers[index][1] = newSupplierName;

			System.out.println("Supplier updated successfully.");
			viewSuppliers();

			//asikng for another one
			System.out.print("\nDo you want to add another supplier? (y/n): ");
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

		for (int i = index; i < supplierCount - 1; i++) {
			suppliers[i][0] = suppliers[i + 1][0];
			suppliers[i][1] = suppliers[i + 1][1];
		}
		suppliers[supplierCount - 1][0] = null;
		suppliers[supplierCount - 1][1] = null;
		supplierCount--;

		System.out.println("Supplier deleted successfully.");
		viewSuppliers();
    
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
		} else {
			return;
		}
	}


    private static void viewSuppliers() {
		
		System.out.println();
		System.out.println("+---------------------------------------------------+");
		System.out.println("|                   VIEW SUPPLIERS                  |");
		System.out.println("+---------------------------------------------------+");
		
        System.out.println("\nSuppliers List:");
        
        System.out.printf("+-----------------------+---------------------------+%n");
		System.out.printf("|  Supplier Id          |    Supplier Name          |%n");
		System.out.printf("+-----------------------+---------------------------+%n");
       
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
			System.out.printf("|  Supplier Id          |    Supplier Name          |%n");
			System.out.printf("+-----------------------+---------------------------+%n");


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
        } else {
            return;
        }
    }
}


    private static void showStockManageMenu(Scanner scanner) {
        while (true) {
            System.out.println();
            System.out.println("+---------------------------------------------------+");
            System.out.println("|                   STOCK MANAGEMENT                |");
            System.out.println("+---------------------------------------------------+\n");

            System.out.print("[1] Item Category Manage");
            System.out.println("\t[2] Item Manage");
            System.out.println("[3] Back to Home\n");
            System.out.print("Enter option: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    showCategoryManageMenu(scanner);
                    break;
                case 2:
                    showItemManageMenu(scanner);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void showCategoryManageMenu(Scanner scanner) {
        while (true) {
            System.out.println();
            System.out.println("+---------------------------------------------------+");
            System.out.println("|               ITEM CATEGORY MANAGEMENT            |");
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
		System.out.println("|                   ADD CATEGORY                    |");
		System.out.println("+---------------------------------------------------+\n");
		
		while (true) {
			System.out.print("Enter Category ID: ");
			String categoryId = scanner.nextLine();

			for (int i = 0; i < categoryCount; i++) {
				if (categoryIds[i] != null && categoryIds[i].equals(categoryId)) {
					System.out.println("Category ID already exists. Try again.");
					return;
				}
			}

			System.out.print("Enter Category Name: ");
			String categoryName = scanner.nextLine();

			categoryIds[categoryCount] = categoryId;
			categoryNames[categoryCount] = categoryName;
			categoryCount++;

			System.out.println("Category added successfully.");
			viewCategories();

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
		System.out.println("|                   UPDATE CATEGORY                 |");
		System.out.println("+---------------------------------------------------+\n");
		
		while (true) {
			System.out.print("Enter Category ID to update: ");
			String categoryId = scanner.nextLine();

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
		System.out.println("|                   DELETE CATEGORY                 |");
		System.out.println("+---------------------------------------------------+\n");
		
		while (true) {
			System.out.print("Enter Category ID to delete: ");
			String categoryId = scanner.nextLine();

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

			for (int i = index; i < categoryCount - 1; i++) {
				categoryIds[i] = categoryIds[i + 1];
				categoryNames[i] = categoryNames[i + 1];
			}
			categoryIds[categoryCount - 1] = null;
			categoryNames[categoryCount - 1] = null;
			categoryCount--;

			System.out.println("Category deleted successfully.");
			viewCategories();

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
		System.out.println("|                  VIEW CATEGORIES                  |");
		System.out.println("+---------------------------------------------------+");		
		
        System.out.println("\nCategories List:");
        
        System.out.printf("+-----------------------+---------------------------+%n");
		System.out.printf("|  Category Id          |    Category Name          |%n");
		System.out.printf("+-----------------------+---------------------------+%n");        
        
        for (int i = 0; i < categoryCount; i++) {
            if (categoryIds[i] != null) {
                System.out.printf("| %-21s | %-25s |%n", categoryIds[i], categoryNames[i]);
            }
        }
        System.out.printf("+-----------------------+---------------------------+%n");
    }

    private static void showItemManageMenu(Scanner scanner) {
        while (true) {
            System.out.println();
            System.out.println("+---------------------------------------------------+");
            System.out.println("|                   ITEM MANAGEMENT                 |");
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
		System.out.println("|                     ADD ITEM                      |");
		System.out.println("+---------------------------------------------------+\n");
		
		while (true) {
			System.out.print("Enter Item ID: ");
			String itemId = scanner.nextLine();

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

			items[itemCount][0] = itemId;
			items[itemCount][1] = itemName;
			items[itemCount][2] = itemCategoryId;
			items[itemCount][3] = String.valueOf(itemPrice);
			items[itemCount][4] = String.valueOf(itemQuantity);
			itemCount++;

			System.out.println("Item added successfully.");
			viewItems();

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
		System.out.println("|                    UPDATE ITEM                    |");
		System.out.println("+---------------------------------------------------+\n");		
		
		while (true) {
			System.out.print("Enter Item ID to update: ");
			String itemId = scanner.nextLine();

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
		System.out.println("|                    DELETE ITEM                    |");
		System.out.println("+---------------------------------------------------+\n");		
		
		while (true) {
			System.out.print("Enter Item ID to delete: ");
			String itemId = scanner.nextLine();

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

			for (int i = index; i < itemCount - 1; i++) {
				items[i][0] = items[i + 1][0];
				items[i][1] = items[i + 1][1];
				items[i][2] = items[i + 1][2];
				items[i][3] = items[i + 1][3];
			}
        
			items[itemCount - 1][0] = null;
			items[itemCount - 1][1] = null;
			items[itemCount - 1][2] = null;
			items[itemCount - 1][3] = null;
			itemCount--;

			System.out.println("Item deleted successfully.");
			viewItems();

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
		System.out.println("|                     VIEW ITEMS                    |");
		System.out.println("+---------------------------------------------------+");			
		
        System.out.println("\nItems List:");
				
		System.out.printf("+-----------------------+---------------------------+%n");
		System.out.printf("| Item Id | Item Name  |Category ID|Price(Rs.) |Qty |%n");
		System.out.printf("+-----------------------+---------------------------+%n");
			
        for (int i = 0; i < itemCount; i++) {
            if (items[i][0] != null) {
				
				System.out.printf("|%-9s|%-12s|%-10s|%-11s|%-5s|%n", items[i][0], items[i][1], items[i][2], items[i][3], items[i][4]);
				
                
            }
            
            System.out.printf("+-----------------------+---------------------------+%n");
            
        }
    }
	
	private static void rankItemsByPrice() {
		for (int i = 0; i < itemCount - 1; i++) {
			for (int j = i + 1; j < itemCount; j++) {
				if (items[i][0] != null && items[j][0] != null && Double.parseDouble(items[i][3]) > Double.parseDouble(items[j][3])) {
					String[] temp = items[i];
					items[i] = items[j];
					items[j] = temp;
				}
			}
		}

		System.out.println("\nItems ranked by price:");
		viewItems();
	}
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("+---------------------------------------------------+");
        System.out.println("|                     LOGIN PAGE                    |");
        System.out.println("+---------------------------------------------------+\n");

        // login system
        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.print("Username: ");
            String inputUsername = scanner.nextLine();
            if (!isEqual(inputUsername, USERNAME)) {
                System.out.println("Invalid username. Please Try again.");
                continue;
            }

            System.out.print("Password: ");
            String inputPassword = scanner.nextLine();
            if (!isEqual(inputPassword, PASSWORD)) {
                System.out.println("Invalid password. Please Try again.");
            } else {
                loggedIn = true;
            }
        }

        System.out.println("Login successful!");

        showHomePage(scanner);
    }
    
    
     private final static void clearConsole() {
		final String os = System.getProperty("os.name");
		try {
			if (os.equals("Linux")) {
			System.out.print("\033\143");
		} else if (os.equals("Windows")) {
			 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		 }else{
			System.out.print("\033[H\033[2J");
			System.out.flush();
		}  } catch (final Exception e) {
			//handle the exception
			System.err.println(e.getMessage());
		}
	}

}
