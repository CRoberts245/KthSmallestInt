//Cameron Roberts

//ProgAssign2:KthSmallestInteger in an Array

package kthsmallest;
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;
public class KthSmallest
{
   private static Random random = new Random();
   private static Scanner keyboard = new Scanner(System.in);

   // Algorithm #1
   public static int select1(int k, int[] myArray)
   {
       if (myArray.length == 1)
           return myArray[0];

       int m = myArray[random.nextInt(myArray.length)];//random lengths for s1 s2 s3
       int[] Set1 = new int[myArray.length];
       int[] Set2 = new int[myArray.length];
       int[] Set3 = new int[myArray.length];
       int var1 = 0;
       int var2 = 0;
       int var3 = 0;

       for (int i = 0; i < myArray.length; i++)
       {
           if (myArray[i] < m)
           {
               Set1[var1] = myArray[i];
               var1++;
           }
           else if (myArray[i] == m)
           {
               Set2[var2] = myArray[i];
               var2++;
           }
           else
           {
               Set3[var3] = myArray[i];
               var3++;
           }
       }
       Set1 = trimFunc(Set1, var1);
       Set2 = trimFunc(Set2, var2);
       Set3 = trimFunc(Set3, var3);
       if (Set1.length >= k)
           return select1(k, Set1);
       else if (Set1.length + Set2.length >= k)
           return m;
       else
           return select1(k - Set1.length - Set2.length, Set3);
   }

   //  Algorithm #2
   public static int select2(int k, int[] myArray2)
   {      
       if (myArray2.length < 50)
       {
           Arrays.sort(myArray2, 0, myArray2.length - 1);
           return myArray2[k - 1];
       }

       for (int i = 0; i < myArray2.length; i += 5)
       {
           if (i + 4 < myArray2.length)
               Arrays.sort(myArray2, i, i + 4);
           else
               Arrays.sort(myArray2, i, myArray2.length - 1);
       }
      
       int[] M = new int[(int)Math.ceil(myArray2.length / 5.0)];
      
       for (int i = 0, j = 0; i < myArray2.length; i += 5, j++)
       {
           if (i + 4 < myArray2.length)
               M[j] = myArray2[(2 * i + 4) / 2];
           else
               M[j] = myArray2[(i + myArray2.length - 1) / 2];              
       }      
       int m = select2(M.length / 2 + 1, M);
       int[] Set1 = new int[myArray2.length];
       int[] Set2 = new int[myArray2.length];
       int[] Set3 = new int[myArray2.length];
       int p = 0;
       int q = 0;
       int r = 0;
       for (int i = 0; i < myArray2.length; i++)
       {
           if (myArray2[i] < m)
           {
               Set1[p] = myArray2[i];
               p++;
           }
           else if (myArray2[i] == m)
           {
               Set2[q] = myArray2[i];
               q++;

           }
           else
           {
               Set3[r] = myArray2[i];
               r++;
           }
       }
       Set1 = trimFunc(Set1, p);
       Set2 = trimFunc(Set2, q);
       Set3 = trimFunc(Set3, r);
       if (Set1.length >= k)
           return select2(k, Set1);
       else if (Set1.length + Set2.length >= k)
           return m;
       else
           return select2(k - Set1.length - Set2.length, Set3);
   }
   // trimFunc method
   private static int[] trimFunc(int[] arr, int size)
   {
       int[] tempVarArray = new int[size];
       for (int i = 0; i < size; i++)
       {
           tempVarArray[i] = arr[i];
       }
       return tempVarArray;
   }
  
   // printFunc method to print the array of integers
   public static void printFunc(int[] printArray)
   {
       for(int i = 0; i < printArray.length; i++)
       {
           System.out.print(printArray[i] + "    ");
           if(i % 10 == 9)//every 10 lines output newline
               System.out.println();
       }
   }

   // start main method
   public static void main(String[] args)
   {
       System.out.print("Array Size? ");
       int size = keyboard.nextInt();
       while(size < 1)
       {
           System.out.println("Size of the array should be > 0");
           System.out.print("Array Size? ");
           size = keyboard.nextInt();
       }
      
       System.out.print("Kth Value? ");
       int k = keyboard.nextInt();      
       while(k < 1 || k > size)
       {
           System.out.println("Value of k: (1-" + size + ")");
           System.out.print("Kth Value? ");
           k = keyboard.nextInt();
       }
      
      
       int[] S = new int[size];  
       for(int i = 0; i < size; i++)
       {
           S[i] = 100 + random.nextInt(900);//add rand ints into array
       }
      
       long Start= System.nanoTime();
       int test1 = select1(k, S);
       long End= System.nanoTime();
       long CPUTime1=End-Start;
       Start = System.nanoTime();
       int test2 = select2(k, S);
       End = System.nanoTime();
       long CPUTime2=End-Start;
       System.out.println(k + "th least value w/ Algorithm #1: " + test1);
       System.out.println(k + "th least value w/ Algorithm #2: " + test2);
       System.out.println("Nanosecond Time Alg#1: "+CPUTime1);
       System.out.println("Nanosecond Time Alg#2: "+CPUTime2);

   }
      // sort method
   private static void sort(int arr[], int start, int end)
   {
       if (start < end)
       {
           int pivot = arr[end];
           int i = (start - 1);

           for (int j = start; j < end; j++)
           {
               if (arr[j] <= pivot)
               {
                   i++;

                   int swapTemp = arr[i];
                   arr[i] = arr[j];
                   arr[j] = swapTemp;
               }
           }

           int swapTemp = arr[i + 1];
           arr[i + 1] = arr[end];
           arr[end] = swapTemp;

           int partitionIndex = i + 1;

           sort(arr, start, partitionIndex - 1);
           sort(arr, partitionIndex + 1, end);
       }
   }
   
} 