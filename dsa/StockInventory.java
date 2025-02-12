/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dsa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author bacjssemillano
 */
public class StockInventory {
    
    //CSV_File = location of your CSV, you can look at in your file properties
    private static final String CSV_File = "C:\\Users\\bacjssemillano\\Documents\\NetBeansProjects\\DSA\\src\\dsa\\motorph.csv";
    private static List<Stock> stockList = new ArrayList<>();

    public static void main(String[] args) {
        loadcsv();
        
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("===== Stock Inventory Management =====");
            System.out.println("1. Add new stock");
            System.out.println("2. Delete incorrect stock");
            System.out.println("3. Sort stocks by brand");
            System.out.println("4. Search for stock by brand or Engine Number");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            
                switch (choice) {
                  
                case 1:
                    addNewStock(scanner);
                    break;
                case 2:
                    deleteStock(scanner);
                    break;
                case 3:
                    sortStocks();
                    break;
                case 4:
                    searchStock(scanner);
                    break;
                case 5:
                    saveCSV();
                    System.out.println("Exiting... Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
           
    }
    
        private static void loadcsv() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_File))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) { // Ensure 5 columns
                    String DateEntered = data[0];
                    String StockLabel = data[1];
                    String Brand = data[2];
                    String EngineNumber = data[3];
                    String Status = data[4];
                    stockList.add(new Stock(DateEntered,StockLabel, Brand, EngineNumber,Status));
                    System.out.println( DateEntered + " " + StockLabel + " " + Brand + " " + EngineNumber + " " + Status + " ");
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the CSV file.");
        }
    }

    private static void addNewStock(Scanner scanner) {
        while (true) {
        System.out.print("Enter Date: ");
            String DateEntered = scanner.nextLine();
        System.out.print("Enter Stock Label: ");
            String StockLabel = scanner.nextLine();
        System.out.print("Enter Brand: ");
            String Brand = scanner.nextLine();
        System.out.print("Enter EngineNumber: ");
            String EngineNumber = scanner.nextLine();
        System.out.print("Enter Status: ");
            String Status = scanner.nextLine();
        scanner.nextLine();  // Consume newline
        
        stockList.add(new Stock(DateEntered,StockLabel, Brand, EngineNumber,Status));
        System.out.println("Stock added successfully.");
        
        // asking the user add another stock, the purpose of this is to stay so that you cant go back to the main menu       
        System.out.print("Do you want to add another stock? (y/n): ");
        char again = scanner.nextLine().charAt(0);
        if (again == 'n' || again == 'N') {
            break;  // Exit the loop and go back to the main menu
        }
        
        }
    }
    

    private static void deleteStock(Scanner scanner) {
        while (true){
        System.out.print("Enter Engine Number to delete: ");
        String EngineNumber = scanner.nextLine();
        
        Stock stockToDelete = null;
        for (Stock stock : stockList) {
            if (stock.getEngineNumber().equals(EngineNumber)) {
                stockToDelete = stock;
                break;
            }
        }
        
        if (stockToDelete != null) {
            stockList.remove(stockToDelete);
            System.out.println("Stock Label " + EngineNumber + " deleted.");
        } else {
            System.out.println("Stock not found.");
        }
        
        // asking the user delete another stock, the purpose of this is to stay so that you cant go back to the main menu
        System.out.print("Do you want to add delete stock? (y/n): ");
        char again = scanner.nextLine().charAt(0);
        if (again == 'n' || again == 'N') {
            break;  // Exit the loop and go back to the main menu
        }
        
        }  
    }

    private static void sortStocks() {
      
        while (true){
    Scanner scanner = new Scanner(System.in);
         
    System.out.println("Choose sorting criteria:");
    System.out.println("1. Sort by StockLabel");
    System.out.println("2. Sort by Brand");
    System.out.println("3. Sort by EngineNumber");
    System.out.println("4. Sort by DateEntered");
    System.out.println("5. Sort by Status");
    System.out.print("Enter your choice: ");
    int choice = scanner.nextInt();
    scanner.nextLine();  // Consume newline

    switch (choice) {
        case 1:
            stockList.sort(Comparator.comparing(Stock::getStockLabel));
            System.out.println("Stocks sorted by Stock Label:");
            break;
        case 2:
            stockList.sort(Comparator.comparing(Stock::getBrand));
            System.out.println("Stocks sorted by Brand:");
            break;
        case 3:
            stockList.sort(Comparator.comparing(Stock::getEngineNumber));
            System.out.println("Stocks sorted by Engine Number:");
            break;
        case 4:
            stockList.sort(Comparator.comparing(Stock::getDateEntered));
            System.out.println("Stocks sorted by Date Entered:");
            break;
        case 5:
            stockList.sort(Comparator.comparing(Stock::getStatus));
            System.out.println("Stocks sorted by Status:");
            break;
        default:
            System.out.println("Invalid choice. Please try again.");
            return;
    }

    // Print sorted list of stocks
    for (Stock stock : stockList) {
        System.out.println(stock);
    }
    // asking the user sort another stock, the purpose of this is to stay so that you cant go back to the main menu
        System.out.print("Do you want to sort another stock? (y/n): ");
        char again = scanner.nextLine().charAt(0);
        if (again == 'n' || again == 'N') {
            break;  // Exit the loop and go back to the main menu
        }
        
        }
    }

    private static void searchStock(Scanner scanner) {
        while (true) {
        System.out.println("Search by: ");
        System.out.println("1. Brand");
        System.out.println("2. Engine Number");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        
        if (choice == 1) {
            System.out.print("Enter brand to search for: ");
            String brand = scanner.nextLine();
            boolean found = false;
            for (Stock stock : stockList) {
                if (stock.getBrand().equalsIgnoreCase(brand)) {
                    System.out.println(stock);
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No stock found for brand: " + brand);
            }
        } else if (choice == 2) {
            System.out.print("Enter Engine Number to search for: ");
            String EngineNumber = scanner.nextLine();
            boolean found = false;
            for (Stock stock : stockList) {
                if (stock.getEngineNumber().equals(EngineNumber)) {
                    System.out.println(stock);
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No stock found for ID: " + EngineNumber);
            }
        } else {
            System.out.println("Invalid choice.");
        }
        
        // asking the user search another stock, the purpose of this is to stay so that you cant go back to the main menu
        System.out.print("Do you want to search another stock? (y/n): ");
        char again = scanner.nextLine().charAt(0);
        if (again == 'n' || again == 'N') {
            break;  // Exit the loop and go back to the main menu
        }
        
        }
    }

    private static void saveCSV() {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_File))) {
            for (Stock stock : stockList) {
                writer.write(stock.getDateEntered() + ","+ stock.getStockLabel() + "," + stock.getBrand() + "," + stock.getEngineNumber() + "," + stock.getStatus());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving the CSV file.");
        }
    }
    
        static class Stock {
        private String DateEntered;
        private String StockLabel;
        private String Brand;
        private String EngineNumber;
        private String Status;
        
        public Stock(String DateEntered, String StockLabel, String Brand, String EngineNumber, String Status) {
            this.DateEntered = DateEntered;
            this.StockLabel = StockLabel;
            this.Brand = Brand;
            this.EngineNumber = EngineNumber;
            this.Status = Status;
        }

        public String getDateEntered() {
            return DateEntered;
        }

        public String getStockLabel() {
            return StockLabel;
        }

        public String getBrand() {
            return Brand;
        }
        
        public String getEngineNumber() {
            return EngineNumber;
        }
        
        public String getStatus() {
            return Status;
        }

        @Override
        public String toString() {
            return "Date Entered: " + DateEntered + ", Stock Label: " + StockLabel + ", Brand: " + Brand + ", EngineNumber: " + EngineNumber + ",Status: " + Status;
        }
    }
    
    
}
