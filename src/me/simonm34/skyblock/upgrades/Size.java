package me.simonm34.skyblock.upgrades;

public class Size {
    private int level;
    private double price;

    public Size(int level, double price) {
        this.level = level;
        this.price = price;
    }

    public int getLevel() {
        return level;
    }
    public double getPrice() {
        return price;
    }
    public int getArea() {
        switch (level) {
            case 1:
                return 100;
            case 2:
                return 150;
            case 3:
                return 200;
            case 4:
                return 250;
            case 5:
                return 300;
        }
        return 100;
    }
}
