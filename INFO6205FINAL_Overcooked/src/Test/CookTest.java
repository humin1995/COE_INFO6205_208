package Test;

import org.junit.Assert;
import org.junit.Test;

import Cook.Cook;
import Restaurant.Restaurant;


/**
 * Author: Qian Liao on 2018/11/28
 */

public class CookTest {

    @Test
    public void copyGene() {
        Cook cook1 = new Cook();
        cook1.initialGene();
        Cook cook2 = new Cook();
        cook2.copyGene(cook1.gene);
        cook1.printGene();
        System.out.println();
        for(int i = 0;i < cook1.gene.length; i++) {
            Assert.assertEquals(cook1.gene[i], cook2.gene[i]);
        }
    }

    @Test
    public void moveUp() {
        Restaurant res = new Restaurant();
        res.generateRestaurant(10,10);
        Cook cook = new Cook();
        cook.position[0] = 3;
        cook.position[1] = 3;
        cook.moveUp(res.getRestaurant());
        Assert.assertTrue(cook.position[0]-3 == -1);
    }

    @Test
    public void moveRight() {
        Restaurant res = new Restaurant();
        res.generateRestaurant(10,10);
        Cook cook = new Cook();
        cook.position[0] = 3;
        cook.position[1] = 3;
        cook.moveRight(res.getRestaurant());
        Assert.assertTrue(cook.position[1]-3 == 1);
    }

    @Test
    public void putSalmon() {
        Restaurant res = new Restaurant();
        res.generateRestaurant(10,10);
        res.getRestaurant()[3][3] = 5;
        Cook cook = new Cook();
        cook.position[0] = 3;
        cook.position[1] = 3;
        cook.putSalmon(res.getRestaurant());
        Assert.assertTrue(res.getRestaurant()[3][3] == 10);
        Assert.assertTrue(cook.score == 30);
    }

    @Test
    public void copyRestaurant() {
        Restaurant res1 = new Restaurant();
        res1.generateRestaurant(10,10);
        res1.showOrder();
        Cook cook = new Cook();
        cook.copyRestaurant(res1.getRestaurant());
        for(int i = 0; i < res1.getX(); i++) {
            for(int j = 0; j < res1.getY(); j++){
                Assert.assertEquals(res1.getRestaurant()[i][j], cook.copyRestaurant[i][j]);
            }
        }
    }
}
