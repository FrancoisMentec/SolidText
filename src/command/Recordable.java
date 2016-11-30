package command;

// A command that implement this interface is recordable as part of a macro
// Recordable Commands should save theirs data in order to be executed again when we play the macro (Memento)

public interface Recordable {
	public Command copy();
}
