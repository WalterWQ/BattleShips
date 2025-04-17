package elysian.development;

public class Weapons {
    private String name;
    private int radius;
    private boolean splashDamage;
    private boolean rapidFire;

    public Weapons(String name, int radius, boolean splashDamage, boolean rapidFire) {
        this.name = name;
        this.radius = radius;
        this.splashDamage = splashDamage;
        this.rapidFire = rapidFire;
    }

    public String getName() { return name; }
    public int getRadius() { return radius; }
    public boolean isSplashDamage() { return splashDamage; }
    public boolean isRapidFire() { return rapidFire; }

    @Override
    public String toString() {
        return name + " (Radius: " + radius +
                ", Splash: " + splashDamage +
                ", Rapid: " + rapidFire + ")";
    }
}
