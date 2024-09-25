package mru.a1.application;


import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.Objects;
import java.util.Scanner;

/**
 * The class contains methods to read from a file, calculate scores
 * based on applicant data, and write the results to an output file. It calculates 
 * various aspects such as age, language skills, education, work experience, and 
 * adaptability, to determine if applicants are qualified.
<<<<<<< HEAD
=======
 * 
 * The main function processes the input and generates an output file showing 
 * the qualified applicants.
 * the package that was imported was a object that will be used through out this class 
>>>>>>> 54acaab13b43d076e93684ffffef983757e1617e
 */

public class App {

	/**
<<<<<<< HEAD
     * @param string arguments
=======
     * The main method serves as the entry point of the application this is where all the other methods interact.
     * It reads input from a file, calculates points for each applicant,
     * and writes the results to an output file.
     * 
     * @param args contains command-line arguments; not used in this case.
>>>>>>> 54acaab13b43d076e93684ffffef983757e1617e
     * @throws Exception if an error occurs during file reading or writing.
     * @author chide
     */
    public static void main(String[] args) throws Exception {
        System.out.println("COMP 1502: Assignment 1");
        //at this point we are creating a user input in java 
        Scanner scanner = new Scanner(System.in);

        System.out.print("Provide the name of the input file:  ");
        String inputFile = scanner.nextLine();
        System.out.print("Provide the name of the output file:  ");
        String outputFile = scanner.nextLine();

        // Create a File instance for the desired file
        File file = new File("rsc/" + inputFile);
        Scanner scanner2 = new Scanner(file);
        // print writer output file
        FileWriter printer = new FileWriter("rsc/" + outputFile);
        PrintWriter pw = new PrintWriter(printer);

        pw.printf("%-20s|%-20s|%5s|%5s%n", "First Name", "Last Name", "Age", "Score");
        pw.println("--------------------+--------------------+-----+-----");

        // initializing qualified number of applicants
        int qualified = 0;
        scanner2.nextLine();
       
        // While the scanner has another line, read the file
        while (scanner2.hasNextLine()) {

            String line = scanner2.nextLine();
            String[] applicant = line.split("\t");

            String firstName = applicant[0];
            String lastName = applicant[1];
            int age = Integer.parseInt(applicant[2]);
            String maritalStatus = applicant[3];
            int speak1 = Integer.parseInt(applicant[4]);
            int listen1 = Integer.parseInt(applicant[5]);
            int read1 = Integer.parseInt(applicant[6]);
            int write1 = Integer.parseInt(applicant[7]);
            boolean all2 = applicant[8].equals("yes");
            String education = applicant[9];
            int workExperience = Integer.parseInt(applicant[10]);
            boolean arrangedEmployment = applicant[11].equals("yes");
            boolean adaptabilitySpouseLanguage = applicant[12].equals("yes");
            boolean adaptabilitySpouseEducation = applicant[13].equals("yes");
            boolean adaptabilitySpouseWork = applicant[14].equals("yes");
            boolean adaptabilityYouEducation = applicant[15].equals("yes");
            boolean adaptabilityYouWork = applicant[16].equals("yes");
            boolean adaptabilityYouEmployment = applicant[17].equals("yes");
            boolean adaptabilityRelatives = applicant[18].equals("yes");
            //taking the applicant values and storing them as int variables and sending them to functions which calculate points and return value as an int
            int agePoints = age(age);
            int languageSkills = languageSkills(speak1, listen1, read1, write1);
            int secondaryLanguageSkills = secondaryLanguageSkillsCalculator(all2);
            int educationPoints = educationCalculator(education);
            int workExperiencePoints = calculateWorkExperiencePoints(workExperience);
            int arrangedEmploymentPoints = arrangedEmployment(arrangedEmployment);
            int adaptabilityPoints = adaptabilityCalculator(adaptabilitySpouseLanguage, adaptabilitySpouseEducation,
                    adaptabilitySpouseWork, adaptabilityYouEducation, adaptabilityYouWork, adaptabilityYouEmployment,
                    adaptabilityRelatives);
            // taking all the int values and adding them to determine if applicant is qualified
            int totalPoints = (agePoints + languageSkills + secondaryLanguageSkills + educationPoints +
                    workExperiencePoints + arrangedEmploymentPoints + adaptabilityPoints);
         
            // -20(negative) is to align to the left and 5 (positive) is to align to the right
            if (totalPoints >= 67) {
                pw.printf("%-20s|%-20s|%5d|%5d%n", firstName, lastName, age, totalPoints);

                qualified++; 

            }

        }

        System.out.println("There are " + qualified + " qualified applicants");
        // Close the scanner
        scanner2.close();
        scanner.close();
        pw.close();

    }
    /**
     * Calculates points for secondary language skills.
     * 
     * @param all2 boolean indicating whether all language abilities are present.
     * @return the points for secondary language skills (maximum 4 points).
     */
    // Secondary language point calculator
    public static int secondaryLanguageSkillsCalculator(boolean all2) {
        int secondaryLanguagePoints = 0;
        if (all2) {
            secondaryLanguagePoints = 4;
        }
        return secondaryLanguagePoints;
    }
    /**
     * Calculates adaptability points based on various factors.
     * 
     * @param adaptabilitySpouseLanguage boolean indicating if spouse has language skills.
     * @param adaptabilitySpouseEducation boolean indicating if spouse has education.
     * @param adaptabilitySpouseWork boolean indicating if spouse has work experience.
     * @param adaptabilityYouEducation boolean indicating if the applicant has education.
     * @param adaptabilityYouWork boolean indicating if the applicant has work experience.
     * @param adaptabilityYouEmployment boolean indicating if the applicant has arranged employment.
     * @param adaptabilityRelatives boolean indicating if the applicant has relatives in the country.
     * @return the adaptability points (maximum 10 points).
     */
    // Adaptability point calculator
    public static int adaptabilityCalculator(boolean adaptabilitySpouseLanguage, boolean adaptabilitySpouseEducation,
            boolean adaptabilitySpouseWork, boolean adaptabilityYouEducation, boolean adaptabilityYouWork,
            boolean adaptabilityYouEmployment, boolean adaptabilityRelatives) {
        int adaptabilityPoints = 0;

        if (adaptabilitySpouseLanguage) {
            adaptabilityPoints += 5;
        } else if (adaptabilitySpouseEducation) {
            adaptabilityPoints += 5;
        } else if (adaptabilitySpouseWork) {
            adaptabilityPoints += 5;
        } else if (adaptabilityYouEducation) {
            adaptabilityPoints += 5;
        } else if (adaptabilityYouWork) {
            adaptabilityPoints += 10;
        } else if (adaptabilityYouEmployment) {
            adaptabilityPoints += 5;
        } else if (adaptabilityRelatives) {
            adaptabilityPoints += 5;
        }

        if (adaptabilityPoints >= 10) {
            adaptabilityPoints = 10;
        }

        return adaptabilityPoints;
    }
    /**
     * Calculates points for arranged employment.
     * 
     * @param arrangedEmployment boolean indicating if arranged employment is present.
     * @return the points for arranged employment (maximum 10 points).
     */
    // Arranged employment point calculator
    public static int arrangedEmployment(boolean arrangedEmployment) {
        int arrangedEmploymentPoints = 0;
        if (arrangedEmployment) {
            arrangedEmploymentPoints = 10;
        }
        return arrangedEmploymentPoints;
    }
    /**
     * Calculates points based on the applicant's education level.
     * 
     * @param education a string representing the applicant's education level.
     * @return the points for education (maximum 25 points).
     */
    // education point calculator
    public static int educationCalculator(String education) {
        int educationPoints = 0;
        if (education.equals("Secondary school (high school diploma)")) {
            educationPoints = 5;
        } else if (education.equals("One-year degree, diploma or certificate")) {
            educationPoints = 15;
        } else if (education.equals("Two-year degree, diploma or certificate")) {
            educationPoints = 19;
        } else if (education.equals("Bachelor's degree or other programs (three or more years)")) {
            educationPoints = 21;
        } else if (education.equals("Two or more certificates, diplomas, or degrees")) {
            educationPoints = 22;
        } else if (education.equals("Professional degree needed to practice in a licensed profession")) {
            educationPoints = 23;
        } else if (education.equals("University degree at the Master's level")) {
            educationPoints = 23;
        } else if (education.equals("University degree at the Doctoral (PhD) level")) {
            educationPoints = 25;
        }
        return educationPoints;
    }

