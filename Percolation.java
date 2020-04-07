/* *****************************************************************************
  *  Name: J Sri Krishna
  *  Date: 5-04-2020
  *  Description:
**************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class Percolation { private boolean[][] objects;
    private final  int _len;
    private final  int top;
    private final  int bottom;
    private WeightedQuickUnionUF li;
    private int opened;
    public Percolation(int n) { objects = new boolean[n][n];
        _len = n;
        li = new WeightedQuickUnionUF(n*n+2);
        top = 0;
        bottom =  n*n +1;
        opened = 0;
    }
    public boolean isOpen(int i, int j) {
        return objects[i-1][j-1];
    }
    public void open(int i, int j) { opened++;
        objects[i-1][j-1] = true;
        if (i == 1) { this.li.union(top, formula(i, j)); }
        if (i == _len) { this.li.union(bottom, formula(i, j));
        }
        if (i < _len && isOpen(i+1, j)) { li.union(formula(i, j), formula(i+1, j));
        }
        if (i > 1 && isOpen(i-1, j)) { li.union(formula(i, j), formula(i-1, j));
        }
        if (j < _len && isOpen(i, j+1)) { li.union(formula(i, j), formula(i, j+1));
        }
        if (j > 1 && isOpen(i, j-1)) { li.union(formula(i, j), formula(i, j-1));
        }
    }
    public boolean isFull(int row, int col) { if ((row > 0 && col > 0) && (row <= _len && col <= _len)) { return li.connected(formula(row, col), top);
        }
        else { throw new IndexOutOfBoundsException();
        }
    }
    public boolean percolates() { return li.connected(top, bottom);
    }
    private int formula(int i, int j) { return _len*(i-1)+(j);
    }
    public int numberOfOpenSites() { return opened;
    }

}