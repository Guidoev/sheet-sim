package data;

public enum TileType {

	Green("green", 0), Black("black", 1), Concept_Green("concept-green", 0), Concept_Black("concept-black", 1), Real_Green("real-green", 0), Real_Black("real-black", 1), Grey("grey",2), Grid_Grey("grid-grey", 2), Bounds("bounds", 3);
	
	String name;
	int value;
	
	TileType(String name, int value) {
		this.name = name;
		this.value = value;
	}
}
