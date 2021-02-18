import java.util.Arrays;
public class heap<T extends Comparable<? super T>> //doesn't convention say we need to the class to be in caps?
{
    private T[] heap;
    private int lastIndex;
    private static final int DEFAULT_CAPACITY = 25;
    public heap()
    {
       this(DEFAULT_CAPACITY);
    }
    public heap(int length)
    {
       if(length < 1)//so that we don't crash the add method or mess up the array
       {
          length = DEFAULT_CAPACITY; 
       }
       @SuppressWarnings("unchecked")
       T[] temp = (T[])new Comparable[length+1]; //we start at index1 so extra +1
       heap = temp;
       lastIndex = 0;
    }
    public int add(T newEntry)
    {
       lastIndex++;
       int currentIndex = lastIndex;
       int parentIndex = currentIndex/2;
       int counter = 0;
       while((parentIndex > 0) && (heap[parentIndex].compareTo(newEntry) < 0))
       {
          heap[currentIndex] = heap[parentIndex];
          currentIndex = parentIndex;
          parentIndex = currentIndex/2;
          counter++;
       }
       heap[currentIndex] = newEntry;
       ensureCapacity();
       return counter;
    }
    private void ensureCapacity()
    {
       if(lastIndex == heap.length - 1)
       {
          heap = Arrays.copyOf(heap, heap.length *2);
       }
    }
    /* I use the add method to construct a heap
    @param T[] entries we all your entries in array form. we don't check if we have enough capacity
    */
    public int constructHeapUsingAdd(T[] entries)
    {
       int sum = 0;
       int  i = 0;
       while((entries != null) && (i != entries.length) && (entries[i] != null))//can't use for loop because if the array entries is not full it will cause an error
       {//the != entries.length is so that entries[100] won't get called
          sum += add(entries[i]); //I modified the add method to return the number of swaps per add method
          i++;
       }
       return sum;
    }
    /* I use the reheap method to construct a heap
    @param T[] entries we all your entries in array form. we don't check if we have enough capacity
    */
    public int constructHeapOptimal(T[] entries)
    {
       int i = 0;
       while((entries != null) && (i != entries.length) && (entries[i] != null))
       {
          heap[i+1] = entries[i];
          i++;
          lastIndex++;
          ensureCapacity();//because I add everything in at once so this must be here
       }
       int rootIndex = 0;
       if(lastIndex/2 > 0) //if not root then do this
       {
          rootIndex = lastIndex/2; //last non-leaf node
       }
       else //if root we stop so we never reach reheap(0)
       {
          return -1; 
       }
       int sum = 0;
       //int j = 0;
       while(rootIndex >=1)
       {
          sum += reheap(rootIndex);
          rootIndex -= 1;
       }
       return sum;
    }
    private int reheap(int currentRoot) //only breaks when heap is empty but it can't get called when heap is empty so it never breaks
    {
       int leftChild = currentRoot*2;
       T entry = heap[currentRoot];
       boolean done = false;
       int counter = 0;
       while(!done && leftChild <= lastIndex)
       {
             int biggestChildIndex = leftChild; //we need a variable to hold biggestChild
             if((leftChild+1 <= lastIndex) && (heap[biggestChildIndex].compareTo(heap[biggestChildIndex+1]) < 0))
             {
                biggestChildIndex++;
             }
             if(entry.compareTo(heap[biggestChildIndex]) < 0)
             {
                heap[currentRoot] = heap[biggestChildIndex];
                currentRoot = biggestChildIndex;
                leftChild = currentRoot*2;
                counter++;
             }
             else
             {
                done = true;   
             }
       }
       heap[currentRoot] = entry;
       return counter;
    }
    public T removeMax()
    {
       if(lastIndex != 0)
       {
          T temp = heap[1];
          heap[1] = heap[lastIndex];
          heap[lastIndex--] = null;
          reheap(1);
          return temp;
       }
       return null;
    }
    //This will return the first 10 values or as many values up to 10 as possible
    //Only works with if the class in the <> has the toString method.
    public String[] firstTenValues()
    {
       String[] values = new String[10];
       if(lastIndex >= 10)
       {
          for(int i = 0; i < 10; i++)
          {  
                values[i] = heap[i+1].toString();   
          }
       }
       else//lastIndex < 10 in this range: 1-9
       {
          int i = 0;
          while((i+1 <= lastIndex)) 
          {
             values[i] = heap[i+1].toString();
             i++;
          }
       }
       return values;
    }
    //a class for testing only
    public void display()
    {
       for(int i = 1; i <= lastIndex; i++)
       {
          System.out.print(heap[i].toString());
          if(i != lastIndex)
          {
             System.out.print(", ");
          }
       }
    }
}
