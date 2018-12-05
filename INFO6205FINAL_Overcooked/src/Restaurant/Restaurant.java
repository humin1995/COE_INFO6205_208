package Restaurant;

/**
 * Author: Qian Liao 2018/11/27
 */
public class Restaurant {

    private int[][] restaurant;
    private int x;
    private int y;
/**
 * 0: cook stay
 * 1: wall
 * 5,6,7; customer orders
 * */
    public void generateRestaurant(int row, int col){
        this.x = row;
        this.y = col;
        this.restaurant = new int[row+2][col+2];

        // Initial each cell as 0
        for(int i = 0; i < row + 2; i++){
            for(int j = 0; j< col + 2; j++){
                restaurant[i][j] = 0;
            }
        }

        // Set walls for four directions
        for(int i = 0;i < row + 2; i++){
            restaurant[0][i] = 1;  //the top wall
            restaurant[i][0] = 1;  //the left wall
            restaurant[row+1][i] = 1; // the right wall
            restaurant[i][col+1] = 1; // the bottom wall
        }

        // put orders
        // Set 5 as salmon, Set 6 as Sweet Shrimp, Set 7 as milk tea
        for(int i = 1;i < row + 1; i++){
            restaurant[1][i] = (int)(Math.random()*3+5);
            restaurant[i][1] = (int)(Math.random()*3+5);
            restaurant[row][i] = (int)(Math.random()*3+5);
            restaurant[i][col] = (int)(Math.random()*3+5);
        }
    }

    public void showOrder(){
        int i,j;
        for(i=0;i<x+2;i++){
            for(j=0;j<y+2;j++){
                System.out.print(restaurant[i][j]+" ");
            }
            System.out.println();
        }
    }

    public void showOrderSymbol(){
        int i,j;
        for(i=0;i< x + 2;i++){
            for(j=0;j< y + 2;j++){
                if(restaurant[i][j]==1)
                    System.out.print("=");
                if(restaurant[i][j]== 5)
                    System.out.print("~"); // salmon
                if(restaurant[i][j]== 6)
                    System.out.print("*"); // shrimp
                if(restaurant[i][j]== 7)
                    System.out.print("&"); // burger
                if(restaurant[i][j]== 0)
                    System.out.print(" ");
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public int[][] getRestaurant() {
        return restaurant;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static void main(String[] args){
        Restaurant restaurant = new Restaurant();
        restaurant.generateRestaurant(4,4);
        restaurant.showOrder();
        restaurant.showOrderSymbol();
    }
}
