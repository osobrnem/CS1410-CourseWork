package coursework.model;

/**
 *
 * Abstract Class for all Board Objects.
 *
 * @author Lewis Miller
 *
 */
public abstract class Entity {

	protected int row;
	protected int col;

	public Entity() {

	}

	/**
	 *
	 * Sets the location variables of the Entity
	 *
	 * @param newRow
	 *            Row
	 * @param newCol
	 *            Column
	 */
	public void setLocation(int newRow, int newCol) {
		row = newRow;
		col = newCol;
	}

	/**
	 *
	 * Sets the row
	 *
	 * @param newRow
	 *            Row
	 */
	public void setRow(int newRow) {
		row = newRow;
	}

	/**
	 *
	 * Sets the column
	 *
	 * @param newCol
	 *            Column
	 */
	public void setCol(int newCol) {
		col = newCol;
	}

	/**
	 *
	 * Returns the Entities row
	 *
	 * @return row Row
	 */
	public int getRow() {
		return row;
	}

	/**
	 *
	 * Returns the Entities Column
	 *
	 * @return col Column
	 */
	public int getCol() {
		return col;
	}

}