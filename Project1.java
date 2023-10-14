package prj1;

/*
* Author: Michael Cerros N01491428
* Course: COP3503
* Project #: 1
* Title : Word Statistics Program
* Due Date: 10/6/2023
*
* Finds statistics involved with a user input string
*/


import java.util.Scanner;
import java.util.Arrays;

public class Project1 {
/*
 * Finds the stats of the user input string
 */
	public static void main(String[] args) {
		
		Scanner scnr = new Scanner(System.in);
		
		System.out.println("Enter List of Words Separated by Spaces");
				
		//main variable declarations
		String userIn = scnr.nextLine();
		int userInt = 0;
		
		//menu loop that lets user pick which choice they want for their string
			do{
					System.out.println();
					System.out.println
							("Please make a selection:\n"
							+ "1) Display List Ordered\n"
							+ "2) Display Word Length\n"
							+ "3) Display List Statistics\n"
							+ "4) Number of Odd/Even Words\n"
							+ "5) Check for Prime Length\n"
							+ "6) Enter New Word List\n"
							+ "7) Quit Program\n");
					
				userInt = scnr.nextInt();
					
				if(userInt == 1) 
				{
					//orders list than prints it out 
					displayListOrdered(userIn);
				}
				else if(userInt == 2) 
				{
					//calculates length of each word and prints it out 
					displayLength(userIn);
				}
				else if(userInt == 3) 
				{
					/*
					 * find the shortest & longest word length 
					 * total number of chars and average word length 
					 * finds the amount of nouns in the string 
					 * also finds the word that re-occurs the most (if any)
					 */
					displayStats(userIn);
				}
				else if(userInt == 4) 
				{
					/*
					 * counts the length per word and determines 
					 * if that word length is even or odd
					 */
					evenOrOdd(userIn);
				}
				else if(userInt == 5) 
				{
					//checks input string length for prime
					primeCheck(userIn);
				}
				else if(userInt == 6) 
				{
					//lets user input a  new string to be checked 
					userIn = newString(userIn);
				}
				else if(userInt == 7) 
				{
					//quits program and closes scanner 
					scnr.close();
					System.exit(0);

				}
				else 
				{
					//user puts in invalid num
					System.out.println("invalid input");
					
				}
				}while(userInt != 7);//end do while 
		

			
	}//end main
	
	/*
	 * orders list and prints it out 
	 * @param input is the user input string 
	 */
	public static void displayListOrdered(String input) 
	{
		//create and initialize string array
		String[] words = input.split(" ");
      
		//sort array
        Arrays.sort(words);     
        
        //print out sorted array within brackets
        	System.out.print("[");
        	for (int i = 0; i < words.length; i++) 
        	{
        		System.out.print(words[i] + ", ");
        	}	//end for
        	System.out.println("]");
	}//end method
	
	/*
	 * Displays length of each word and prints it out 
	 * @param input is the user input string 
	 */
	public static void displayLength(String input)
	{
		//create and initialize string array
		String[] words = input.split(" ");
		/*
		 * prints out word at index i than print the 
		 * length of the word at index i
		 */
			for(int i = 0; i < words.length; i++) 
			{
			System.out.println(words[i] + " Length is " + words[i].length());
			}//end for
	}//end method
	
	/*
	 * finds the stats earlier mentioned in the
	 * main function 3rd option description 
	 * @param input is the user input string 
	 */
	public static void displayStats(String input)
	{
		/*create and initialize the variables
		 * ( separated by what they're used for )
		 */
		//shortest and longest word
		String[] words = input.split(" ");
		String shortestWord = words[0];
		String longestWord = words[0];
		int shortestWordNum = 0;
		int longestWordNum = 0;
		
		//total words & average length of all chars
		double totalChars = words[0].length();
		double  averageLength = 0;
		
		//noun count
		int countNoun = 0;
		
		//finding most frequent word 
		String freqWord="No mode";
		int count=0;
		int max=0;
			
		//loop runs through length of string array
			for(int i = 1; i < words.length; i++) 
			{    
				//finds shortest & longest word, amount of nouns, and the total chars
				if(shortestWord.length() > words[i].length())	
				{
					shortestWord = words[i];
					shortestWordNum = words[i].length();
				}//end if	
				
				if(longestWord.length() < words[i].length())	
				{
					longestWord = words[i];
					longestWordNum = words[i].length();
				}//end if	
				
				if(words[i - 1].charAt(0) >= 'A' && words[i - 1].charAt(0) <= 'Z')
				{
		        	   countNoun++; 	   
		        }//end if
				
				totalChars += words[i].length(); 
			}//end for
			
			//finds the word that occurs most in the string 
			for(int i=0;i< words.length;i++) 
			{
				String current= words[i];
				
					for(int j = i+1; j < words.length; j++) 
					{

						if(current.equalsIgnoreCase(words[j]))
						{
							count++; 
						}//end if
						
					}//end nested for
				
					if(count>max) 	
					{
						max=count;
						freqWord=current;
					}// end if
				
				
				}//end outer for
			
			//divides total chars by amount of words in string to get avg
			averageLength = totalChars/words.length;
			
			System.out.println("Min Word Lenght: " + shortestWordNum);
			System.out.println("Max Word Lenght: " + longestWordNum);
			System.out.println("Total Number of Characters: " + totalChars);
			System.out.printf("Average Word Length: %.2f", averageLength);
			System.out.println();
			System.out.println("the amount of words that are nouns is " + countNoun);
			System.out.println("the word that occurs the most frequently is " + freqWord);

	}
	
	/*
	 * calculates if a words length is even or odd
	 * and prints out the amount of odd or even
	 * words
	 * @param input is the user input string 
	 */
	public static void evenOrOdd(String input)
	{
		
		String[] words = input.split(" ");//split input string into string array
		int	countOdd = 0;
		int countEven = 0;
		for(int i = 0; i < words.length; i++) 
		{	
				if(words[i].length()  % 2 == 0)
				{
        	   	countEven++; 	   
				}
				else 
				{
				countOdd++;
				}
		}
		System.out.println("Number Even: " + countEven);
		System.out.println("Number Odd: " + countOdd);
		
	}
	
	
	/*
	 * calculates a words length to see if its
	 * length is a prime number and prints
	 * how many there are 
	 * @param input is the user input string 
	 */
	public static void primeCheck(String input) {
		//split input string
		String[] words = input.split(" ");
		int	countPrime = 0;
		
		//for loop runs through the length of string array
			for (int i = 0; i < words.length; i++) 
			{
				
			//create and initialize variables
	        int wordLength = words[i].length();
	        boolean isPrime = true;

	        //determines whether word at index i is prime
	        if (wordLength <= 1) 
	        {
	            isPrime = false; // 0 and 1 are not prime
	        }//end if
	        else 
	        {
	        		for (int j = 2; j <= Math.sqrt(wordLength); j++) 
	        		{
	        				if (wordLength % j == 0) 
	        				{
	        						isPrime = false;
	        							break;
	        				}//end if
	        		}//end nested
	        }//end else

	        if (isPrime)
	        {
	            countPrime++;
	        }//end if
	    }

		System.out.println("Number of Prime in list: " + countPrime);
	}
	
	/*
	 * lets user input a new string to be
	 * inspected
	 * @param input is the user input string 
	 */
	public static String newString(String input) //lets user enter new input String
	{
		//create and initialize variables 
		String newString = "";
		
		//prompt user
		System.out.println("Enter List of Words Separated by Spaces");
		
		//scan in in user data
		Scanner scnr = new Scanner(System.in);
		newString = scnr.nextLine();
		
		//return new string
				return newString;
	}

}