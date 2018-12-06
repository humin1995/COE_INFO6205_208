package Test;

import Cook.Cook;
import org.junit.Assert;
import org.junit.Test;

import Cook.Generation;

/**
 * Author: Qian Liao on 2018/11/28
 */

public class GenerationTest {

    @Test
    public void Crossover() {
        Cook cook1 = new Cook();
        Cook cook2 = new Cook();

        cook1.initialGene();
        cook2.initialGene();

        Generation g = new Generation(100);
        g.crossover(cook1,cook2);
        for(int i=1;i<cook1.gene.length;i=i+2){
            Assert.assertTrue(cook1.gene[i]==cook2.gene[i]);
        }
    }

    @Test
    public void generationInitialization(){
        Generation g = new Generation(100);
        for(int i=0;i<g.population;i++){
            Assert.assertTrue(g.generation[i].score ==0);
        }
    }
}
