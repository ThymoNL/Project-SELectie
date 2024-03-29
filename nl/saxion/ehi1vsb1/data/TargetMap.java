package nl.saxion.ehi1vsb1.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Map containing targets
 *
 * @author Thymo van Beers
 * @author Sieger van Breugel
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
        Target closestTarget = null;

        System.out.println(targetList.size());
        for (int i = 0; i < targetList.size(); i++) {
            if (closestTarget == null) {
                for (int j = 0; j < targetList.size(); j++) {
                    if (!targetList.get(j).isFriendly()) {
                        closestTarget = targetList.get(j);
                        break;
                    }
                }
            } else {
                double distanceToClosestTargetXPos = distanceToTarget(xPos, closestTarget.getxPos());
                double distanceToClosestTargetYPos = distanceToTarget(yPos, closestTarget.getyPos());
                double distanceToTargetXPos = distanceToTarget(xPos, targetList.get(i).getxPos());
                double distanceToTargetYPos = distanceToTarget(yPos, targetList.get(i).getyPos());

                if (distanceToTargetXPos < distanceToClosestTargetXPos && distanceToTargetYPos < distanceToClosestTargetYPos && !targetList.get(i).isFriendly()) {
                    closestTarget = targetList.get(i);
                    System.out.println(closestTarget.getName());
                    break;
                } else if (distanceToTargetXPos < distanceToClosestTargetXPos && !targetList.get(i).isFriendly()) {
                    if (distanceToTargetXPos + distanceToTargetYPos < distanceToClosestTargetXPos + distanceToClosestTargetYPos) {
                        closestTarget = targetList.get(i);
                        break;
                    }
                } else if (distanceToTargetYPos < distanceToClosestTargetYPos && !targetList.get(i).isFriendly()) {
                    if (distanceToTargetXPos + distanceToTargetYPos < distanceToClosestTargetXPos + distanceToClosestTargetYPos) {
                        closestTarget = targetList.get(i);
                        break;
                    }
                }
            }
        }

        return closestTarget;
    }

    /**
     * Get the X or Y distance to the Target
     *
     * @param pos X or Y position
     * @param targetPos X or Y position of Target
     *
     * @return The X or Y distance to the Target
     *
     * @author Tim Hofman
     */
    private double distanceToTarget(double pos, double targetPos) {
        if (pos >= targetPos) {
            return (pos - targetPos);
        } else {
            return (targetPos - pos);
        }
    }

    /**
     * Adds a Target to the list with targets
     *
     * @param target The target that needs to be added to the list
     *
     * @author Tim Hofman
     */
    public void addTarget(Target target) {
        if (exists(target)) {
            removeTarget(target.getName());
        }
        targetList.add(target);
    }

    /**
     * Prints all targets from the list with targets.
     *
     * @author Tim Hofman
     */
    public void printTargets() {
        System.out.println("\n--------\nTargets");
        if (targetList.size() > 0) {
            for (Target target : targetList) {
                System.out.println("  Target: "   + target.getName()
                                    + "\n    X Position: " + target.getxPos()
                                    + "\n    Y Position: " + target.getyPos()
                                    + "\n    Friendly:   " + target.isFriendly()
                                    + "\n    Turn:       " + target.getTurn()
                                    + "\n");
            }
        }
    }

    /**
     * Check if target is already in map
     *
     * @param target Target to find
     * @return true - target exists; false - target does not exist
     * @author Thymo van Beers
     */
    private boolean exists(Target target) {
        for (Target targetToCheck : targetList) {
            if (target.getName().equals(targetToCheck.getName())) {
                return true;
            }
        }

        return false;
    }

    /**
     * Get a Target from the targetList based on name
     *
     * @param targetName - The name of the target to return
     * @return Target from targetList
     * @author Sieger van Breugel
     */
    public Target getTarget(String targetName) {
        for (Target target : targetList) {
            if (targetName.equals(target.getName())) {
                return target;
            }
        }
        return null;
    }

    /**
     * Removes a Target from the targetList based on the name
     *
     * @param targetName - The name of the target to remove
     * @author Thymo van Beers
     * @author Tim Hofman
     */
    public void removeTarget(String targetName) {
        for (int i = 0; i < targetList.size(); i++) {
            Target target = targetList.get(i);
            if (target.getName().equals(targetName))
                targetList.remove(i);
        }
    }

    /**
     * Gets the size of the targetList
     *
     * @return Size of targetList
     * @author Thymo van Beers
     */
    public int size() {
        return targetList.size();
    }
}
