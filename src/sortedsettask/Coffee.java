package sortedsettask;

public class Coffee implements Comparable<Coffee> {
    String type;

    public Coffee(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Coffee{" +
                "type='" + type + '\'' +
                '}';
    }

    @Override
    public int compareTo(Coffee coffee) {
        return this.type.compareTo(coffee.type);
    }
}
