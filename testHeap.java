import java.util.Scanner;
import java.io.*;
public class testHeap
{
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
       //Scan in entries
       File f = new File("src\\project4\\data.txt");
       Scanner file = new Scanner(f);//didn't check if the file exists because we know it exists
       Integer[] entries = new Integer[100];
       int i = 0;
       while(file.hasNextInt())
       {
          entries[i] = file.nextInt();
          i++;
       }//seems like my two methods work now we just need to actually do the project and test other cases in a different class
       file.close();
       FileWriter a = new FileWriter("src\\project4\\Output.txt", true);
       PrintWriter output = new PrintWriter(a);
       
       //make heap using add method
       heap<Integer> sequential_Insertions = new heap<>();
       int totalSwapsOfAddMethod = sequential_Insertions.constructHeapUsingAdd(entries); //gives me total swaps here too
       //print output to file output.txt
       for(int j = 0; j < 70; j++)
       {
          output.print("="); 
       }
       output.println();
       //print first 10 values
       output.print("Heap built using sequential insertions: ");
       String[] values1 = sequential_Insertions.firstTenValues();
       for(String x: values1)
       {
          output.print(x+",");
       }
       output.println("...");
       //print total number of swaps
       output.println("Number of swaps in the heap creation: "+totalSwapsOfAddMethod);
       
       //remove 10 values
       for(int j = 0; j < 10; j++)
       {
          sequential_Insertions.removeMax();
       }
       
       //print first 10 after remove
       output.print("Heap after 10 removals: ");
       values1 = sequential_Insertions.firstTenValues();
       for(String x: values1)
       {
          output.print(x+",");
       }
       output.println("...\n");
       
       //make heap using optimal method
       heap<Integer> optimalHeapCreation = new heap<>();
       int totalSwapsOfOptimal = optimalHeapCreation.constructHeapOptimal(entries);
       //print optimally created heap to output
       //first 10 values
       output.print("Heap built using optimal method: ");
       String[] values2 = optimalHeapCreation.firstTenValues();
       for(String x: values2)
       {
         output.print(x+",");
       }
       output.println("...");
       //Number of Swaps
       output.println("Number of swaps in the heap creation: "+totalSwapsOfOptimal);
       //remove 10 values
       for(int j = 0; j < 10; j++)
       {
          optimalHeapCreation.removeMax();
       }
       //first 10 values after removeMax operation
       output.print("Heap after 10 removals: ");
       values2 = optimalHeapCreation.firstTenValues();
       for(String x: values2)
       {
         output.print(x+",");
       }
       output.println("...");
       for(int j = 0; j < 70; j++)
       {
          output.print("="); 
       }
       output.println("\n");
       output.close();
    }
}
