package command;

// A command that implement this interface, is reversible
// A reversible command, is a command that may undone by (for example) a CTRL-Z

public interface Reversible{
	public void revert();
}
