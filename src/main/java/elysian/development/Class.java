package elysian.development;

import java.util.*;


public class Class {

    public enum ClassType {
        STEALTH,
        HEAVY_DUTY,
        FIGHTER,
        AIR_DEFENSE
    }

    private ClassType type;
    private List<String> ships;
    private List<Weapons> weapons;
    private int missileRadius;
    private boolean hasStealth;
    private boolean hasAirDefense;
    private double shipSizeModifier;
    private int attackCooldownTurns;

    public Class(int selected) {
        this.weapons = new ArrayList<>();
        switch (selected) {
            case 1:
                this.type = ClassType.STEALTH;
                this.ships = Arrays.asList("Mini Sub", "Silent Cruiser");
                this.weapons.add(new Weapons("Torpedo", 1, false, false));
                this.missileRadius = 1;
                this.hasStealth = true;
                this.hasAirDefense = false;
                this.shipSizeModifier = 0.75;
                this.attackCooldownTurns = 3;
                break;
            case 2:
                this.type = ClassType.HEAVY_DUTY;
                this.ships = Arrays.asList("Battleship", "Destroyer");
                this.weapons.add(new Weapons("Cannon", 1, false, false));
                this.weapons.add(new Weapons("Missile", 2, true, false));
                this.missileRadius = 2;
                this.hasStealth = false;
                this.hasAirDefense = false;
                this.shipSizeModifier = 1.0;
                this.attackCooldownTurns = 1;
                break;
            case 3:
                this.type = ClassType.FIGHTER;
                this.ships = Arrays.asList("Gunboat", "Assault Ship");
                this.weapons.add(new Weapons("Rapid Torpedo", 1, false, true));
                this.missileRadius = 1;
                this.hasStealth = false;
                this.hasAirDefense = false;
                this.shipSizeModifier = 0.9;
                this.attackCooldownTurns = 1;
                break;
            case 4:
                this.type = ClassType.AIR_DEFENSE;
                this.ships = Arrays.asList("Radar Ship", "AA Frigate");
                this.weapons.add(new Weapons("Anti-Air Missile", 1, true, false));
                this.weapons.add(new Weapons("Sonar Ping", 0, false, false)); // utility
                this.missileRadius = 1;
                this.hasStealth = false;
                this.hasAirDefense = true;
                this.shipSizeModifier = 1.0;
                this.attackCooldownTurns = 2;
                break;
            default:
                throw new IllegalArgumentException("Invalid class type");
        }
    }

    public ClassType getType() { return type; }
    public List<String> getShips() { return ships; }
    public List<Weapons> getWeapons() { return weapons; }
    public int getMissileRadius() { return missileRadius; }
    public boolean hasStealth() { return hasStealth; }
    public boolean hasAirDefense() { return hasAirDefense; }
    public double getShipSizeModifier() { return shipSizeModifier; }
    public int getAttackCooldownTurns() { return attackCooldownTurns; }
}
