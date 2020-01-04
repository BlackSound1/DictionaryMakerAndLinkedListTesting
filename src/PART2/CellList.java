// -----------------------------------------------------
// Written by: Matthew Segal
// ----------------------------------------------------
package PART2;

import java.util.NoSuchElementException;

/**
 * A class for the definition of a CellList object, which will be a Linked List
 */
public class CellList implements  Cloneable{

    /**
     * A class for the definition of a CellNode object, which will be an element in the CellList Linked List
     */
    private class CellNode implements Cloneable{
        // THIS CLASS HAS THE PRIVATE MODIFIER TO HELP REDUCE THE LIKELIHOOD OF A PRIVACY LEAK.///////
        // ADDITIONALLY, IT IS AN INNER CLASS, AND THUS, IT'S FIELDS ARE ONLY DIRECTLY ACCESSIBLE BY//
        // THE OUTER CLASS CellList///////////////////////////////////////////////////////////////////

        //////////
        //FIELDS//
        //////////
        private CellPhone cellPhone;
        private CellNode next;

        ///////////////////////
        //GETTERS AND SETTERS//
        ///////////////////////
        /**
         * Gets the CellPhone of the calling CellNode
         * @return The CellPhone
         */
        CellPhone getCellPhone() {
            return cellPhone;
        }

        /**
         * Sets the CellPhone of the calling CellNode
         * @param cellPhone The given CellPhone
         */
        void setCellPhone(CellPhone cellPhone) {
            this.cellPhone = cellPhone.clone(); // Sets the CellPhone to  a deep copy of the incoming CellPhone
        }

        /**
         * Gets the next element in the CellList
         * @return the next element
         */
        CellNode getNext() {
            return next;
        }

        /**
         * Sets the next element in the CellList
         * @param next The given CellNode to make the next
         */
        void setNext(CellNode next) {
            this.next = next;
        }

        ////////////////
        //CONSTRUCTORS//
        ////////////////
        /**
         * Default Constructor for CellNode
         * Sets both fields to null
         */
        public CellNode() {
            cellPhone = null;
            next = null;
        }

        /**
         * Parameterized Constructor
         * @param cellPhone The CellPhone to base this CellNode off of
         * @param next A CellNode
         */
        public CellNode(CellPhone cellPhone, CellNode next) {
            this.cellPhone = cellPhone;
            this.next = next;
        }

        /**
         * Copy Constructor for CellNode
         * @param n The CellNode to copy
         */
        public CellNode(CellNode n){
            /*THIS METHOD MAY CAUSE A PRIVACY LEAK. BUT I PREVENT THIS BY ONLY USING A CLONE
            * OF THE CELLPHONE OBJECT BEING PASSED IN.*/
            this.cellPhone = n.getCellPhone().clone(); // This creates a deep copy of the CellNode
            this.next = n.getNext();
        }

        ///////////
        //METHODS//
        ///////////
        /**
         * Creates a CellNode clone
         * @return The clone of the CellNode
         */
        public CellNode clone(){
            CellNode clone = null;

            try {
                clone = (CellNode) super.clone();

                // Uses the Copy Constructor to create a clone
                clone = new CellNode(this);

                //clone = new CellNode(clone.getCellPhone(), clone.getNext());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return clone;
        }
    }

    //////////
    //FIELDS//
    //////////
    private CellNode head;
    private int size;

    ///////////////////////
    //GETTERS AND SETTERS//
    ///////////////////////
    /**
     * Gets the head of the CellList
     * @return The head
     */
    CellNode getHead() { return head; }

    /**
     * Sets the head of the CellList
     * @param head The given CellNode to make the head
     */
    void setHead(CellNode head) {
        this.head = head;
    }

    /**
     * Gets the size of the CellList
     * @return The size
     */
    int getSize() {
        return size;
    }

    /**
     * Sets the size of the CellList
     * @param size The given size to make the CellList
     */
    void setSize(int size) {
        this.size = size;
    }

