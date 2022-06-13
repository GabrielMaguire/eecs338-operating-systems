/**
 * Author: Gabriel Maguire
 * Date: 10/18/2020
 * 
 * This code was created to represent a virtual memory page for use
 * in the comparison of two different page replacement algorithms.
 */

public class Page {
    
    private int address;
    private byte reference = 1; // Used to represent the recency of page use

    public Page(int address) {
        this.address = address;
    }

    public int getAddress() {
        return address;
    }

    public byte getReference() {
        return reference;
    }

    public void setReference(byte reference) {
        this.reference = reference;
    }

}
