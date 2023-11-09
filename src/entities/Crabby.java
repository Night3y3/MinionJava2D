package entities;

import Main.Game;

import static utilz.Constants.EnemyConstants.*;

public class Crabby extends Enemy{
    public Crabby(float x, float y) {
        super(x, y, CRABBY_WIDTH, CRABBY_HEIGHT, CRABBY);
        initHitbox(x,y,(int)(22 * Game.SCALE),(int)(26  * Game.SCALE));
    }

    public void update(int[][] lvlData,Player player){
        updateMove(lvlData,player);
        updateAnimationTick();
    }

    private void updateMove(int[][] lvlData, Player player){
        if (firstUpdate){
            firstUpdateCheck(lvlData);
        }

        if (inAir){
            updateInAir(lvlData);
        }else {       // Enemy Patrolling
            switch (enemyState){
                case IDLE -> newState(RUNNING);
                case RUNNING -> {
                    if (canSeePlayer(lvlData,player))
                        turnTowardsPlayer(player);
                    if (isPlayerCloseForAttack(player))
                        newState(ATTACK);
                    move(lvlData);
                }
            }
        }
    }



}