    ////////////////
    //CONSTRUCTORS//
    ////////////////
    /**
     * The default Constructor for a CellList
     */
    public CellList(){
        head = null;
        size = 0;
    }

    /**
     * The copy Constructor for a CellList
     * @param cellList The CellList to copy based on
     */
    public CellList(CellList cellList){
        if (cellList.getHead() == null){
            // If the given CellList's head is null, simply create a new default CellList.

            new CellList();
        }else {
            // Sets the initial size to 0
            size = 0;
            // Sets the initial head to null
            head = null;

            // Creates a temporary CellNode based on the head of the given CellList
            CellNode temp = cellList.head;

            // Adds the first element to the end of the new CellList and advances to the next
            // AVOIDS PRIVACY LEAK BY USING ONLY A CLONE OF THE CELLPHONE OBJECT
            CellPhone phone1 = temp.getCellPhone().clone();
            long newNum = phone1.getSerialNum();

            addToEnd(new CellPhone(phone1,newNum));

            temp = temp.getNext();

            while (temp != null){
                // Performs similar steps to above, but for the rest of the CellList
                CellPhone newPhone = temp.getCellPhone().clone();
                newNum = newPhone.getSerialNum();
                addToEnd(new CellPhone(newPhone,newNum));

                temp = temp.getNext();
            }
        }
    }

    ///////////
    //METHODS//
    ///////////
    /**
     * Clones a given CellList
     * @return The clone of the given CellList
     */
    public CellList clone(){
        /*CellList clone;
        try {
            clone = (CellList) super.clone();
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }*/
        return new CellList(this);
    }
    /**
     * Adds the given CellPhone to a CellNode at the start of the CellList
     * @param phone The given CellPhone to add
     */
    void addToStart(CellPhone phone){
        /*THIS METHOD COULD POTENTIALLY CAUSE A PRIVACY LEAK. WHEN I REFERENCE A
         * CELLPHONE OBJECT, I SHOULD CREATE A CLONE OF IT. I DO NOT DO THIS, BECAUSE
         * CLONING A CELLPHONE OBJECT REQUIRES ME TO ASK THE USER FOR A NEW SERIAL NUMBER FOR
         * THE CELLPHONE. I FIND THIS WILL MAKE THE PROGRAM TEDIOUS TO RUN AND TEST.*/

        // I SHOULD CREATE A NEW CELLNODE BASED ON A CLONE OF THE GIVEN CELLPHONE HERE
        head = new CellNode(phone, head);
        size++;
    }

    /**
     * Adds a CellPhone to the end of the CellList
     * @param phone The CellPhone to add
     */
    void addToEnd(CellPhone phone){
        /*THIS METHOD COULD POTENTIALLY CAUSE A PRIVACY LEAK. WHEN I REFERENCE A
        * CELLPHONE OBJECT, I SHOULD CREATE A CLONE OF IT. I DO NOT DO THIS, BECAUSE
        * CLONING A CELLPHONE OBJECT REQUIRES ME TO ASK THE USER FOR A NEW SERIAL NUMBER FOR
        * THE CELLPHONE. I FIND THIS WILL MAKE THE PROGRAM TEDIOUS TO RUN AND TEST.*/

        if (head == null){
            // I SHOULD ADD A CLONE OF THE CELLPHONE TO THE START HERE
            addToStart(phone);
        }else {
            CellNode temp = head;

            // Advances through the CellList until the last CellNode
            while (temp.getNext() != null){
                temp = temp.getNext();
            }

            // I SHOULD CREATE A NEW CELLNODE BASED ON A CLONE OF THE GIVEN CELLPHONE HERE
            // Sets the next CellNode after the last CellNode to be a new CellNode based
            // on the given CellPhone
            temp.setNext(new CellNode(phone,temp.getNext()));
            size++;
            //System.out.println(size);
        }
    }

