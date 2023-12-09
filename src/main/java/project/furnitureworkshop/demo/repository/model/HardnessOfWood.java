package project.furnitureworkshop.demo.repository.model;

public enum HardnessOfWood {

    SOFT("soft"),
    HARD("hard"),
    SUPERHARD("superhard");

    final String value;

    public String getValue() {
        return value;
    }

    HardnessOfWood(String value) {
        this.value = value;
    }

    public static HardnessOfWood findByValue(String value) {
        HardnessOfWood result = null;
        for (HardnessOfWood hardness : values()) {
            if (hardness.getValue().equalsIgnoreCase(value)) {
                result = hardness;
                break;
            }
        }
        return result;
    }

}
