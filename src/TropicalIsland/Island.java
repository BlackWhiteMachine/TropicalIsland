/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TropicalIsland;

import java.util.PriorityQueue;

/**
 *
 * @author Alexey
 */
public class Island {
    public Cell[][] altitude;

    public int height;
    public int width;

    public Island(int height, int width) {
    	altitude = new Cell[height][width];

    	this.height = height;
    	this.width  = width;
    }

    public void setCells(int[][] array) {
        if (this.height != array.length || this.width != array[0].length) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                this.altitude[i][j]= new Cell(array[i][j]) ;
            }
        }

        for(int i=1; i<this.height-1; i++) {
            for(int j=1; j<this.width-1; j++) {
                this.altitude[i][j].set_left(altitude[i][j-1]);
                this.altitude[i][j].set_top(altitude[i+1][j]);
                this.altitude[i][j].set_right(altitude[i][j+1]);
                this.altitude[i][j].set_bottom(altitude[i-1][j]);
            }
        }
    }

    public int init_const() {
        int changes;

        do {
            changes = 0;
            for(int i=0; i<this.height; i++) {
                int current = 0;
                for(int j=0; j<this.width; j++) {
                    if (this.altitude[i][j].height >= current) {
                        current = this.altitude[i][j].height;
                        if (this.altitude[i][j].is_const != true) {
                            this.altitude[i][j].is_const = true;
                            changes++;
                        }
                    }

                    if (this.altitude[i][j].is_const == true) {
                        current = this.altitude[i][j].height;
                    }
                }
                current = 0;
                for(int j=this.width-1; j>=0; j--) {
                    if (this.altitude[i][j].height >= current) {
                        current = this.altitude[i][j].height;
                        if (this.altitude[i][j].is_const != true) {
                            this.altitude[i][j].is_const = true;
                            changes++;
                        }
                    }

                    if (this.altitude[i][j].is_const == true) {
                        current = this.altitude[i][j].height;
                    }

                }
            }

            for(int j=0; j<this.width; j++) {
                int current = 0;
                for(int i=0; i<this.height; i++) {
                    if (this.altitude[i][j].height >= current) {
                        current = this.altitude[i][j].height;
                        if (this.altitude[i][j].is_const != true) {
                            this.altitude[i][j].is_const = true;
                            changes++;
                        }
                    }

                    if (this.altitude[i][j].is_const == true) {
                        current = this.altitude[i][j].height;
                    }

                }
                current = 0;
                for(int i=this.height-1; i>=0; i--) {
                    if (this.altitude[i][j].height >= current) {
                        current = this.altitude[i][j].height;
                        if (this.altitude[i][j].is_const != true) {
                            this.altitude[i][j].is_const = true;
                            changes++;
                        }
                    }

                    if (this.altitude[i][j].is_const == true) {
                        current = this.altitude[i][j].height;
                    }

                }
            }
        } while (changes != 0);

        int retval = 0;
        for(int i=0; i<this.height; i++) {
            for(int j=0; j<this.width; j++) {
                if (this.altitude[i][j].is_const == false)
                    retval++;
            }
        }
        return retval;
    }

    public void reset_const () {
        for(int i=0; i<this.height; i++) {
            for(int j=0; j<this.width; j++) {
                this.altitude[i][j].is_const = false;
            }
        }
    }

    public void print_altitude() {
        for(int i=height-1; i>=0; i--) {
            for(int j=0; j<width; j++) {
                System.out.print(altitude[i][j].toString());
            }
            System.out.println("");
        }

        System.out.println("*********************************************");
    }

    public void chech_botton(Cell current) {


        int pivot = current.bln_neighbor();

        PriorityQueue<Cell> VarQueue = new PriorityQueue<>(new CellComparator());

        for (Cell result : current.getNeighbors())
            VarQueue.add(result);


        if (VarQueue.isEmpty()) {
            current.set_volume(pivot);
        }
        else {
            Cell min_neighbor = VarQueue.peek();
            if (min_neighbor.height >= pivot) {
                current.set_volume(pivot);
            }
            else {
                PriorityQueue<Cell> array1 = new PriorityQueue<>(new CellComparator());

                array1.add(current);

                while ( (min_neighbor != null) && (min_neighbor.height < pivot) )  {

                     Cell neighbor = VarQueue.poll();

                    if (pivot > neighbor.bln_neighbor()) {
                        chech_botton(neighbor);
                        chech_botton(current);
                        return;
                    }

                    array1.add(neighbor);

                    for (Cell result : neighbor.getNeighbors()) {
                        if (!VarQueue.contains(result) && !array1.contains(result) )
                            VarQueue.add(result);
                    }
                    min_neighbor = VarQueue.peek();
                }

                for (Cell result : array1)
                    result.set_volume(pivot);
            }
        }
        VarQueue.clear();
    }

    public void rain() {
        int x = 0;
        int y = 1;

        this.init_const();
    //    int step = 1;

while (this.init_const() != 0) {
        for(int i=0; i<this.height; i++) {
            for(int j=0; j<this.width; j++) {
                if (altitude[i][j].is_const == false) 
                    chech_botton(altitude[i][j]);
            }
        }
        this.reset_const();
}
/*
        boolean center = false;

        while (this.init_const() != 0) {
            int step = 0;
            for (; step < 20; ) {
       //    for (; center != true; ) {
                int i = step ;
                int j = step ;
                for(; j<this.width-step; j++) {
                    if (altitude[i][j].is_const == false) {

            //    System.out.println(altitude[i][j].height + "; i=" + i + "; j=" + j);
                        chech_botton(altitude[i][j]);
                        this.print_altitude();
                    }
                }
                System.out.println( "i=" + i + "; j=" + j);
                j--;
                for(; i<this.height-step; i++) {
                    if (altitude[i][j].is_const == false) 
                        chech_botton(altitude[i][j]);
                }
                System.out.println( "i=" + i + "; j=" + j);
                i--;

                for(; j>step; j--) {
                    if (altitude[i][j].is_const == false) {

            //    System.out.println(altitude[i][j].height + "; i=" + i + "; j=" + j);
                        chech_botton(altitude[i][j]);
                    }
                }
                System.out.println( "i=" + i + "; j=" + j);


                for(; i>step; i--) {
                    if (altitude[i][j].is_const == false) 
                        chech_botton(altitude[i][j]);
                }
                System.out.println( "i=" + i + "; j=" + j);

                step++;
                if ((step >= this.height/2) || (step >= this.width/2))
                    center = true;
            }
            //        chech_botton(altitude[y][x]);

        this.reset_const();
        }
*/
    }

    public int count_water() {
        this.rain();

        int result = 0;

        for(int i=1; i<this.height-1; i++) {
            for(int j=1; j<this.width-1; j++) {
                result += this.altitude[i][j].volume;
            }
        }

        return result;
    }
}
