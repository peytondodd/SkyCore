package me.simonm34.skyblock.upgrades;

public class Members {
    private int level;
    private double price;

    public Members(int level, double price) {
        this.level = level;
        this.price = price;
    }

    public int getLevel() {
        return level;
    }
    public double getPrice() {
        return price;
    }
    public int getMembers() {
        switch (level) {
            case 1:
                return 2;
            case 2:
                return 4;
            case 3:
                return 6;
            case 4:
                return 8;
            case 5:
                return 10;
        }
        return 2;
    }
}
