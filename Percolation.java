/* *****************************************************************************
  *  Name: J Sri Krishna
  *  Date: 5-04-2020
  *  Description:
**************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
class Percolation{
    boolean[][] objects;
    int _len;
    int top;
    int bottom;
    WeightedQuickUnionUF li;
    Percolation(int n){
        objects = new boolean[n][n];
        _len = n;
        li = new WeightedQuickUnionUF(n*n+2);
        top =0;
        bottom= n*n +1;
    }
    boolean isOpen(int i , int j){
        return objects[i-1][j-1];
    }
    void open(int i, int j){
        objects[i-1][j-1] = true;
        if(i==1){
            this.li.union(top,formula(i,j));
        }
        if(i==_len){
            this.li.union(bottom,formula(i,j));
        }
        if(i<_len && isOpen(i+1, j)){
            li.union(formula(i,j),formula(i+1,j));
        }
        if(i>1 && isOpen(i-1,j)){
            li.union(formula(i,j),formula(i-1,j));
        }
        if(j<_len && isOpen(i,j+1)){
            li.union(formula(i,j),formula(i,j+1));
        }
        if(j>1 && isOpen(i,j-1)){
            li.union(formula(i,j),formula(i,j-1));
        }
    }
    boolean isFull(int row, int col){
        if((row >0 && col > 0) && (row<=_len && col <= _len)  ){
            return li.connected(formula(row,col),top);
        }
        else{
            throw new IndexOutOfBoundsException();
        }
    }
    boolean percolates(){
        return li.connected(top,bottom);
    }
    int formula(int i, int j){
        return _len*(i-1)+(j);
    }

}