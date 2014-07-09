public class Token {
	private boolean free;
	private boolean iscomplete;
	private int position;
	private int playerId;
	public Token(int playerId) {
		
		this.playerId = playerId;
		this.position = playerId * 13;
		this.free = false ;
		this.iscomplete = false;
		
	}
	public int getPlayerId(){
		return playerId;
	}
	
	public void reset(){
		
		position = playerId * 13;
		free = false;
		
	}
	public void moveToken(int steps) {
		
		position += steps;	
		
	}
	
	public void Free() {
		this.free = true;
	}
	public int getplayerId() {
		return this.playerId;
	}
	public int getPosition() {
		return position;
	}
	public boolean isFree() {
		return free;
	}
	public boolean isComplete(){
		return iscomplete;
	}
}
