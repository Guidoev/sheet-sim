package data;

public class Counter {

	public static final int[] VALUES = {125,71}; // {VERDI, NERE}

	public int[] values = {0,0};
	public int counter;
	
	public int[] countValues(Tile[][] map) {
		values[0]=0;
		values[1]=0;
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[i].length; j++) {
				if(map[i][j].getValue()==0) {
					values[0]++;
				} else if(map[i][j].getValue()==1) {
					values[1]++;
				}
			}
		}
		return values;
	}
	
	public void updateValues(Tile[][] map) {
		values[0]=0;
		values[1]=0;
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[i].length; j++) {
				if(map[i][j].getValue()==0) {
					values[0]++;
				} else if(map[i][j].getValue()==1) {
					values[1]++;
				}
			}
		}
	}
	
	public void resetValues() {
		values[0]=0;
		values[1]=0;
	}
	
	
}
