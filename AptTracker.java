import java.util.Scanner;

public class AptTracker{

    public static Scanner sc = new Scanner(System.in);
    public static int PQSize = 64;
    public static PriorityQueue thePQ = new PriorityQueue(PQSize);

    public static void menu(){

        System.out.println("\n");
        System.out.println("1.\tAdd an apartment");
        System.out.println("2.\tUpdate an apartment");
        System.out.println("3.\tRemove an apartment");
        System.out.println("4.\tRetrieve the lowest price apartment");
        System.out.println("5.\tRetrieve the highest square footage apartment");
        System.out.println("6.\tRetrieve the lowest price apartment for a city");
        System.out.println("7.\tRetrieve the highest square footage apartment for a city");
        System.out.println("0.\tExit the program");
        
        System.out.print("\nPlease enter a number of the menu option you want. ");
        int choice = sc.nextInt();

        switch(choice){
            case 0: 
                System.exit(0);
                break;
            case 1:  
                addApartment();
                break;
            case 2: 
                updateCost();
                break;
            case 3: 
                deleteApt();
                break;
            case 4: 
                System.out.println("\n" + thePQ.minCost().toString());
                break;
            case 5: 
                System.out.println("\n" + thePQ.maxSqFt().toString());
                break;
            case 6: 
                cityCheapest();
                break;
            case 7: 
                citySpacious();
                break;
            case 8: 
                printArr();
                break;
            default:
                System.out.println("\nPlease enter a valid number.");
                break;
        }
    }

    public static void addApartment(){

        String streetAddress, city, temp;
        int apartmentNumber, zipcode, costPerMonth, sqFootage;

        System.out.println("\nPlease fill out the information for the apartment.");

        temp = sc.nextLine();
        System.out.print("\nWhat is the street address? ");
        streetAddress = sc.nextLine();

        System.out.print("\nWhat is the apartment number? ");
        apartmentNumber = sc.nextInt();

        temp = sc.nextLine();
        System.out.print("\nWhat is the city? ");
        city = sc.nextLine();

        System.out.print("\nWhat is the zipcode? ");
        zipcode = sc.nextInt();

        System.out.print("\nWhat is the cost per month? ");
        costPerMonth = sc.nextInt();

        System.out.print("\nWhat is the square footage? ");
        sqFootage = sc.nextInt();

        Apartment apt = new Apartment(streetAddress, apartmentNumber, 
                            city, zipcode, costPerMonth, sqFootage);

        thePQ.insert(apt);
    }

    public static String findIdentifier(){
        String address, temp;
        int aptNum, zipcode;

        temp = sc.nextLine();
        System.out.print("\nWhat is the street address? ");
        address = sc.nextLine();

        System.out.print("\nWhat is the apartment number? ");
        aptNum = sc.nextInt();

        System.out.print("\nWhat is the zipcode? ");
        zipcode = sc.nextInt();

        temp = address + " " + aptNum + " " + zipcode;
        return temp;

    }

    public static void updateCost(){
        int newCost;
        String toUpdate = findIdentifier();
        char response;
        String temp;

        if(!thePQ.costIndex.containsKey(toUpdate)) return;
        System.out.println("\nThat apartment costs $" + thePQ.findApartment(toUpdate).getCostPerMonth() + ".\n");
        temp = sc.nextLine();
        System.out.println("Would you like to update how much it costs? (y, n) ");
        response = sc.nextLine().charAt(0);

        if(Character.toLowerCase(response) == 'y'){
            System.out.print("\nWhat is the new cost per month? ");
            newCost = sc.nextInt();
            thePQ.update(toUpdate, newCost);
        }
    }

    public static void deleteApt(){
        thePQ.remove(findIdentifier());
    }

    private static void cityCheapest(){
		String theCity;

        System.out.println("\nCheapest Apartment in the City");
        System.out.print("Please enter the city: ");
        theCity = sc.nextLine();
        theCity = sc.nextLine().toLowerCase();

        Apartment temp = thePQ.cityCost(0, theCity);
        if (temp == null) System.out.println("\nThere are no records for that city.");
        else System.out.println("\n" + temp);
	}

    private static void citySpacious(){
		String theCity;

        System.out.println("\nMost Spacious Apartment in the City");
        System.out.print("Please enter the city: ");
        theCity = sc.nextLine();
        theCity = sc.nextLine().toLowerCase();

        Apartment temp = thePQ.citySqFt(0, theCity);
        if (temp == null) System.out.println("\nThere are no records for that city.");
        else System.out.println("\n" + temp);
	}

    public static void main(String[] args) {

        while(true){
            menu();
        }

    }

    public static void printArr(){

        for(int i = 0; i < thePQ.size(); i++){
            System.out.print(thePQ.costPQ[i].getCostPerMonth() + " ");
        }
        System.out.println("\n");
        for(int i = 0; i < thePQ.size(); i++){
            System.out.print(thePQ.sqFtPQ[i].getSqFootage() + " ");
        }
    }
}