    /**
     * Inserts a new CellNode at the given index
     * @param phone The CellPhone to set the CellNode based on
     * @param index The index to iterate to
     */
    void insertAtIndex(CellPhone phone, int index){
        /*THIS METHOD COULD POTENTIALLY CAUSE A PRIVACY LEAK. WHEN I REFERENCE A
         * CELLPHONE OBJECT, I SHOULD CREATE A CLONE OF IT. I DO NOT DO THIS, BECAUSE
         * CLONING A CELLPHONE OBJECT REQUIRES ME TO ASK THE USER FOR A NEW SERIAL NUMBER FOR
         * THE CELLPHONE. I FIND THIS WILL MAKE THE PROGRAM TEDIOUS TO RUN AND TEST.*/

        try {
            if (index < 0 || index > this.getSize() - 1){
                throw new NoSuchElementException();
            }else {
                if (index == 0){
                    // Performs the insertion operation at the start of the Linked List

                    // I SHOULD ADD A CLONE OF THE CELLPHONE TO THE START HERE
                    addToStart(phone);
                }else {
                    // Creates a temporary Node
                    CellNode beforeIndexTemp = head;
                    CellNode indexTemp = head;

                    // Steps through the CellList until the Node before the specified index is reached
                    for (int i = 0; i < index - 1; i++){
                        beforeIndexTemp = beforeIndexTemp.getNext(); // Keeps setting the temp node to the next node
                    }

                    // Steps through the CellList until the specified index is reached
                    for (int i = 0; i < index; i++){
                        indexTemp = indexTemp.getNext();
                    }

                    // I SHOULD CREATE A NEW CELLNODE BASED ON A CLONE OF THE GIVEN CELLPHONE HERE
                    beforeIndexTemp.setNext(new CellNode(phone,indexTemp));

                    size++;
                }
            }
        }catch (NoSuchElementException e){
            System.exit(0);
        }
    }

    /**
     * Deletes a CellNode at a given index
     * @param index The index of the CellNode to delete
     */
    void deleteFromIndex(int index){
        try {
            if (index < 0 || index > this.getSize() - 1){
                throw new NoSuchElementException();
            }else {
                if (index == 0){
                    // Performs the delete operation on the first element in the Linked List
                    deleteFromStart();
                }else {
                    // Creates 2 temporary Nodes
                    CellNode beforeIndexTemp = head;
                    CellNode indexNode = head;

                    // Steps through the CellList until the index before the specified index is reached
                    for (int i = 0; i < index - 1; i++){
                        beforeIndexTemp = beforeIndexTemp.getNext(); // Keeps setting the temp node, to the next node

                    }

                    // Steps through the CellList until the index of the specified index is reached
                    for (int i = 0; i < index; i++){
                        indexNode = indexNode.getNext();
                    }

                    // Once index has been reached, sets temp Node to point to the Node after the index node
                    beforeIndexTemp.setNext(indexNode.getNext());
                    size--;
                }
            }
        }catch (NoSuchElementException e){
            System.exit(0);
        }
    }

    /**
     * Deletes the first element in the Linked List
     */
    void deleteFromStart(){
        if (head != null){
            head = head.getNext();
            size--;
        }
    }

