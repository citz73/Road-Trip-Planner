import java.util.*;
import java.lang.*;
import java.io.*;

/* 
Referenced geeksforgeeks for Hamiltonian Cycle Algorithm
https://www.geeksforgeeks.org/hamiltonian-cycle-backtracking-6/
*/

/*
Hamiltonian Cycle that takes in string turns into integer and runs on integer vertices and find
if Hamiltonian Cycle(cycle that vists every vertices without visiting it more than once) exists or not. 
The cycle search can start from any vertex.
*/

public class HamiltonianCycle {
    HashMap<String, Integer> city;
    int V;
    //int[][] G;

    public HamiltonianCycle(int V, HashMap<String, Integer> city) throws FileNotFoundException { // populate the hashamp
        this.V = V;
        this.city = city;
        city = new HashMap<>();
    }

    public boolean stringMatch(int[][]G, String name) { // Find the integer vertex that match the string 
        int vertex = city.get(name);
        return IsHamiltonian(G, vertex);
    }
    
    public boolean IsValidPath(int[][] G, int[] cycle, int index, int vertex) {
        if(G[cycle[index-1]][vertex] == 0) { // check if current node is connected with previous node
            return false;
        }
        for(int i=0; i < index; i++) { // check if the vertex has been added before
            if(cycle[i] == vertex) {
                return false;
            }
        }
        return true;
    }

    public boolean HamilRecurr(int[][] G, int[] cycle, int index) {
        // Base Case
        if(index == V) { // if all the path is filled up 
            if(G[cycle[index-1]][cycle[0]] > 0) { // check that the last node is connected with the first node thus creates a Hamiltonian cycle
                return true;
            } else {
                return false;
            }
        }
        for(int vertex = 0; vertex < V; vertex++) { // start from any index
            if(IsValidPath(G, cycle, index, vertex)) {
                cycle[index] = vertex;
                if(HamilRecurr(G, cycle, index+1)) {
                    return true;
                }
                cycle[index] = -1;    
            }
        }
        return false;
    }

    public void printHamiltonian(int[] cycle) { // prints out the path of cycle
        for(int i = 0; i < V; i++) { 
            //System.out.print(cycle[i] + " ");
        }
        //System.out.println(cycle[0]);
    }

    public boolean IsHamiltonian(int[][] G, int s) { // s refers to the starting vertex
        int[] cycle = new int[V];
        for(int i = 0; i < V; i++) {
            cycle[i] = -1;
        }
        cycle[0] = s;
        if(HamilRecurr(G, cycle, 1)) {
            printHamiltonian(cycle);
            return true;
        } else {
            System.out.println("cycle does not exist");
            return false;
        }
    }

    public static void main(String[] args) {
        int V = 6;
        // int graph1[][]      =  {{0, 1, 0, 1, 0}, 
        //                         {1, 0, 1, 1, 1}, 
        //                         {0, 1, 0, 0, 1}, 
        //                         {1, 1, 0, 0, 1}, 
        //                         {0, 1, 1, 1, 0}, 
        //                         }; 
    //HamiltonianCycleG H = new HamiltonianCycleG(V, sc);
    //System.out.println(H.IsHamiltonian(graph1,3));

    // int graph2[][] = {{0, 1, 0, 1, 0}, 
    //                   {1, 0, 1, 1, 1}, 
    //                   {0, 1, 0, 0, 1}, 
    //                   {1, 1, 0, 0, 0}, 
    //                   {0, 1, 1, 0, 0}, 
    //                 };  
    // System.out.println(H.IsHamiltonian(graph2,3));
    
    // int graph3[][] ={{0,    966,  1513, 2964, 1149, 927},
    //                  {966,  0,    2410, 1520, 1817, 729},
    //                  {1513, 2410, 0,    604,  481,  2742},
    //                  {2964, 1520, 604,  0,    595,  1289},
    //                  {1149, 1817, 481,  595,  0,    494},
    //                  {927,  729,  2742, 1289, 494,  0}};
    // int s = 0;  
    // int graph4[][] = {{0,1,1},
    //                   {1,0,1},
    //                   {1,1,0},
    //                   }; 
    // System.out.println(H.IsHamiltonian(graph3, s));                     
    }
}    

               
  