package oop.items;

import java.util.List;

/**
 * DVD class
 * 
 * @author yunqi
 *
 */
public class DVD extends Item {

	private String releasDate;
	private int lifeTime;
	private List<String> actors;

	public String getReleasDate() {
		return releasDate;
	}

	public void setReleasDate(String releasDate) {
		this.releasDate = releasDate;
	}

	public int getLifeTime() {
		return lifeTime;
	}

	public void setLifeTime(int lifeTime) {
		this.lifeTime = lifeTime;
	}

	public List<String> getActors() {
		return actors;
	}

	public void setActors(List<String> actors) {
		this.actors = actors;
	}

	/**
	 * create a dvd
	 * @param releasDate
	 * @param lifeTime
	 * @param actors
	 */
	public DVD(String releasDate, int lifeTime, List<String> actors) {
		super();
		this.releasDate = releasDate;
		this.lifeTime = lifeTime;
		this.actors = actors;
	}

	@Override
	public String toString() {
		return "DVD [releasDate=" + releasDate + ", lifeTime=" + lifeTime + ", actors=" + actors + ", getStatus()="
				+ getStatus() + ", getItemId()=" + getItemId() + ", getTitle()=" + getTitle() + ", getDueDate()="
				+ getDueDate() + "]";
	}

}
