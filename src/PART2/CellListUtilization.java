// -----------------------------------------------------
// Written by: Matthew Segal
// ----------------------------------------------------
package PART2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A class to determine how a CellList is used
 */
public class CellListUtilization {
    public static Scanner userIn = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("WELCOME TO THE PROGRAM THAT TESTS WHETHER THE " +
                "CELLLIST, CELLNODE, AND CELLPHONE CLASS METHODS I IMPLEMENTED ARE GOOD");

        CellList list1 = new CellList();
        CellList list2 = new CellList();

        Scanner reader;

        System.out.println("\nTesting whether creating CellLists based on the contents of the given sample " +
                "file works:\n(Only adds to the CellList if the CellList does not already contain the given " +
                "CellNode)\n");
        try {
            reader = new Scanner(new FileInputStream("samples/Cell_Info.txt"));

            while (reader.hasNextLine()){
                long serial = reader.nextLong();
                String brand = reader.next();
                double price = reader.nextDouble();
                int year = reader.nextInt();

                CellPhone phone = new CellPhone(serial,brand,year,price);

                System.out.print("Checking list 1: ");
                if (!list1.contains(phone.getSerialNum())){
                    list1.addToStart(phone);
                }

                System.out.print("Checking list 2: ");
                if (!list2.contains(phone.getSerialNum())){
                    list2.addToStart(phone);
                }
            }
            System.out.println("\nLIST 1 CONTENTS:");
            list1.showContents();

            System.out.println("\nLIST 2 CONTENTS:");
            list2.showContents();

            if (list1.equals(list2)) System.out.println("THE LISTS ARE EQUAL");
            else System.out.println("THE LISTS AREN'T EQUAL");

            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("\nTests whether searching the CellLists for a specific serial number works:");

        System.out.print("\nPlease enter a serial number to check the first list for: ");
        list1.find(userIn.nextLong());

        System.out.print("\nPlease enter another serial number to check the first list for: ");
        list1.find(userIn.nextLong());

        System.out.println("\nTests if adding an element at a given index (0) works:");
        System.out.println("\nOLD SIZE WAS: " + list1.getSize());
        list1.insertAtIndex(new CellPhone(11,"cool",1000,100),0);
        list1.showContents();
        System.out.println("\nNEW SIZE IS: " + list1.getSize());

        CellList testList = new CellList();

        System.out.println("\nTests if the default CellList constructor works:");
        System.out.println("\nThis list should be empty");

        testList.showContents();
        System.out.println("Size: " + testList.getSize());

        System.out.println("\nTesting equality between 2 CellLists:");
        if (testList.equals(list1)){
            System.out.println("Test list and list1 are equal");
        }else {
            System.out.println("Test list and list 1 aren't equal");
        }

        System.out.println("\nTesting if adding to the start works:");
        testList.addToStart(new CellPhone(111,"newBrand",1002,5000));

        System.out.println("\nAfter adding 1 element:");
        testList.showContents();
        System.out.println("Size: " + testList.getSize());

        testList.addToStart(new CellPhone(123,"123",123,123));

        System.out.println("\nAfter adding another element:");
        testList.showContents();
        System.out.println("Size: " + testList.getSize());

        System.out.println("\nTesting if inserting at an index works:");
        testList.insertAtIndex(new CellPhone(999,"brand",8000,2), 1);

        System.out.println("\nInserts a new CellPhone at index 1:");
        testList.showContents();
        System.out.println("Size: " + testList.getSize());

        System.out.println("\nDeletes a CellNode from the start:");
        testList.deleteFromStart();
        testList.showContents();
        System.out.println("Size: " + testList.getSize());

        int indexToReplace = 1;
        System.out.println("\nTests if replacing a CellNode at a given index works. Index is: " + indexToReplace);
        testList.replaceAtIndex(new CellPhone(456,"456",456,456),indexToReplace);
        testList.showContents();
        System.out.println("Size: " + testList.getSize());

        int indexToDelete = 1;
        System.out.println("\nDeletes a CellNode at a given index: " + indexToDelete);
        testList.deleteFromIndex(indexToDelete);
        testList.showContents();
        System.out.println("Size: " + testList.getSize());

        System.out.println("\nTests if adding to end works:");
        testList.addToEnd(new CellPhone(555,"555",555,555));
        testList.showContents();
        System.out.println("Size: " + testList.getSize());

        System.out.println("\nTests if adding to start works:");
        testList.addToStart(new CellPhone(222,"222",222,222));
        testList.showContents();
        System.out.println("Size: " + testList.getSize());

        long testSerial = 111;
        System.out.println("\nTests if the Test List contains a specific serial number: " + testSerial);
        if (testList.contains(testSerial)){
            System.out.println("Test List has given serial number " + testSerial);
        }else System.out.println("Test List doesn't have given serial number " + testSerial);

        System.out.println("\nTests creating 3 new CellPhones:");
        CellPhone phone1 = new CellPhone(456,"456",456,456);
        CellPhone phone2 = new CellPhone(457,"456",456,456);
        CellPhone phone3 = new CellPhone(147,"687",79,88);

        System.out.println(phone1.toString() + "\n" + phone2.toString() + "\n" + phone3.toString());

        System.out.println("\nTests if 2 CellPhones are equal if they are equal in all ways but serial number:");
        if (phone1.equals(phone2)){
            System.out.println("phone 1 equals phone 2");
        }else System.out.println("phone 1 does not equal phone 2");

        System.out.println("\nTests if 2 CellPhones are equal if they're totally different:");
        if (phone2.equals(phone3)){
            System.out.println("phone 2 equals phone 3");
        }else System.out.println("phone 2 does not equal phone 3");


        System.out.println("\nCreating a clone of phone1:");
        CellPhone clone = phone1.clone();

        System.out.println("\nTests if a CellPhone is equal to it's clone:");
        System.out.println(phone1.toString() + "\n" + clone.toString());
        if (phone1.equals(clone)){
            System.out.println("They are equal");
        }else System.out.println("They are not equal");


        System.out.println("\nTests if the CellList copy constructor method works:");
        CellList listClone1 = new CellList(testList);
        System.out.println("\nORIGINAL:");
        testList.showContents();
        System.out.println("Size: " + testList.getSize());
        System.out.println("\nCLONE:");
        listClone1.showContents();
        System.out.println("Size: " + listClone1.getSize());

        System.out.println("\nTests if the CellList clone method works:");
        System.out.println("\nORIGINAL:");
        testList.showContents();
        System.out.println("Size: " + testList.getSize());

        System.out.println("\nCLONING:");
        CellList listClone = testList.clone();
        System.out.println("\nCLONE:");
        listClone.showContents();
        System.out.println("Size: " + listClone.getSize());

        System.out.println("\nTests if testList and listCLone are equal:");
        if (listClone.equals(testList)){
            System.out.println("listClone is equal to testList");
        }else {
            System.out.println("listCLone is not equal to testList");
        }

        System.out.println("\nTests if deleting elements until there are none left work:\n");

        testList.showContents();
        System.out.println("Size: " + testList.getSize());
        testList.deleteFromStart();

        System.out.println();
        testList.showContents();
        System.out.println("Size: " + testList.getSize());
        testList.deleteFromStart();

        System.out.println();
        testList.showContents();
        System.out.println("Size: " + testList.getSize());
        testList.deleteFromStart();

        System.out.println();
        testList.showContents();
        System.out.println("Size: " + testList.getSize());
        testList.deleteFromStart();

        System.out.println("\nTests if removing an element from an already empty list causes error:");
        testList.showContents();
        System.out.println("Size: " + testList.getSize());


        System.out.println("\nTHANK YOU, GOODBYE!");
        userIn.close();
    }
}
