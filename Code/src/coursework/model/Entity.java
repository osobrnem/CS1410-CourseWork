package coursework.model;


/**
 * @author Lewis Miller
 *
 */
public abstract class Entity {

	protected int row;
	protected int col;

	public Entity() {

	}

	public void setLocation(int newRow, int newCol){
		row = newRow;
		col = newCol;
	}

	public void setRow(int newRow) {
		row = newRow;
	}

	public void setCol(int newCol) {
		col = newCol;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

}