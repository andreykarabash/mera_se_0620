package src.lesson10;

public class Parameters {
    enum parameters {
        MAZE_SIZE_X(21),
        MAZE_SIZE_Y(21),
        EXITS_NUMBER(3),
        MAX_DIGGER_STEP(5);

        private int value;

        parameters(int value) {
            this.value = value;

        }

        public int getValue() {
            return this.value;
        }
    }
}
