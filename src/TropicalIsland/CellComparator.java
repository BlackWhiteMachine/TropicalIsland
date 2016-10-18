/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TropicalIsland;

import java.util.Comparator;

/**
 *
 * @author Alexey
 */
public class CellComparator implements Comparator<Cell> { 

public int compare(Cell o1, Cell o2) { 
// if (o1.getAltitude() > o2.getAltitude()) return 1; 
// if (o1.getAltitude() < o2.getAltitude()) return -1; 
// return 0; 
return o1.compareTo(o2); 
}
}
