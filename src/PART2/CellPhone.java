// -----------------------------------------------------
// Written by: Matthew Segal
// ----------------------------------------------------
package PART2;

/**
 * A class for the definition of a CellPhone object, which will be the data associated with a CellNode
 */
public class CellPhone implements Cloneable{

    //////////
    //FIELDS//
    //////////
    private long serialNum;
    private String brand;
    private int year;
    private double price;

    ///////////////////////
    //SETTERS AND GETTERS//
    ///////////////////////

    /**
     * Gets the serial number of the CellPhone
     * @return The serial number
     */
    long getSerialNum() { return serialNum; }

    /**
     * Sets the serial number of the CellPhone
     * @param serialNum The serial number to set
     */
    void setSerialNum(long serialNum) {
        this.serialNum = serialNum;
    }

    /**
     * Gets the brand of the CellPhone
     * @return The brand
     */
    String getBrand() {
        return brand;
    }

    /**
     * Sets the brand of the CellPhone
     * @param brand The brand to set
     */
    void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Gets the build year of the CellPhone
     * @return The build year
     */
    int getYear() {
        return year;
    }

    /**
     * Sets the build year of the CellPhone
     * @param year The build year to set
     */
    void setYear(int year) {
        this.year = year;
    }

    /**
     * Gets the price of the CellPhone
     * @return The price
     */
    double getPrice() {
        return price;
    }

    /**
     * Sets the price of the CellPhone
     * @param price The price to set
     */
    void setPrice(double price) {
        this.price = price;
    }

    ////////////////
    //CONSTRUCTORS//
    ////////////////
    /**
     * Parameterized Constructor of this CellPhone
     * @param serialNum The unique serial number it will have
     * @param brand The brand
     * @param year Its build year
     * @param price Its price
     */
    public CellPhone(long serialNum, String brand, int year, double price) {
        this.serialNum = serialNum;
        this.brand = brand;
        this.year = year;
        this.price = price;
    }

    /**
     * The Copy Constructor
     * @param c The other CellPhone to copy
     * @param val The new unique serial number the copied CellPhone will have
     */
    public CellPhone(CellPhone c, long val){
        this.brand = c.getBrand();
        this.serialNum = val;
        this.price = c.getPrice();
        this.year = c.getYear();
    }

    ///////////
    //METHODS//
    ///////////
    /**
     * Creates a Clone object of a given CellPhone
     * @return The Clone CellPhone
     */
    public CellPhone clone(){
        CellPhone clone = null;

        try {
            clone = (CellPhone) super.clone();

            // Gets a new serial number for the clone CellPhone to have
            System.out.print("Please enter a new Serial Number for the Clone to have: ");

            long newNum = CellListUtilization.userIn.nextLong();

            // Uses Copy Constructor to create a clone
            clone = new CellPhone(this,newNum);
            //clone = new CellPhone(newNum,clone.getBrand(),clone.getYear(),clone.getPrice());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }

    /**
     * Returns a String featuring useful info about this CellPhone
     * @return The String of useful info
     */
    public String toString(){
        return "The phone's Serial Number is: " + this.serialNum + ", it's Price is: " + this.price +
                ", it's Build Year is: " + this.year + ", and it's Brand is: " + this.brand;
    }

    /**
     * Determines if a given Object is equal to this CellPhone
     * @param o The Object to test this CellPhone against
     * @return True of False
     */
    public boolean equals(Object o){
        if (o == null){
            return false;
        }else if (CellPhone.class != o.getClass()){
            return false;
        }

        CellPhone c = (CellPhone) o;

        return this.brand.equals(c.getBrand()) && this.year == c.getYear() && this.price == c.getPrice();
    }
}
