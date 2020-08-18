package src.lesson6;

public class Parameters {
    enum params {
        NUMBER_VISITS_YEAR(300),
        CURRENT_YEAR(2020);

        private int value;

        params(int value) {
            this.value = value;

        }

        public int getValue() {
            return this.value;
        }
    }
}
