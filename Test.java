import java.util.*;
import java.lang.*;
import java.io.*;

public class Test { // test both use of Hamiltonian cycle and the TSP problem - use TSP that has Hamiltonian cycle and find minimum path  

    public HashMap<String, Integer> readIn(Scanner sc) throws FileNotFoundException {
        HashMap<String, Integer> city = new HashMap<>();
        while(sc.hasNext()){
            String cityName = sc.next();
            Integer v = sc.nextInt();
            city.put(cityName, v);
            System.out.println(cityName);
        }
        sc.close();
        System.out.println();
        return city;
    }

    public void checkCycle(int[][] graph, int V, HashMap<String, Integer> city, Scanner scan ) throws FileNotFoundException{
        
        HamiltonianCycle checkHamiltonian = new HamiltonianCycle(V,city);
        TravelingSalesman tsp = new TravelingSalesman(graph, V, city);
        String name = scan.nextLine();
        while(!city.containsKey(name)) {
            System.out.println("Invalid city. Please try Again");
            name = scan.nextLine();
        }
        System.out.println();
        if(checkHamiltonian.stringMatch(graph, name)) {
            tsp.stringMatch(name);
        } else {
            System.out.println("Hamiltonian Cycle does not exist");
        } 
    }

    public static void main(String[] args) throws FileNotFoundException {
        Test check = new Test();
        Scanner user = new Scanner(System.in);

        int[][] graph1 = {{0, 24, 56}, // real data (air distance)
                          {24, 0, 49},
                          {56,49, 0},
                        };
        int V = 3; 
        Scanner s1 = new Scanner(new File("city1.txt"));  
        HashMap<String, Integer> city = check.readIn(s1);
        check.checkCycle(graph1, V, city, user);
        System.out.println();                



        int[][] graph2 ={{0,    868,  2002, 463, 497, 346}, // real data (car distance)
                         {868,  0,    159, 1299, 387, 1025},
                         {2002, 1159, 0,    2385,  1504,  2185},
                         {463, 1299, 2385,  0,    912,  621},
                         {348, 1023, 2183,  625,  0,    4},
                         {346,  1025, 2185, 621, 4,  0}
                        };
        V = 6; 
        Scanner s2 = new Scanner(new File("city2.txt"));  
        city = check.readIn(s2);
        check.checkCycle(graph2, V, city, user);
        System.out.println();


        int[][] graph3 = {{0, 24, 56, 65, 164, 108, 140, 287}, // real data (car distance) 
                          {24, 0, 49, 65, 153, 82, 114, 276},
                          {66,49, 0, 101, 199, 61, 133, 322},
                          {65, 65, 101, 0, 154, 144, 176, 335},
                          {164, 153, 199, 154, 0, 230, 262, 229},
                          {108, 81, 61, 144, 230, 0, 60, 276},
                          {142, 115, 134, 178, 264, 63, 0, 248},
                          {287, 275, 321, 336, 229, 276, 246, 0}
                    }; 
        V = 8; 
        Scanner s3 = new Scanner(new File("city3.txt"));  
        city = check.readIn(s3);
        check.checkCycle(graph3, V, city, user);
        System.out.println();

        int[][] graph4 = {{0, 24, 56, 65, 164, 108, 140, 287,97, 179},  // real data
                        {24, 0, 49, 65, 153, 82, 114, 276, 70, 162},
                        {66,49, 0, 101, 199, 61, 133, 322, 89, 130},
                        {65, 65, 101, 0, 154, 144, 176, 335, 133, 214},
                        {164, 153, 199, 154, 0, 230, 262, 229, 218, 312},
                        {108, 81, 61, 144, 230, 0, 60, 276, 24, 107},
                        {142, 115, 134, 178, 264, 63, 0, 248, 45, 143},
                        {287, 275, 321, 336, 229, 276, 246, 0, 257, 434},
                        {97,70, 89, 133, 218, 24, 45, 257, 0, 122},
                        {180, 162, 130, 215, 312, 107, 141, 374, 122, 0}
                        }; 

        V = 10; 
        Scanner s4 = new Scanner(new File("city4.txt"));  
        city = check.readIn(s4);
        check.checkCycle(graph4, V, city, user);
        System.out.println();

        int[][] graph5 ={{0,  0,  0, 0, 0, 0},     // Assume the situation where the the connection flight is all disconnected at that hour frame
                         {0,  0,    0, 0, 0, 1025},
                         {0, 1159, 0,    0,  1504,  0},
                         {463, 0, 2385,  0,    912,  621},
                         {0, 1023, 0,  0,  0,    4},
                         {346,  0, 2185, 0, 4,  0}
                        };
        V = 6; 
        Scanner s5 = new Scanner(new File("city5.txt"));  
        city = check.readIn(s5);
        check.checkCycle(graph5, V, city, user);
        System.out.println();
        
    }
}

        



