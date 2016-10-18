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
public class Cell {
    public int height;
    public int volume;
    public boolean is_const;
    

    public Cell left;
    public Cell top;
    public Cell right;
    public Cell bottom;

    public Cell(int height) {
        this.height = height;
        this.volume = 0;
        this.is_const = false;
    }
    

    private void check_const(Cell pivot) {
        if ((pivot.is_const) && (pivot.height <= this.height) && (this.is_const == false)) {
            this.change_const();
        }
    }

    public void change_const() {

    //    if (this.is_const != true) {
            this.is_const = true;

            this.check_const(left);
            this.check_const(top);
            this.check_const(right);
            this.check_const(bottom);

        //    return this.is_const;
    //    }
   //     else return false;
    }

    public void set_left(Cell pivot) {
        this.left = pivot;
    }

    public void set_top(Cell pivot) {
        this.top = pivot;
    }

    public void set_right(Cell pivot) {
        this.right = pivot;
    }

    public void set_bottom(Cell pivot) {
        this.bottom = pivot;
    }

    public int bln_neighbor() {
        int result = 1001;

        if (left.is_const)
            result = left.height;

        if ( (top.is_const) && (top.height < result) )
            result = top.height;

        if ( (right.is_const) && (right.height < result) )
            result = right.height;

        if ( (bottom.is_const) && (bottom.height < result) )
            result = bottom.height;

        return result;
    }

    public boolean have_balanced() {
        if (left.is_const)
            return true;

        if (top.is_const)
            return true;

        if (right.is_const)
            return true;

        if (bottom.is_const)
            return true;

        return false;
    }

    public void set_volume(int level) {
        if (level > this.height) {
            this.volume += level - this.height;
            this.height = level;
        }
        this.change_const();
    }

    public PriorityQueue<Cell> getNeighbors() {
        PriorityQueue<Cell> result = new PriorityQueue<>(new CellComparator());

        if (this.left.is_const == false)
            result.add(this.left);

        if (this.top.is_const == false)
            result.add(this.top);

        if (this.right.is_const == false)
            result.add(this.right);

        if (this.bottom.is_const == false)
            result.add(this.bottom);

        return result;
    }

    public int compareTo(Cell c2) {
        return this.height - c2.height;
    }

    public String toString(){
        if (is_const) return "|" + height + "|";
        else return " " + height + " ";
    }
}
