package Cook;

public class Cook {
        public int score ;

        public int index = 5;

        public int[] position = new int[2];

        public int[] gene = new int[(int)Math.pow(3,index)];

        public int[][] TraceMap;

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
        //7 : delivery milkTea to customers

        public void initialGene(){
            for(int i = 0; i<gene.length; i++){
                gene[i] = (int)(Math.random()*8);
            }
        }

        public void initialPosition(int row, int col){
            position[0] = (int)(Math.random()*row);
            position[1] = (int)(Math.random()*col);
        }

        public int getGeneByIndex(int index){
            return gene[index];
        }

        public void copyGene(int[] oldGene){
            for(int i = 0; i<oldGene.length; i++){
                this.gene[i] = oldGene[i];
            }
        }

        public void singleAction(int[][] map){
            int state[] = new int[5];

            state[0] = map[position[0]][position[1]];          // Current Position
            state[1] = map[position[0]][position[1]-1];        // Left
            state[2] = map[position[0]+1][position[1]];       // Bottom
            state[3] = map[position[0]][position[1]+1];       // Right
            state[4] = map[position[0]-1][position[1]];       // Top

            int genePos = (int)(Math.pow(3,4)*state[0]
                    +Math.pow(3,3)*state[1]
                    +Math.pow(3,2)*state[2]
                    +Math.pow(3,1)*state[3]
                    +Math.pow(3,0)*state[4]);

            int gene = this.getGeneByIndex(genePos);

//            System.out.println(" gene: "+gene);
//            System.out.println(" pos before: "+pos[0]+" "+pos[1]);

            switch (gene){
                case 0:
                    //STAY in the block
                    break;
                case 1:
                    moveLeft(map);
                    //replaceRoutewith4();
                    break;
                case 2:
                    moveDown(map);
                    //replaceRoutewith4();
                    break;
                case 3:
                    moveRight(map);
                    //replaceRoutewith4();
                    break;
                case 4:
                    moveUp(map);
                    //replaceRoutewith4();
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

//            recordTrace(map);

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
                score -=5;
            }
        }

        public void putShrimp(int[][] map){
            if(map[position[0]][position[1]] == 6){
                score += 30;
                map[position[0]][position[1]] = 10;
            }else{
                score -=5;
            }
        }

        public void putMileTea(int[][] map){
            if(map[position[0]][position[1]] == 7){
                score += 30;
                map[position[0]][position[1]] = 10;
            }else{
                score -=5;
            }
        }

        public void printGene(){
            for(int i=0;i<gene.length;i++){
                System.out.print(gene[i]);
            }
        }

//        public void replaceRoutewith9(){
//        TraceMap[position[0]][position[1]]=9;
//    }

        public void showRouteInSymbol(){
            int i,j;
            for(i=0;i<EVOLUTION.ROW+2;i++){
                for(j=0;j<EVOLUTION.ROW+2;j++){
                    if(TraceMap[i][j]==10)
                        System.out.print("◆");

                    if(TraceMap[i][j]==1)
                        System.out.print("=");
                    if(TraceMap[i][j]== 0)
                        System.out.print(" ");

                    System.out.print(" ");
                }
                System.out.println();
            }
    }
}
