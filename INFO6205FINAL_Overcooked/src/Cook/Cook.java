package Cook;
import Evolution.Evolution;
/**
 * Created by Min Hu on 2018/11/28.
 */
public class Cook {
        public int score ;

        public int index = 8;

        public int[] position = new int[2];

        public int[] gene = new int[(int)Math.pow(2,8)];

        public int[][] copyRestaurant;

        public Cook() {
            score = 0;
        }

        //              4(top)
        //
        //    1(left)     0     3(right)
        //
        //              2(bottom)

        //5: delivery salmon to customers
        //6 : delivery shrimp to customers
        //7 : delivery burger to customers

        public void initialGene(){
            for(int i = 0; i<gene.length; i++){
                gene[i] = (int)(Math.random()*8);
            }
        }

        public void initialPosition(int row, int col){
            position[0] = (int)(1+Math.random()*row);
            position[1] = (int)(1+Math.random()*col);
        }

        public int getGeneByIndex(int index){
            return gene[index];
        }

        public void copyGene(int[] oldGene){
            for(int i = 0; i<gene.length; i++){
                this.gene[i] = oldGene[i];
            }
        }

        public void singleAction(int[][] map){

            int genePos = (int)(Math.random()*255);
            int gene = this.getGeneByIndex(genePos);

//            System.out.println(" gene: "+gene);

            switch (gene){
                case 0:
                    //STAY in the block
                    break;
                case 1:
                    moveLeft(map);
                    break;
                case 2:
                    moveDown(map);
                    break;
                case 3:
                    moveRight(map);
                    break;
                case 4:
                    moveUp(map);

                    break;
                case 5:
                    putSalmon(map);
                    break;
                case 6:
                    putShrimp(map);
                    break;
                case 7:
                    putMileTea(map);
                    break;
            }

//            System.out.println(" pos after: "+position[0]+" "+position[1]);
//            System.out.println(" score: "+score);
        }


        public void moveLeft(int[][] map){
            if(map[position[0]][position[1]-1] != 1){
                position[1]--;
            }
        }

        public  void moveRight(int[][] map){
            if(map[position[0]][position[1]+1]!= 1){
                position[1]++;
            }
        }

        public void moveUp(int[][] map){
            if(map[position[0]-1][position[1]] !=1){
                position[0]--;
            }
        }

        public void moveDown(int[][] map){
            if(map[position[0]+1][position[1]] !=1){
                position[0]++;
            }
        }

        public void putSalmon(int[][] map){
            if(map[position[0]][position[1]] == 5){
                score += 30;
                map[position[0]][position[1]] = 10;
            }else{
                score -=4;
            }
        }

        public void putShrimp(int[][] map){
            if(map[position[0]][position[1]] == 6){
                score += 30;
                map[position[0]][position[1]] = 10;
            }else{
                score -=4;
            }
        }

        public void putMileTea(int[][] map){
            if(map[position[0]][position[1]] == 7){
                score += 30;
                map[position[0]][position[1]] = 10;
            }else{
                score -=4;
            }
        }

        public void printGene(){
            for(int i=0;i<gene.length;i++){
                System.out.print(gene[i]);
            }
        }

    public void copyRestaurant(int[][] res){

        copyRestaurant = new int[Evolution.ROW + 2][Evolution.COL+2];
        for(int i=0;i<Evolution.ROW+2;i++){
            for(int j=0;j<Evolution.COL+2;j++){
                copyRestaurant[i][j]= res[i][j];
            }
        }

    }


}
