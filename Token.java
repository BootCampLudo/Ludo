public class Token {
	private int isfree;
	private int iscomplete;
	private int position;
	private int playerid;
	public Token(int id, int playerId) {
		coordinate = playerId * 13 - 1;
		state = GAME_CONFIG.LOCKED;
		this.id = id;
		this.playerId = playerId;
	}
	public int getPlayerId(){
		return playerId;
	}
	public boolean canMove(int steps) {
		return (coordinate - playerId * 13 + steps <= 60  && state != GAME_CONFIG.LOCKED) || (state == GAME_CONFIG.LOCKED && steps == 6);
	}
	public void reset(){
		coordinate = playerId * 13 - 1;
		state = GAME_CONFIG.LOCKED;
	}
	public boolean moveToken(int steps) {
		if (!canMove(steps))
			return false;
		coordinate = coordinate + steps;
		if (coordinate - playerId*13 == 60) {
			state = GAME_CONFIG.FINISHED;
		}else if(coordinate - playerId*13 >=55) {
			state = GAME_CONFIG.SAFE;
		}
		return true;
	}
	
	public void setState(int state) {
		this.state = state;
	}
	public int getId() {
		return this.id;
	}
	public int getCoordinate() {
		return coordinate;
	}
	public int getState() {
		return state;
	}
	
}
