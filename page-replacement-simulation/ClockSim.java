/**
 * Author: Gabriel Maguire
 * Date: 10/18/2020
 * 
 * This code was created to represent the functionality of the second-chance
 * page replacement algorithm. The second-change algorithm attempts to show
 * an improvement over the FIFO algorithm by implementing a reference bit to
 * hold information on if a given page was used recently.
 */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class ClockSim {
    
    private static HashMap<Integer, Page> pageTable;
    private static Queue<Page> replacementQueue;
    
    private int sizeOfMemory; // The number of pages that can be stored in memory

    public ClockSim(int sizeOfMemory) {
        this.sizeOfMemory = sizeOfMemory;
        pageTable = new HashMap<>();
        replacementQueue = new LinkedList<>();
    }

    /**
     * This method is called when the program (simulated by the reference string)
     * needs to access memory. This method first checks to see if the requested page
     * is currently in memory. If not, the page is added to memory and the replacement
     * queue and reference bit for each page is used to determine which page will be
     * replaced if necessary.
     * @param address Page memory address range
     * @return Memory 'hit' or 'miss' status
     */
    public String accessMemory(int address) {
        if (pageTable.containsKey(address)) {
            // Page found in memory
            pageTable.get(address).setReference((byte)1);
            return "Hit";
        } else {
            // Page not found in memory
            Page page = new Page(address);
            if (replacementQueue.size() < sizeOfMemory) {
                replacementQueue.add(page);
                pageTable.put(address, page);
            } else {
                while (replacementQueue.peek().getReference() == 1) {
                    Page tempPage = replacementQueue.poll();
                    tempPage.setReference((byte)0);
                    replacementQueue.add(tempPage);
                }
                Page replacePage = replacementQueue.poll();
                pageTable.remove(replacePage.getAddress());
                pageTable.put(address, page);
                replacementQueue.add(page);
            }
            return "Miss";
        }
    }

    public void printReplacementQueue() {
        for (Page page : replacementQueue) {
            System.out.printf("%-2d(%d)   ", page.getAddress(), page.getReference());
        }
        System.out.println();
    }

}