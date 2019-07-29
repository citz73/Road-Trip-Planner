import java.util.*;
import java.lang.*;
import java.io.*;

// Referenced geeksforgeeks for Traveling Salesman Algorithm
//https://www.geeksforgeeks.org/travelling-salesman-problem-implementation-using-backtracking/


// Traveling salesman assumes the existence of the Hamiltonian cycle and work on to find the graph
// with the minimal distance among the Hamiltonian cycles. Then it prints out such path with the minimum distance.

public class TravelingSalesman { 
    int[] cycle;  // stores the cycle
    ArrayList<Integer[]> storePermutation;
    int[][] matrix;
    HashMap<String, Integer> city;
    int V;


    public TravelingSalesman() {
        this.V = 0;
        storePermutation = new ArrayList<Integer[]>();
    }

    public TravelingSalesman(int[][] matrix, int V, HashMap<String, Integer> city) throws FileNotFoundException { // recieves all the data and saves it as global variable
        this.matrix = matrix;
        this.V = V;
        this.city = city;
        cycle = new int[V+1];
        storePermutation = new ArrayList<Integer[]>(); // should have storage for n-1!
        city = new HashMap<>(); 
    }

    public void stringMatch(String name) { // convert the string vertex back into integer vertex and also print out minimum distance and the cycle that creates minimum path.
        int vertex = city.get(name);
        int min = findMin(vertex);
        System.out.println("Min TSP in " + min);
        System.out.println();
        for(int i = 0; i < cycle.length; i++) {
            String eachKey = "";
            Integer value = cycle[i];
            for(Map.Entry inverse: city.entrySet()) { // find the key from the value (inverse mapping)
                if(value.equals(inverse.getValue())) {
                    eachKey = (String)inverse.getKey(); // casting is used
                    System.out.print(eachKey + " "); // write the key out 
                    break;
                }
            }
        }
        System.out.println();
    }


    public int findMin(int source){
        ArrayList<Integer> vertices = new ArrayList<Integer>();
        for(int i = 0; i < V; i++) { // add all the vertices in the array vertices except the starting node
            if(source == i) {
                continue;
            } else { // source != i
                vertices.add(i);
            }
        }
        int min = Integer.MAX_VALUE;
        permutation(vertices, 0); // permutate the cycle
        cycle[0] = source;
        for(Integer[] eachPermute: storePermutation) { 
            int cycleWeight = 0;
            int temp = source;
            for(int i = 0; i < eachPermute.length; i++) {
                // if(matrix[temp][eachPermute.get(i)] == 0) {  // break out if we see vertex is not connected. (no need to continue the search)
                //     break;
                // }
                cycleWeight += matrix[temp][eachPermute[i]];
                temp = eachPermute[i];
            }
            cycleWeight += matrix[temp][source];
            if(cycleWeight < min) {
                min = cycleWeight;
                for(int i = 0; i < eachPermute.length; i++) {  // get the minimum path 
                    cycle[i+1] = eachPermute[i];  
                }
                cycle[this.V] = source; 
            }
        }
        return min; // return the minimum we found
    }

    public void addTo(ArrayList<Integer> vertices){ // change arraylist to array and stores all the permutation
        Integer[] arr = new Integer[vertices.size()];
        for(int i = 0; i < arr.length; i++){
            arr[i] = vertices.get(i);
        }
        this.storePermutation.add(arr);
    }

    public void permutation(ArrayList<Integer> vertices, int s) { // s refers to starting index, find all the permutation of the vertices
        // Base Case
        if(s == vertices.size()-1) { // we fixed every value
            addTo(vertices);
        } else {
            for(int i = s; i < vertices.size(); i++) {
                Collections.swap(vertices, i, s);
                permutation(vertices, s+1);
                Collections.swap(vertices, i, s);
            }
        }  
    }


    public static void main (String[] args) throws FileNotFoundException {
        int graph[][] = { { 0, 10, 15, 20 }, 
					    { 10, 0, 35, 25 }, 
					    { 15, 35, 0, 30 }, 
					    { 20, 25, 30, 0 } }; 
	    int s = 2; 
        // ArrayList<Integer> vertices = new ArrayList<Integer>();
        // vertices.add(2);
        // vertices.add(3);
        // vertices.add(4);
        //HashMap<String, Integer> city = new HashMap<>();
        //TravelingSalesman travel = new TravelingSalesman(graph, 4, city);
        //System.out.println(travel.findMin(s));
        // travel.stringMatch("Denver");
        // travel.permutation(vertices,s);

        // int graph2[][] = {{0,    966,  1513, 2964, 1149, 927},
        //                   {966,  0,    2410, 1520, 1817, 729},
        //                   {1513, 2410, 0,    604,  481,  2742},
        //                   {2964, 1520, 604,  0,    595,  1289},
        //                   {1149, 1817, 481,  595,  0,    494},
        //                   {927,  729,  2742, 1289, 494,  0}};
        // int s = 0;
        //Scanner sc = new Scanner(new File("city2.txt"));
        // TravelingSalesman travel2 = new TravelingSalesman(graph2, 6, sc);
        //System.out.println(travel2.findMin(s));
        // travel2.stringMatch("Youngstown");                  

    }
}




