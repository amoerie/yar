package domain.action;

/**
 * @author moerie
 */
public class ReplaceAction extends RenameAction {
	private String target;
	private String replacement;
		
	public ReplaceAction(String target, String replacement) {
		this.target = target;
		this.replacement = replacement;
	}

	@Override
	public String execute(String fileName) {
        if(target == null || target.length() == 0)
            return fileName;
		return fileName.replace(target, replacement);
	}

	@Override
	public String toString() {
		return "Replace '" + target + "' with '" + replacement + "'";
	}

    public void setTarget(String target) {
        this.target = target;
    }

    public void setReplacement(String replacement) {
        this.replacement = replacement;
    }

    public String getTarget() {
        return target;
    }

    public String getReplacement() {
        return replacement;
    }
}
