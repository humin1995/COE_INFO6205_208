package Cook;

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


}
