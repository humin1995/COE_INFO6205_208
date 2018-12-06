package Evolution;

import Cook.*;
import Restaurant.Restaurant;

/**
 * Author: Qian Liao on 2018/11/28
 */
public class Evolution {
    public static final int GENERATION = 2500;
    public static final int ROW = 10;
    public static final int COL = 10;
    public static final int COOK_NUM = 200;
    public static final int MOVE_TIMES = 100;
    public static final double MUTATION_RATE = 0.001;


    public static void main(String[] args){
        // Generate a new Restaurant
        Restaurant restaurant = new Restaurant();
        restaurant.generateRestaurant(ROW,COL);
        restaurant.showOrder();
        restaurant.showOrderSymbol();
        System.out.println();

        // Initialize the first generation
        Generation firstGeneration = new Generation(COOK_NUM);
        for(int i = 0; i < COOK_NUM; i++ ){
            firstGeneration.generation[i].initialPosition(ROW,COL);
            firstGeneration.generation[i].initialGene();
            firstGeneration.generation[i].printGene();
            System.out.println();
        }
        // Initialize N generations
        for(int N = 0; N < GENERATION ; N++){
            for(int cook = 0; cook < COOK_NUM; cook++){
                firstGeneration.generation[cook].initialPosition(ROW,COL);
                restaurant.generateRestaurant(ROW,COL);
                firstGeneration.generation[cook].copyRestaurant(restaurant.getRestaurant());
                for(int move = 0; move < MOVE_TIMES; move++){
                    firstGeneration.generation[cook].singleAction(restaurant.getRestaurant());
                }
            }
            int bestScore = firstGeneration.bestScore();
            System.out.println("The "+ N + " Generation Best Score: " + firstGeneration.bestScore()
            + " Average Score: " + firstGeneration.averageScore());
//            if(bestScore >250){
//                break;
//            }
            firstGeneration = firstGeneration.generateNewGeneration(); //error
        }

        // Bst score location
        int[] bestScoreCooks = firstGeneration.getBestScoreLocation();
        System.out.println("Best Gene:");
        for(int i=0;i < bestScoreCooks.length;i++){
            firstGeneration.generation[bestScoreCooks[i]].printGene();
            System.out.println();
        }

    }
}