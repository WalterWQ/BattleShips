package elysian.development;

public class Weapons {
    private String name;
    private int radius;
    private boolean splashDamage;
    private boolean rapidFire;
    private int cooldown;
    private int currentcooldown;

    public Weapons(String name, int radius, boolean splashDamage, boolean rapidFire, int cooldown) {
        this.name = name;
        this.radius = radius;
        this.splashDamage = splashDamage;
        this.rapidFire = rapidFire;
        this.cooldown = cooldown;
        this.currentcooldown = 0;
    }

    public String getName() { return name; }
    public int getRadius() { return radius; }
    public int getCooldown() { return cooldown; }
    public boolean isSplashDamage() { return splashDamage; }
    public boolean isRapidFire() { return rapidFire; }
    public boolean inOnCooldown() {
        return currentcooldown > 0;
    }

    public int currentcooldown() {
        return currentcooldown;
    }

    public void increaseCooldown(int turns) {
        currentcooldown = turns;
    }

    public void reduceCooldown() {
        if (currentcooldown > 0) currentcooldown--;
    }

    @Override
    public String toString() {
        String status = inOnCooldown() ? "COOLDOWN: " + currentcooldown + " turn(s)" : "READY";
        return name + " | Radius: " + radius +
                " | Splash: " + splashDamage +
                " | Rapid: " + rapidFire +
                " | " + status;
    }
}
