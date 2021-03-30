public class Transit {
	private String fromState;
	private Character symbol;
	private String toState;
	
	public Transit(String fromState, Character symbol, String toState) {
		super();
		this.fromState = fromState;
		this.symbol = symbol;
		this.toState = toState;
	}
	public String getFromState() {
		return fromState;
	}
	public void setFromState(String fromState) {
		this.fromState = fromState;
	}
	public Character getSymbol() {
		return symbol;
	}
	public void setSymbol(Character symbol) {
		this.symbol = symbol;
	}
	public String getToState() {
		return toState;
	}
	public void setToState(String toState) {
		this.toState = toState;
	}
}
