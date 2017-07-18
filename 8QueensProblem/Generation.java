/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queensga;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Mauricio G. Coello
 */
public class Generation {
    
    private static final Random rand = new Random(System.currentTimeMillis());

    
    private final int genSize;
    private final int bSize;
    private final float elitism;
    private final float mutation;
    private final float crossover;
    private Chromosome[] members;
    
    Generation(int _bSize, int _genSize, float _elitism, float _mutation, float _crossover){
        bSize = _bSize;
        genSize = _genSize;
        elitism = _elitism;
        mutation = _mutation;
        crossover = _crossover;
        members = new Chromosome[genSize];
        for (int i = 0; i < genSize; i++) {
            members[i] = new Chromosome(bSize);
        }
        Arrays.sort(members);
    }
    private Chromosome[] SelectParents() {
        Chromosome[] parents = new Chromosome[2];
        int sum, roulette, rSum;
        sum=0;
        rSum = 0;
        for (Chromosome member : members) {
            sum += member.GetFitness();
        }
        roulette = rand.nextInt(sum);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < members.length; j++) {
                rSum+=members[j].GetFitness();
                if(rSum > roulette)
                {
                    parents[i]=members[j];
                    j=genSize;
                }
            }
            rSum=0;
        }
        return parents;
    }
    public int[] Evolve() {
        Chromosome[] newGen = new Chromosome[genSize];
        
        int idx = Math.round(genSize * elitism);
        System.arraycopy(members, 0, newGen, 0, idx);

        while (idx < genSize) {
            if (rand.nextFloat() <= crossover) {
                Chromosome[] parents = SelectParents();
                Chromosome[] children = parents[0].Reproduce(parents[1]);

                if (rand.nextFloat() <= mutation) {
                    newGen[idx++] = children[0].Mutate();
                } else {
                    newGen[idx++] = children[0];
                }

                if (idx < newGen.length) {
                    if (rand.nextFloat() <= mutation) {
                        newGen[idx] = children[1].Mutate();
                    } else {
                        newGen[idx] = children[1];
                    }
                }
            } else {
                if (rand.nextFloat() <= mutation) {
                    newGen[idx] = members[idx].Mutate();
                } else {
                    newGen[idx] = members[idx];
                }
            }
            ++idx;
        }

        Arrays.sort(newGen);

        members = newGen;
        //System.out.println(Arrays.toString(members[members.length-1].GetBoard()) + "Fitness: " + members[members.length-1].GetFitness());
        if(members[members.length-1].GetFitness() == 28)
            return members[members.length-1].GetBoard();
        else
            return new int[1];
    }
    
}
