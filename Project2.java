/*
 * Author: Michael Cerros
 * Course: COP 3503
 * Project #2
 * Title: Data from file Preprocessor
 * Due Date: 10/30/23
 * 
 * Takes input from csv and outputs new data in a new csv file
 */
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.io.File;

/*
 * Takes input from csv and outputs new data in a new csv file
 */

public class Project2 {

	public static ArrayList<String> dates = new ArrayList<String>();
	public static ArrayList<String> times = new ArrayList<String>();
	public static ArrayList<Double> sensor2278 = new ArrayList<Double>();
	public static ArrayList<Double> sensor3276 = new ArrayList<Double>();
	public static ArrayList<Double> sensor4689 = new ArrayList<Double>();
	public static ArrayList<Double> sensor5032 = new ArrayList<Double>();
	public static ArrayList<Double> section1Diff = new ArrayList<Double>();
	public static ArrayList<Double> section2Diff = new ArrayList<Double>();
	public static ArrayList<Double> totalAvg = new ArrayList<Double>();


	public static void main(String[] args)throws NumberFormatException, IOException {
		//variable Declarations
		System.out.println("Project 2 Data Preprocessing\n");
		Scanner scnr = new Scanner (System.in);
		boolean fileTruthVal = false;
		String line;
		String[] inputArray;

		while (!fileTruthVal)
		{
			try
			{
				//Get user & file input
				System.out.println("enter the name of the file you wish to sort:");
				String inputFile = scnr.next();
				System.out.println("Reading in Data from the file " + inputFile + "...");
				File inFile = new File(inputFile);
				FileReader fileReader = new FileReader(inFile);
				Scanner fileScnr = new Scanner(fileReader);
				
				fileScnr.nextLine();
					
				//parse file and sort data into respective ArrayList
				while (fileScnr.hasNextLine()) 
				{
					line = fileScnr.nextLine();	
					inputArray = line.split(",");
					
					//store each reversed date into dateReversed string
					String dateReversed = reverseDate(inputArray);
					
					dates.add(dateReversed);
					times.add(inputArray[1]);
					sensor2278.add(Double.valueOf(inputArray[2]));
					sensor3276.add(Double.valueOf(inputArray[3]));
					sensor4689.add(Double.valueOf(inputArray[4]));
					sensor5032.add(Double.valueOf(inputArray[5]));
				}//end nested while
				System.out.println("Formatting Dates to YYYY/MM/DD...");
				//method calls
				findDifference();
                findAverage();
                
                //declaration of fileWriter and printWriter objects
                FileWriter fileWriter = new FileWriter(inputFile.replace(".csv", "_Difference.csv"));
                PrintWriter printWriter = new PrintWriter(fileWriter);

                
                printWriter.write("Date,Time,Sensor_2278,Sensor_3276,Sensor_4689,Sensor_5032,Section1_Difference,Section2_Difference,Total_Average\n");

                for (int i = 0; i < dates.size(); i++) 
                {
                    printWriter.write(
                    dates.get(i) + "," +
                    times.get(i) + "," +
                    sensor2278.get(i) + "," +
                    sensor3276.get(i) + "," +
                    sensor4689.get(i) + "," +
                    sensor5032.get(i) + "," +
                    section1Diff.get(i) + "," +
                    section2Diff.get(i) + "," +
                    totalAvg.get(i) + "\n");
                }
				scnr.close();
				fileWriter.close();
				fileScnr.close();
				printWriter.close();
				fileReader.close();
				System.out.println("Done, Exiting Program!");
				fileTruthVal = true;	
			}//end try
			catch(FileNotFoundException e) 
			{
				System.out.println("No file found with such name. Try Again!");
				fileTruthVal = false;
				
				dates.clear();
	            times.clear();
	            sensor2278.clear();
	            sensor3276.clear();
	            sensor4689.clear();
	            sensor5032.clear();
	            section1Diff.clear();
	            section2Diff.clear();
	            totalAvg.clear();
			}//end catch
			catch(NumberFormatException e) 
			{
                System.out.println("The file being processed has a format error. Please input a different file.");
                fileTruthVal = false;
              
                dates.clear();
                times.clear();
                sensor2278.clear();
                sensor3276.clear();
                sensor4689.clear();
                sensor5032.clear();
                section1Diff.clear();
                section2Diff.clear();
                totalAvg.clear();
            }
			catch (ParseException e)
            {
                System.out.println("The file being processed has a parsing error. Please input a different file.");
                fileTruthVal = false;
                
                dates.clear();
                times.clear();
                sensor2278.clear();
                sensor3276.clear();
                sensor4689.clear();
                sensor5032.clear();
                section1Diff.clear();
                section2Diff.clear();
                totalAvg.clear();
            }
		}//end while 
	}//end main 
	/*
	 * sets the date formatting to yyyy/MM/dd.
	 * @param inputArray has the input needed to convert to specified format.
	 * @return The specified date format.
	 */
	public static String reverseDate(String[] inputArray) throws ParseException
	{
		SimpleDateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd");

        String originalDate = inputArray[0];
        Date date = originalFormat.parse(originalDate);
            
        return  outputFormat.format(date);	
	}
	/*
	 * finds the difference between the sensor speeds in the file and sorts into respective ArrayList.
	 * There is no paramater. 
	 * There is no return. 
	 */
	public static void findDifference() 
	{
		System.out.println("Calculating Speed Difference Between Sections...");
		for (int i = 0; i < sensor3276.size(); i++)
		{
            section1Diff.add(sensor2278.get(i) - sensor3276.get(i));
            section2Diff.add(sensor4689.get(i) - sensor5032.get(i));
        }
	}
	/*
	 * finds the average between the sensors speeds in the file and sorts into respective ArrayList for that line.
	 * There is no paramater. 
	 * There is no return. 
	 */

	public static void findAverage() 
	{
		System.out.println("Calculating Speed Average Between Days...");
		 for (int i = 0; i < sensor3276.size(); i++) 
		 {
	         totalAvg.add((sensor3276.get(i) + sensor2278.get(i) + sensor5032.get(i) + sensor4689.get(i)) / 4);
	     }
	}

}
