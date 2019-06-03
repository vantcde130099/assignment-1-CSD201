/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import Entity.Customer;
import Entity.Ordering;
import Entity.Product;
import LinkedList.MyList;
import static Manager.Validate.checkInputLimited;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 *
 * @author thinh
 */
public class MenuManager {
    private static final String CUSTOMER_URL ="Customers.txt";
    private static final String PRODUCT_URL ="Products.txt";
    private static final String ORDER_URL ="Orders.txt";
    
    public static void loadFromFileCustomers(MyList<Customer> mlc){
        try {    
            File f = new File(CUSTOMER_URL);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr); //tới đây chắc tự biết rồi khỏi nói hỉ!
            
            String line;
            while((line = br.readLine()) != null){  //read line by line into the 'line'(string)
                String[] detailCus = line.split(":");                
                mlc.addToTail(new Customer(detailCus[0], detailCus[1], detailCus[2]));
            }
            System.err.println("Load successful!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//------------------------------------------------------------------------------
    public static void loadFromFileProducts(MyList<Product> mlp){
        try {    
            File f = new File(PRODUCT_URL);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            
            String line;
            while((line = br.readLine()) != null){
                String[] detailPro = line.split(":");                //this function have "ép kiểu"
                mlp.addToTail(new Product(detailPro[0], detailPro[1], Integer.parseInt(detailPro[2]),Integer.parseInt(detailPro[3]), Double.parseDouble(detailPro[4])));
            }
            System.err.println("Load successful!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//------------------------------------------------------------------------------    
    //print info customers
    public static void printCus(MyList<Customer> mc){
        mc.traverse();
    }
//------------------------------------------------------------------------------  
    //print info product
    public static void printPro(MyList<Product> ml){
        ml.traverse();
    }
//------------------------------------------------------------------------------  
    //add Customer to the end
    public  static void inputCusToTail(MyList<Customer> mc){
        System.out.print("Enter your code: ");
        String cCode = Validate.checkInputString();
        System.out.print("Enter your name: ");
        String cName = Validate.checkInputString();
        System.out.print("Enter your number phone: ");
        String cPhone =Validate.checkInputString();
        
        mc.addToTail(new Customer(cCode, cName, cPhone)); //it worked!
        
        System.err.println("Add successful!");
    }
//------------------------------------------------------------------------------ 
    //add Product to the end
    public static void inputProToTail(MyList<Product> mp){
        System.out.print("Enter your code: ");
        String pCode = Validate.checkInputString();
        System.out.print("Enter your product name: ");
        String pName = Validate.checkInputString();
        System.out.print("Enter quantity of product: ");
        int pQuan = Validate.checkInputInt();
        System.out.print("Enter number product was sale: ");
        int pSale = Validate.checkInputSaled(pQuan);
        System.out.print("Enter price: ");
        double pPrice = Validate.checkInputDouble();
        
        mp.addToTail(new Product(pCode, pName, pQuan, pSale, pPrice));  //it worked again!
        
        System.err.println("Add succcessful!");
    }
//------------------------------------------------------------------------------ 
    public static void saveToFile(MyList<Customer> mc ){
        try {
            

            File f = new File(CUSTOMER_URL);        /*Find how to solve it*/
            FileWriter fw = new FileWriter(f);
            //fw.write(mc.);
        } catch (Exception e) {
        }
    }
//------------------------------------------------------------------------------
    //search by customer code
    public static void searchByCCode(MyList<Customer> mc){
        System.out.print("Enter Customer Code: ");
        String cCode = Validate.checkInputString();         
                                    
        Customer c; //defind a Customer
        if((c = mc.search(new Customer(cCode, "", ""))) == null){
            System.err.println("Not found!");
            return;
        }
        System.out.println(c.toString());
    }
//------------------------------------------------------------------------------  
    //delete by Customer code
    public static void delectByCCode(MyList<Customer> mc){
        System.out.print("Enter Customer code: ");
        String cCode = Validate.checkInputString();
        Customer c;
        //method delect not working
        mc.delete(new Customer(cCode, "", ""));
    }
//------------------------------------------------------------------------------ 
    //search by Product Code
    public static void searchByPCode(MyList<Product> mp){
        System.out.print("Enter Product code: ");
        String pCode = Validate.checkInputString();
        
        Product p;  //Define a Product 
        if((p = mp.search(new Product(pCode, "", 0, 0, 0))) == null){
            System.err.println("Not found!");
            return;
        }
        System.out.println(p.toString());
    }
//------------------------------------------------------------------------------
    //delete by Product Code
    public static void deleteByPCode(MyList<Product> mp){
        System.out.print("Enter Product code: ");
        //method delect not working
    }
//------------------------------------------------------------------------------
    //sort Product list by PCode
    public static void sortByPCode(MyList<Product> mp){
        mp.sort();
        System.err.println("Sorted");
    }
//------------------------------------------------------------------------------
    //add a Product after   
    public static void addProductAfter(MyList<Product> mp){
        System.out.print("Enter position k: ");
        int k = Validate.checkInputInt();
        System.out.print("Enter your code: ");
        String pCode = Validate.checkInputString();
        System.out.print("Enter your product name: ");
        String pName = Validate.checkInputString();
        System.out.print("Enter quantity of product: ");
        int pQuantity = Validate.checkInputInt();
        System.out.print("Enter number product was sale: ");
        int pSale = Validate.checkInputSaled(pQuantity);
        System.out.print("Enter price: ");
        double pPice = Validate.checkInputDouble();
        
        mp.addAfterPosition(k, new Product(pCode, pName, pQuantity, pSale, pPice));     //Jesus!! its working !
        
        System.err.println("Add successful!");
    }
//------------------------------------------------------------------------------
    public static void addOrder(MyList<Ordering> mo){
        System.out.print("Enter Customer code: ");
        String cCode = Validate.checkInputString();
        System.out.print("Enter Product code: ");
        String pCode = Validate.checkInputString();
        System.out.print("Enter Quantity of product: ");
        int pQuantity = Validate.checkInputInt();
        
        mo.addToTail(new Ordering(pCode, cCode, pQuantity));
        
        System.err.println("Add successful!");
    }
//------------------------------------------------------------------------------
    public static void printOrder(MyList<Ordering> mo){
        mo.traverse();
    }
//------------------------------------------------------------------------------ 
    public static void sortOrder(MyList<Ordering> mo){
        mo.sort();
    }
//------------------------------------------------------------------------------
    public static int menu(){
        System.out.printf( "%-3s%-10s", "", "---MAIN MENU---\n");
        System.out.println("1. Product list");
        System.out.println("2. Customer list");
        System.out.println("3. Order list");
        System.out.println("0. Exit program");
        
        int choise = checkInputLimited(0, 3);
        return choise;
    }
//------------------------------------------------------------------------------
    


//------------------------------------------------------------------------------
}
