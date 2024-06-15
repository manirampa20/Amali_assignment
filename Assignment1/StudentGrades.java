import java.util.Scanner;

public class StudentGrades {
    public static  void main (String[] args){

    Scanner scanner = new Scanner(System.in);

    //Read the number of student
        System.out.println("Enter the number of student");

        int N = scanner.nextInt();

        //create an array to store scores
        int [] scores = new int[N];

        //Read in scores from user input
        System.out.println("Enter the score of " + N +" students");

        for(int i= 0; i < scores.length; i++){
            scores[i] = scanner.nextInt();

        }
        //search for maximum grades, minimum grades, average grade

        int maxGrade = Integer.MIN_VALUE;
        int minGrade = Integer.MAX_VALUE;

        //Iterate through the array to find minimum, maximum, average

        for(int score : scores){
            if(score > maxGrade){
                maxGrade = score;
            }
            if(score < minGrade){
                minGrade = score;
            }
            //Initialize variable to hold sum and count of grades

           int sum = 0;
            sum += score;

            //calculate the average grade
            double averageGrade = (double) sum/N;
        }
        //create array stats
        int [] stats = new int[5];
        for(int score: scores){
            if(score >= 0 && score <= 20){
                stats[0]++;
            }else if(score >= 21 && score <= 40){
                stats[1]++;
            } else if (score >= 41 && score <=60) {
                stats[2]++;

            }else if (score >= 61 && score <=80){
                stats[3]++;
            }else if(score >= 81 && score <=100){
                stats[4]++;
            }
        }
        // we are starting to work on graph
        int maximum = 0;

        for(int str : stats)
            if(str > maximum)
                maximum = str;

        //our for loop is going to start looping from the maximum on y-axis it will decrement up to the lowest adding ">"
        // "i" is representing any element we will loop over

        for (int i = maximum; i > 0; i--){
            System.out.print(i + " > ");

            //and here we loop over every element in the stats if it is equal or greater to the "i" print "#" otherwise
            //use tab to print spaces

            for(int stat : stats){
                if (stat >= i){
                    System.out.print("\t#######\t");
                }else {
                    System.out.print("\t\t\t");
                }
            }
            System.out.println();
        }

        //print the x-axis
        System.out.println("\t+-----------+-----------+-----------+-----------+-----------+\n");
        System.out.println("\tI     0-20  I   21-40   I    41-60  I    61-80  I    81-100 I\n");
    }


}
