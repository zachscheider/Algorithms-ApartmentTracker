public class Apartment{
    private String streetAddress;
    private int apartmentNumber;
    private String city;
    private int zipcode;
    private int costPerMonth;
    private int sqFootage;
    private String identifier;

    public Apartment(String streetAddress, int apartmentNumber, String city, 
                        int zipcode, int costPerMonth, int sqFootage){
        setStreetAddress(streetAddress);
        setApartmentNumber(apartmentNumber);
        setCity(city);
        setZipcode(zipcode);
        setCostPerMonth(costPerMonth);
        setSqFootage(sqFootage);
        setIdentifier();
    }

    public void setStreetAddress(String streetAddress){
        this.streetAddress = streetAddress;
    }

    public String getStreetAddress(){
        return this.streetAddress;
    }

    public void setApartmentNumber(int apartmentNumber){
        this.apartmentNumber = apartmentNumber;
    }

    public int getApartmentNumber(){
        return this.apartmentNumber;
    }

    public void setCity(String city){
        this.city = city;
    }

    public String getCity(){
        return this.city;
    }

    public void setZipcode(int zipcode){
        this.zipcode = zipcode;
    }

    public int getZipcode(){
        return this.zipcode;
    }

    public void setCostPerMonth(int costPerMonth){
        this.costPerMonth = costPerMonth;
    }

    public int getCostPerMonth(){
        return this.costPerMonth;
    }

    public void setSqFootage(int sqFootage){
        this.sqFootage = sqFootage;
    }

    public int getSqFootage(){
        return this.sqFootage;
    }

    public void setIdentifier(){
        identifier = streetAddress + " " + apartmentNumber + " " + zipcode;
    }

    public String getIdentifier(){
        return this.identifier;
    }

    public String toString(){

        StringBuilder s = new StringBuilder();
        s.append("Street Address: " + streetAddress + "\n");
        s.append("Apartment Number: " + apartmentNumber + "\n");
        s.append("City: " + city + "\n");
        s.append("Zipcode: " + zipcode + "\n");
        s.append("Cost Per Month: $" + costPerMonth + "\n");
        s.append("Square Footage: " + sqFootage);

        return s.toString();
    }
}  