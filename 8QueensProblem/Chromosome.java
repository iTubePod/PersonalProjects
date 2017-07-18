/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Random;

/**
 *
 * @author Mauricio González Coello
 */
public final  class Chromosome implements Comparable<Chromosome>{
    private final int sze;    
    private final int[] board;
    private int fitness;
    private final Random rand = new Random(System.currentTimeMillis());
    public Chromosome(int _sze){
        fitness=0;
        sze=_sze;
        for(int i=sze-1; i>0; i--){
            //Generates fitness max value based on the board size
            fitness+=i;
        }
        board = RandomGenerator();
        CheckCollisions();
    }
    public Chromosome(int[] _board, int _sze){
        fitness=0;
        sze=_sze;
        for(int i=sze-1; i>0; i--){
            fitness+=i;
        }
        board = _board;
        CheckCollisions();
    }
    //Generates queens' positions inside the board of n by n
    private int[] RandomGenerator(){
        int [] tmprtr = new int[sze];
        int tmp = rand.nextInt(Integer.MAX_VALUE);
        for (int i = 0; i < sze; i++) {
            tmprtr[i]= tmp%sze;
            tmp = rand.nextInt(Integer.MAX_VALUE);
        }
        return tmprtr;
    }
    
    //Returns board with queens' positions
    public int[] GetBoard(){
        return board;
    }
    
    //returns the chromosome's fitness score
    public int GetFitness(){
        return fitness;
    }
    
    //Checks if every queen attacks any other queen inside the board
    public void CheckCollisions(){
        int j=0;
        int k;
        for (int i = 0; i < sze; i++) {
            k=0;
            if(j!=sze-1)
            {
                for(j=i+1; j < sze; j++){
                    k++;
                    if (board[i]==board[j]+k){
                        fitness-=1;
                        //System.out.println("Queen at position (" + i + "," + board[i] + ")" + " attacks queen at (" + j + "," + board[j] + ") diagonally up");
                    }
                    else if(board[i]==board[j]-k)
                    {
                        fitness-=1;
                        //System.out.println("Queen at position (" + i + "," + board[i] + ")" + " attacks queen at (" + j + "," + board[j] + ") diagonally down");
                    }
                    else if(board[i]==board[j])
                    {
                        fitness-=1;
                        //System.out.println("Queen at position (" + i + "," + board[i] + ")" + " attacks queen at (" + j + "," + board[j] + ") lineal");
                    }
                }
            }
        }
    }
    
    //Mutate function
    public Chromosome Mutate(){
        int idx = rand.nextInt(Integer.MAX_VALUE) +1;
        idx%=sze;
        int mutation = rand.nextInt(Integer.MAX_VALUE) + 1;
        mutation%=sze;
        while(board[idx] == mutation){
            mutation = rand.nextInt(Integer.MAX_VALUE) + 1;
            mutation%=sze;
        }
        board[idx] = mutation;
        return this;
    }
    
    //Reproduce function, receives the mom and dad based on the selection of parents fucntion in the generation and returns 2 new chromosomes based on the genes of both parents
    public Chromosome[] Reproduce(Chromosome mom) {
        int pivot    = rand.nextInt(board.length);
        int[] son1 = new int[board.length];
        int[] son2 = new int[board.length];
        
        System.arraycopy(board, 0, son1, 0, pivot);
        System.arraycopy(mom.GetBoard(), pivot, son2, pivot, (son2.length - pivot));
        System.arraycopy(mom.GetBoard(), 0, son2, 0, pivot);
        System.arraycopy(board, pivot, son2, pivot, (son2.length - pivot));

        return new Chromosome[] { new Chromosome(son1, sze), new Chromosome(son2, sze)}; 
    }

    //Compare fucntion override for the sort of chromosome in an array based on the fitness score of it.
    @Override
    public int compareTo(Chromosome o) {
        if (fitness > o.fitness)
            return 1;
        else if (fitness < o.fitness)
            return -1;
        return 0;
    }
}
