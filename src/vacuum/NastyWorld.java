package vacuum;

public class NastyWorld extends World{

	public NastyWorld(int width) {
		super(width);
	}
	
	@Override
	void setUp(int width){
		int edge = (int)(0.33 * width);
		//Lower left
		for(int i = 0; i < edge; i++){
			getSquare(edge, i).setObstacle(true);
		}
		for(int i = edge; i > 0; i--){
			getSquare(i, edge).setObstacle(true);
		}
		for(int i = edge; i < edge + edge - 1; i++){
			getSquare(1, i).setObstacle(true);
		}
		for(int i = 0; i < edge; i++){
			for(int j = 0; j < edge; j++){
				getSquare(i, j).setDirty(true);
			}
		}
	}

}
