package Test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import Restaurant.Restaurant;

/**
 * Author: Qian Liao on 2018/11/28
 */

public class RestaurantTest {

    @Test
    public void generateRestaurant() {
        Restaurant restaurant = new Restaurant();
        restaurant.generateRestaurant(15,15);
        for(int i = 0; i < restaurant.getX() + 2; i++){
            for(int j = 0; j< restaurant.getY() + 2; j++){
                Assert.assertTrue(restaurant.getRestaurant()[0][i] == 1);
                Assert.assertTrue(restaurant.getRestaurant()[i][0] == 1);
                Assert.assertTrue(restaurant.getRestaurant()[restaurant.getX()+1][i] == 1);
                Assert.assertTrue(restaurant.getRestaurant()[i][restaurant.getY()+1] == 1);
            }
        }
    }

    @Test
    public void showOrder() {
        Restaurant restaurant = new Restaurant();
        restaurant.generateRestaurant(15,15);
        restaurant.showOrder();
        System.out.println();
    }

    @Test
    public void showOrderSymbol() {
        Restaurant restaurant = new Restaurant();
        restaurant.generateRestaurant(15,15);
        restaurant.showOrderSymbol();
    }
}
