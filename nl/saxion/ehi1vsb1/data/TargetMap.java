package nl.saxion.ehi1vsb1.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Map containing targets
 *
 * @author Thymo van Beers
 * @author Tim Hofman
 */
public class TargetMap {
    private List<Target> targetList;

    public TargetMap() {
        targetList = new ArrayList<>();
    }

    /**
     * Find the target closest to the given position
     *
     * @param xPos X position
     * @param yPos Y position
     *
     * @return The closest target
     *
     * @author Tim Hofman
     */
    public Target getClosest(double xPos, double yPos) {
        //TODO: Find closest target based on given position
        Target closestTarget = new Target();

        for (int i = 0; i < targetList.size(); i++) {
            if (i == 0) {
                closestTarget = targetList.get(i);
            } else {
                double distanceToClosestTargetXPos = distanceToTarget(xPos, closestTarget.getxPos());
                double distanceToClosestTargetYPos = distanceToTarget(yPos, closestTarget.getyPos());
                double distanceToTargetXPos = distanceToTarget(xPos, targetList.get(i).getxPos());
                double distanceToTargetYPos = distanceToTarget(yPos, targetList.get(i).getyPos());

                if (distanceToTargetXPos < distanceToClosestTargetXPos && distanceToTargetYPos < distanceToClosestTargetYPos) {
                    closestTarget = targetList.get(i);
                } else if (distanceToTargetXPos < distanceToClosestTargetXPos) {
                    if (distanceToTargetXPos + distanceToTargetYPos < distanceToClosestTargetXPos + distanceToClosestTargetYPos) {
                        closestTarget = targetList.get(i);
                    }
                } else if (distanceToTargetYPos < distanceToClosestTargetYPos) {
                    if (distanceToTargetXPos + distanceToTargetYPos < distanceToClosestTargetXPos + distanceToClosestTargetYPos) {
                        closestTarget = targetList.get(i);
                    }
                }
            }
        }

        return closestTarget;
    }

    private double distanceToTarget(double pos, double targetPos) {
        if (pos >= targetPos) {
            return (pos - targetPos);
        } else {
            return (targetPos - pos);
        }
    }

    public void addTarget(Target target) {
        for (int i = 0; i < targetList.size(); i++) {
            if (target.getName().equals(targetList.get(i).getName())) {
                break;
            } else {
                targetList.add(target);
            }
        }
    }
}
