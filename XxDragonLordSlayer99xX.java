package com.sanderhelleso.robo;

import java.awt.Color;
import robocode.*;

import java.util.Random;
import java.util.Vector;

/**
 * XxDragonLordSlayer99xX - A Robot fidget spinner
 *
 * @author Sander Lade Hellesø
 * @author Dag Eirik Vik
 * @author Ralf Leistad
 */

public class XxDragonLordSlayer99xX extends AdvancedRobot {

    // globals
    Random random = new Random();

    // color method
    private Color Color(int r, int g, int b, int a) {
        return new Color(r, g, b, a);
    }

    // main abilities and loop for robot
    @Override
    public void run() {
        // set pink color
        setAllColors(Color(255, 183, 255, 1));

        // robots run method
        while(true) {
            right();
        }
    }

    // move robot in a circular pattern to the right, with a little twist every round
    public void right() {
        int pattern = random.nextInt(10000) + 5000;
        setTurnRight(pattern);
        setMaxVelocity(5);
        ahead(pattern);
    }

    // move the robot in a in medium circluar pattern
    public void moveRobo() {
        // random movement pattern
        int pattern = random.nextInt(200) + 100;
        turnRight(pattern);
        ahead(pattern);
    }

    // fire a massive load when finding a enemy using the scanner
    public void onScannedRobot(ScannedRobotEvent e) {
        fire(3);
    }

    // run when robot hits enemy, backs off
    public void onBulletHit(BulletHitEvent event) {
        back(50);
    }

    // run when robot is hit by bullet
    @Override
    public void onHitByBullet(HitByBulletEvent event) {
        moveRobo();
    }

    @Override
    public Vector<HitByBulletEvent> getHitByBulletEvents() {
        moveRobo();
        return super.getHitByBulletEvents();
    }

    // run when colliding with robot
    @Override
    public void onHitRobot(HitRobotEvent event) {
        // fire a hard load on the enemy
        if (event.getBearing() > -10 && event.getBearing() < 10) {
            fire(3);
        }
        // continue moving if its our fault our robot got hit
        if (event.isMyFault()) {
            turnRight(10);
        }
    }
}