    // age point calculator
    /**
     * Calculates points based on the applicant's age.
     * 
     * @param age the age of the applicant.
     * @return the points for age (maximum 12 points).
     */
    public static int age(int age) {

        int agePoints = 0;

        if (age < 18 || age > 47) {
            agePoints = 0;
        } else if (age >= 18 && age <= 35) {
            agePoints = 12;
        } else if (age == 36) {
            agePoints = 11;
        } else if (age == 37) {
            agePoints = 10;
        } else if (age == 38) {
            agePoints = 9;
        } else if (age == 39) {
            agePoints = 8;
        } else if (age == 40) {
            agePoints = 7;
        } else if (age == 41) {
            agePoints = 6;
        } else if (age == 42) {
            agePoints = 5;
        } else if (age == 43) {
            agePoints = 4;
        } else if (age == 44) {
            agePoints = 3;
        } else if (age == 45) {
            agePoints = 2;
        } else if (age == 46) {
            agePoints = 1;
        }
        return agePoints;
    }
    
    /**
     * Calculates points based on the applicant's work experience.
     * 
     * @param workExperience the number of years of work experience.
     * @return the points for work experience (maximum 15 points).
     */
    // Experience point calculator
    
    public static int calculateWorkExperiencePoints(int workExperience) {

        int workPoints = 0;

        if (workExperience >= 6) {
            workPoints = 15;
        } else if (workExperience >= 4) {
            workPoints = 13;
        } else if (workExperience >= 2) {
            workPoints = 11;
        } else if (workExperience == 1) {
            workPoints = 9;
        }
        // No work experience gives 0 points, which is already the default value of
        // workPoints
        return workPoints;
    }
    /**
     * Calculates points for language skills in speaking, listening, reading, and writing.
     * 
     * @param speak1 the speaking skill level.
     * @param listen1 the listening skill level.
     * @param read1 the reading skill level.
     * @param write1 the writing skill level.
     * @return the total points for language skills (maximum 24 points).
     */
    // Language skills point calculator
    public static int languageSkills(int speak1, int listen1, int read1, int write1) {
        int languagePoints = 0;

        if (speak1 >= 9) {
            languagePoints += 6;
        } else if (speak1 == 8) {
            languagePoints += 5;
        } else if (speak1 == 7) {
            languagePoints += 4;
        }

        if (listen1 >= 9) {
            languagePoints += 6;
        } else if (listen1 == 8) {
            languagePoints += 5;
        } else if (listen1 == 7) {
            languagePoints += 4;
        }

        if (read1 >= 9) {
            languagePoints += 6;
        } else if (read1 == 8) {
            languagePoints += 5;
        } else if (read1 == 7) {
            languagePoints += 4;
        }

        if (write1 >= 9) {
            languagePoints += 6;
        } else if (write1 == 8) {
            languagePoints += 5;
        } else if (write1 == 7) {
            languagePoints += 4;
        }

        return languagePoints;
    }

}
