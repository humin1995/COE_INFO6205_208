package Cook;
import Evolution.*;
/**
 * Created by Min Hu on 2018/11/28.
 */
public class Generation {
    public int population;
    public Cook[] generation;

    public Generation(int population){
        this.population = population;
        generation = new Cook[population];
        for(int i = 0; i<population; i++){
            generation[i] = new Cook();
        }
    }

    public void geneStatic(int genePosition){
        double[]number = {0,0,0,0,0,0,0,0};
        for(int i =0;i<population; i++){
            if(generation[i].gene[genePosition] == 0)
                number[0]++;
            else if(generation[i].gene[genePosition] == 1)
                number[1]++;
            else if(generation[i].gene[genePosition] == 2)
                number[2]++;
            else if(generation[i].gene[genePosition] == 3)
                number[3]++;
            else if(generation[i].gene[genePosition] == 4)
                number[4]++;
            else if(generation[i].gene[genePosition] == 5)
                number[5]++;
            else if(generation[i].gene[genePosition] == 6)
                number[6]++;
            else if(generation[i].gene[genePosition] ==7)
                number[7]++;
        }

        double sum = 0;
        for(int i = 0; i<number.length; i++){
            sum += number[i];

        }

        System.out.println("position of gene is "+genePosition);
        for(int i=0;i<number.length;i++){
            System.out.print("Ratio of "+ i +": "+ number[i]*100/sum+"%  ");
        }
        System.out.println();
    }

    public int bestScore(){
        int bestScore = 0;
        for(int i = 0; i<generation.length; i++){
            if(generation[i].score >bestScore){
                bestScore = generation[i].score;
            }
        }
        return bestScore;
    }



    public double averageScore(){
        double sum = 0;
        for(int i = 0; i<generation.length; i++){
            if(generation[i].score >0){
                sum += generation[i].score;
            }else{
                sum += 0.1;
            }
        }
        return sum/generation.length;
    }

    public int[] getBestScoreLocation(){
        int best = bestScore();
        int bestCount = 0;
        int[] resultContainer = new int[Evolution.COOK_NUM];

        for(int i=0;i<population;i++){
            if(generation[i].score==best){
//                resultContainer[bestCount] = generation[i].score;
                resultContainer[bestCount] = i;
                bestCount++;
            }
        }

        int[] result = new int[bestCount];
        for(int i=0;i<bestCount;i++){
            result[i] = resultContainer[i];
        }
        return result;
    }

    public int[] getGeneByPosition(int location){
        return generation[location].gene;
    }

    public int worstScore(){
        int worst = 0;
        for(int i =0; i<generation.length; i++){
            if(generation[i].score<worst){
                worst = generation[i].score;
            }
        }
        return worst;
    }


    public Generation generateNewGeneration(){

        Generation newGeneration = new Generation(population);

        selection(newGeneration);

//        newGeneration.printGenerationGene();

        reproduce(newGeneration);

        mutation(newGeneration);
//        System.out.println();

//        newGeneration.printGenerationGene();

        return newGeneration;
    }

    public void selection(Generation newGeneration){
        double sum = 0;
        int bestScore = bestScore();
        int worstScore = worstScore();
        double[] selectionRatio = new double[population];
        double[] circleRatio = new double[population];

        for(int i = 0; i<population; i++){
            if(generation[i].score >0)
                sum += generation[i].score;
            else
                sum += 0.1;
//            System.out.println("sum: "+sum);
        }

        for(int i = 0; i<population; i++){
            if(generation[i].score >0)
                selectionRatio[i] = generation[i].score/sum;
            else
                selectionRatio[i] = 0.1/sum;
//            System.out.println("selectionRatio: "+selectionRatio[i]);
        }

        for(int i =0; i<population; i++){
            if(i==0)
                circleRatio[i] = selectionRatio[i];
            else
                circleRatio[i] = circleRatio[i-1] + selectionRatio[i];

//            System.out.println("rate: "+circleRatio[i]);
        }

        for(int i = 0; i<population; i++){

            double a = Math.random();
            for(int j = 0; j<population-1; j++){
                if(a<circleRatio[0]){
                    newGeneration.generation[i].copyGene(this.generation[j].gene);
                    break;
                }
                else if(a>circleRatio[j] && a<circleRatio[j+1]){
                    newGeneration.generation[i].copyGene(this.generation[j+1].gene);
                    break;
                }

            }
//            System.out.println("newGeneration generation[]: "+newGeneration.generation[i].gene);
        }
    }

    public void reproduce(Generation newGeneration){
        Generation dupliGeneration = new Generation(newGeneration.population);
        for(int i = 0; i<dupliGeneration.population; i++){
            dupliGeneration.generation[i].copyGene(this.generation[i].gene);
        }

        for(int i = 0; i<newGeneration.population; i++){
            int spouse = (int)(Math.random()*newGeneration.population);
            while(spouse == i){
                spouse = (int)(Math.random()*newGeneration.population);
            }

            crossover(newGeneration.generation[i],dupliGeneration.generation[spouse]);
        }
    }

    public void crossover(Cook c1, Cook c2){

        if(c1.gene == null || c2.gene == null)
            return;
        if(c1.gene.length != c2.gene.length)
            return;
//        int size = c1.gene.length;
//        int a = (int)(Math.random()*size);
//        int b = (int)(Math.random()*size);
//
//        int min = a>b?b:a;
//        int max = a>b?a:b;  //generate random gene to cross
//
//        for(int i = min; i<max; i++){
//            c1.gene[i] = c2.gene[i];
//        }
        for(int i=1;i<c1.gene.length;i=i+2){
            c1.gene[i] = c2.gene[i];
        }
    }

    public void mutation(Generation newGeneration){
        for(int i=0;i<newGeneration.population;i++){
            for(int j=0;j<newGeneration.generation[i].gene.length;j++){
                double mutate = Math.random();
                if(mutate < Evolution.MUTATION_RATE){
                    int newGene = (int)(Math.random()*8);
                    newGeneration.generation[i].gene[j] = newGene;
                }
            }
        }
    }

    public void printGenerationGene(){
        for(int i = 0; i<population; i++){
            generation[i].printGene();
            System.out.println();
        }
    }
}
