package main;

public class Main {

    public static void main(String[] args) {

        Transition[] transitions = new Transition[]{
                new Transition("q0", 'a', "q1", 'x', MoveDirection.Right),
                new Transition("q1", 'a', "q1", 'a', MoveDirection.Right),
                new Transition("q1", '0', "q1", '0', MoveDirection.Right),
                new Transition("q1", 'b', "q2", '0', MoveDirection.Left),
                new Transition("q2", '0', "q2", '0', MoveDirection.Left),
                new Transition("q2", 'a', "q2", 'a', MoveDirection.Left),
                new Transition("q2", 'x', "q0", 'x', MoveDirection.Right),
                new Transition("q0", '0', "q3", '0', MoveDirection.Right),
                new Transition("q3", '0', "q3", '0', MoveDirection.Right),
                new Transition("q3", '_', "q4", '_', MoveDirection.Right),
        };

        TuringMachine turingMachine = new TuringMachine("q0", "q4", "q5", transitions);

        boolean result = turingMachine.check("aaaabbbb");
        System.out.println(result);
    }
}

class TuringMachine {

    final public String startState;
    final public String acceptState;
    final public String rejectState;
    final public Transition[] transitions;

    private String currentState;
    private int position;
    private char[] tape;

    final int tapeLength = 20;
    final int stop = 100;

    public TuringMachine(String startState, String acceptState, String rejectState, Transition[] transitions) {
        this.startState = startState;
        this.acceptState = acceptState;
        this.rejectState = rejectState;
        this.transitions = transitions;
    }

    public boolean check(String word) {

        // makes the tape and put the word in the middle of the tape
        this.tape = "_".repeat(this.tapeLength).toCharArray();
        this.position = (this.tapeLength - word.length()) / 2;
        System.arraycopy(word.toCharArray(), 0, tape, this.position, word.length());

        this.currentState = startState;
        System.out.printf("%-3d   %s   %-30s   position: %-3d state: %-3s \n", 0, String.valueOf(this.tape), " ".repeat(30), this.position, this.currentState);

        for (int i = 0; i < this.stop; i++) {

            // end of tape
            if (this.position == -1 || this.position == this.tapeLength) {
                System.out.println("End of tape");
                return false;
            }

            Transition transition = move();
            if (transition == null) {
                System.out.println("No transition is defined");
                return false;
            }

            System.out.printf("%-3d   %s   %-30s   position: %-3d state: %-3s \n", i + 1, String.valueOf(this.tape), transition, this.position, this.currentState);

            if (this.currentState.equals(acceptState)) return true;
            else if (this.currentState.equals(rejectState)) return false;
        }

        System.out.println("End of loop limit");
        return false;
    }

    private Transition move() {
        Transition transition = findTransition(this.currentState, this.tape[this.position]);
        if (transition == null) return null;
        else {
            this.currentState = transition.writeState;
            this.tape[this.position] = transition.writeSymbol;

            if (transition.moveDirection == MoveDirection.Right) this.position++;
            else this.position--;
        }

        return transition;
    }

    private Transition findTransition(String readState, char readSymbol) {
        for (Transition transition : this.transitions) {
            if (transition.readState.equals(readState) && transition.readSymbol == readSymbol)
                return transition;
        }

        return null;
    }
}

enum MoveDirection {Left, Right}

class Transition {

    final public String readState;
    final public char readSymbol;
    final public String writeState;
    final public char writeSymbol;
    final public MoveDirection moveDirection;

    public Transition(String readState, char readSymbol, String writeState, char writeSymbol, MoveDirection moveDirection) {
        this.readState = readState;
        this.readSymbol = readSymbol;
        this.writeState = writeState;
        this.writeSymbol = writeSymbol;
        this.moveDirection = moveDirection;
    }

    @Override
    public String toString() {
        return String.format("(%s, %s) -> (%s, %s, %s)", readState, readSymbol, writeState, writeSymbol, moveDirection.toString());
    }
}
