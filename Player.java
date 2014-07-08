public class Player {

	private int id;
	private boolean isUnfinished;
	private Token[] tokens;

	public Player(int id, Token[] tokens) {
		this.id = id;
		this.tokens=tokens;
		this.isUnfinished=true;
	}
	
	public Token[] getTokens() {
		return tokens;
	}
	
	public boolean isUnfinished()
	{
		return isUnfinished;
	}
	
	public int getId()
	{
		return id;
	}
	
	
	public void updateIsUnfinished()
	{
		int countTokensFinished = 0;
		for (int i=0; i<4; i++)
		{
			if(tokens[i].getState() == GAME_CONFIG.FINISHED)
			{
				countTokensFinished ++;
			}else {
				break;
			}
			
		}
		
		if (countTokensFinished == 4)
		{
			isUnfinished = false;
		}
	}
	
	public void unlockToken(int id)
	{
		Token t = findTokenById(id);
		if (t != null) {
			t.setState(1);
		}
	}
	private Token findTokenById(int id) {
		for(int i=0;i<tokens.length;i++)
		{
			if(tokens[i].getId()==id)
			{
				return tokens[i];
			}
		}
		return null;
	}
	public boolean moveTokenByStep(int tokenId, int steps)
	{
		Token t = findTokenById(tokenId);
		return t.moveToken(steps);
	}
	
	public int findLockedToken()
	{
		for(int i=0;i<tokens.length;i++)
		{
			if(tokens[i].getState()==GAME_CONFIG.LOCKED)
			{
				return tokens[i].getId();
			}
		}
			return 0;
	}
	
	public boolean canMove(int steps){
		return tokens[0].canMove(steps)  || tokens[1].canMove(steps) || tokens[2].canMove(steps) || tokens[3].canMove(steps);
	}
}
