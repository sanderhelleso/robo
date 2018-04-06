package com.sanderhelleso.robo;

import robocode.*;
import robocode.util.Utils;

import java.util.Random;
import java.util.Vector;

public class XxDragonLordSlayer99xX extends AdvancedRobot {

    // globals
    Random random = new Random();
    private RobotStatus status;

    // main abilities and loop for robot
    @Override
    public void run() {

        setTurnRadarRight(Double.POSITIVE_INFINITY);
        while(true) {

            // adjust gun at start
            setAdjustGunForRobotTurn(true);
            setAdjustRadarForGunTurn(true);
            turnRadarRight(360);

            // move
            moveRobo();
            scan();
        }
    }

    public void dodge() {
        // random movement pattern
        int ahead = random.nextInt(50) + 25;
        int right = random.nextInt(50) + 25;

        ahead(ahead);
        turnRight(right);
    }

    public void moveRobo() {
        // random movement pattern
        int ahead = random.nextInt(200) + 100;
        int left = random.nextInt(100) + 50;
        int back = random.nextInt(200) + 100;
        int right = random.nextInt(100) + 50;

        turnLeft(left);
        ahead(ahead);
        turnRight(right);
        back(back);
    }

    @Override
    public void onStatus(StatusEvent e) {
        super.onStatus(e);
        this.status = e.getStatus();

        // get position
        double currentY = e.getStatus().getY();
        double currentX = e.getStatus().getX();

        double arenaWidth = 800;
        double arenaHeight = 600;

        if (arenaWidth - currentX <= 100) {
            if (e.getStatus().getHeading() <= 90) {
                turnLeft(180);
                ahead(50);
            }

            else if (e.getStatus().getHeading() > 90 && e.getStatus().getHeading() <= 180) {
                turnRight(180);
                ahead(50);
            }
        }

        if (currentX - arenaWidth >= 100) {
            turnRight(180);
            ahead(50);
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {

        setTurnRadarRight(2.0 * Utils.normalRelativeAngleDegrees(getHeading() + e.getBearing() - getRadarHeading()));
        setTurnGunRight(getHeading() - getGunHeading() + e.getBearing());
        setFire(Math.min(400 / e.getDistance(), 3));
        fireBullet(10);
    }

    // run when robot hits enemy
    public void onBulletHit(BulletHitEvent event) {

    }

    // run when robot is hit by bullet
    @Override
    public void onHitByBullet(HitByBulletEvent event) {
        moveRobo();
    }

    // run when colliding with wall
    @Override
    public void onHitWall(HitWallEvent event) {
        super.onHitWall(event);
        /*int move = random.nextInt(200) + 50;
        turnLeft(180);
        back(move);*/
    }

    @Override
    public Vector<HitByBulletEvent> getHitByBulletEvents() {
        dodge();
        return super.getHitByBulletEvents();
    }

    // run when colliding with robot
    @Override
    public void onHitRobot(HitRobotEvent event) {
        super.onHitRobot(event);
        fire(3);
        fireBullet(10);
    }
}
