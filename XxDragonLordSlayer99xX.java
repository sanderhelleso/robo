package com.sanderhelleso.robo;

import robocode.*;

import java.util.Random;

public class XxDragonLordSlayer99xX extends AdvancedRobot {

    // globals
    Random random = new Random();

    // main abilities and loop for robot
    @Override
    public void run() {
        while(true) {

            // random movement pattern
            int ahead = random.nextInt(200) + 100;
            int left = random.nextInt(100) + 50;
            int back = random.nextInt(200) + 100;
            int right = random.nextInt(100) + 50;

            turnLeft(left);
            ahead(ahead);
            turnRight(right);
            back(back);

            // fire power and dir
            fire(1);
            turnGunLeft(50);
        }
    }

    // run when robot hits enemy
    public void onBulletHit(BulletHitEvent event) {
        int move = random.nextInt(200) + 100;
        turnLeft(move);
        ahead(move);
    }

    // run when robot is hit by bullet
    @Override
    public void onHitByBullet(HitByBulletEvent event) {
        int move = random.nextInt(200) + 50;
        turnRight(move);
        back(move);
    }

    // run when scanning a robot
    @Override
    public void onScannedRobot(ScannedRobotEvent event) {
        ahead(500);
    }

    // run when colliding with wall
    @Override
    public void onHitWall(HitWallEvent event) {
        super.onHitWall(event);
        int move = random.nextInt(200) + 50;
        turnLeft(180);
        back(move);
    }

    // run when colliding with robot
    @Override
    public void onHitRobot(HitRobotEvent event) {
        super.onHitRobot(event);
        fire(3);
        fireBullet(10);
    }
}
