package command;

// A command that implement this interface, is reversible
// A reversible command, is a command that may undone by (for example) a CTRL-Z

// We do not save the state of the buffer before each command, the state is recalculated
// Reversible commands have to know how to revert the action they have done 

public interface Reversible{
	public void revert();
}
