public class Token {
	private boolean isfree;
	private int iscomplete;
	private int position;
	private int playerId;
	public Token(int playerId) {
		
		this.playerId = playerId;
		this.position = playerId * 12;
		this.isfree = false ;
		this.iscomplete = false;
		
	}
	public int getPlayerId(){
		return playerId;
	}
	
	public void reset(){
		
		coordinate = playerId * 12;
		isfree = false;
		
	}
	public void moveToken(int steps) {
		
		position += steps;	
		
	}
	
	public void free() {
		this.isfree = true;
	}
	public int getplayerId() {
		return this.playerid;
	}
	public int getPosition() {
		return position;
	}
	public int isfree() {
		return isfree;
	}
	
}