    /**
     * Replaces the CellPhone at the given index in the CellList with the given CellPhone
     * @param phone The Given CellPhone to replace with
     * @param index The given index to traverse the CellList until
     */
    void replaceAtIndex(CellPhone phone, int index){
        /*THIS METHOD COULD POTENTIALLY CAUSE A PRIVACY LEAK. WHEN I REFERENCE A
         * CELLPHONE OBJECT, I SHOULD CREATE A CLONE OF IT. I DO NOT DO THIS, BECAUSE
         * CLONING A CELLPHONE OBJECT REQUIRES ME TO ASK THE USER FOR A NEW SERIAL NUMBER FOR
         * THE CELLPHONE. I FIND THIS WILL MAKE THE PROGRAM TEDIOUS TO RUN AND TEST.*/

        try {
            if (index >= 0 && index < this.getSize()){ // Only activates if index is in proper range
                if (index == 0){ // Performs the replacement operation on the first element

                    // I SHOULD CREATE A NEW CELLNODE BASED ON A CLONE OF THE GIVEN CELLPHONE HERE
                    head = new CellNode(phone,head.getNext());

                }else {
                    CellNode beforeIndexTemp = head; // Creates temporary node
                    CellNode indexTemp = head;


                    // Iterates through the CellList a number of times equal to the index - 1
                    for (int i = 0; i < index - 1; i++){
                        beforeIndexTemp = beforeIndexTemp.getNext();
                    }

                    // Iterates through the CellList a number of times equal to the index
                    for (int i = 0; i < index; i++){
                        indexTemp = indexTemp.getNext();
                    }

                    // I SHOULD CREATE A NEW CELLNODE BASED ON A CLONE OF THE GIVEN CELLPHONE HERE
                    beforeIndexTemp.setNext(new CellNode(phone,indexTemp.getNext()));
                }
            }else {
                throw new NoSuchElementException();
            }
        } catch (NoSuchElementException e) {
            System.exit(0);
        }
    }

    /**
     * Finds the CellNode that has a CellPhone with the given serial number
     * @param serialNum The given serial number to check for
     * @return The Cell Node that was found to contain a CellPhone with the given serial number
     */
    CellNode find(long serialNum){
        int iterations = 1;
        CellNode temp = head; // Creates temporary CellNode
        long serialToCheck;

        // Keeps advancing through the list until it finds a CellPhone with the given serial number
        while (temp != null){

            // Gets the serial number to be found
            serialToCheck = temp.getCellPhone().getSerialNum();

            if (serialToCheck == serialNum){
                System.out.println("It took " + iterations + " iterations to find this: " +
                        temp.getCellPhone().toString());
                return temp;
            }else {
                temp = temp.getNext();
                iterations++;
            }
        }
        System.out.println("Did not find node after " + iterations + " iterations");
        return null;
    }

    /**
     * Checks if Linked List contains a given serial number
     * @param serialNum The given serial number to check for
     * @return True or False, based on success or failure in finding the serial number
     */
    boolean contains(long serialNum){

        return (find(serialNum) != null);
    }

    /**
     * Shows the contents of the whole Linked List
     */
    void showContents(){

        if (size == 0){
            System.out.println("Empty CellList");
        }else{
            CellNode temp = head; // Creates temporary CellNode

            // Advances through the Linked List, and prints the toString of each CellPhone there.
            while (temp != null){
                System.out.println(temp.getCellPhone().toString());

                temp = temp.getNext();
            }

            // Shows the last CellNode as well
            //System.out.println(temp.getCellPhone().toString());
        }
    }

    /**
     * Checks if 2 Linked Lists are equal to each other
     * @param list The Linked List to check against
     * @return True or False, based on if they are equal
     */
    boolean equals(CellList list){

        if (list == null){ // Checks if given CellList is null
            return false;
        }else if (list.getClass() != this.getClass()){ // Checks if the 2 object's classes aren't equal
            return false;
        }

        // Creates a copy of the given CellList. Preserves privacy
        CellList otherList = (CellList) list;

        // Checks if sizes are not equal
        if (this.getSize() != otherList.getSize()){
            return false;
        }

        // Creates temp CellNodes in both CellLists, for traversal.
        CellNode thisNode = this.getHead();
        CellNode otherNode = otherList.getHead();

        // Traverses calling CellList
        while (thisNode != null){

            // Creates variables based on the 2 CellNode's CellPhones
            CellPhone thisPhone = thisNode.getCellPhone();
            CellPhone otherPhone = otherNode.getCellPhone();

            // Checks if the various criteria of the 2 CellList's CellNodes aren't equal. Doesn't check
            // serialnum, as they might be unique.
            if (!(thisPhone.equals(otherPhone))){
                return false;
            }else {

                // Iterates to the next CellNodes in the respective CellLists
                thisNode = thisNode.getNext();
                otherNode = otherNode.getNext();
            }
        }
        return true;
    }
}
