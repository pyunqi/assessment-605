package oop.items;

/**
 * Video Class
 * 
 * @author yunqi
 *
 */
public class Video extends Item {
	private String producer;
	private int lifeTime;
	private int length; // how many minutes

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public int getLifeTime() {
		return lifeTime;
	}

	public void setLifeTime(int lifeTime) {
		this.lifeTime = lifeTime;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * create a video
	 * @param producer
	 * @param lifeTime
	 * @param length
	 */
	public Video(String producer, int lifeTime, int length) {
		super();
		this.producer = producer;
		this.lifeTime = lifeTime;
		this.length = length;
	}

	/**
	 * create a video
	 */
	@Override
	public String toString() {
		return "Video [producer=" + producer + ", lifeTime=" + lifeTime + ", length=" + length + ", getStatus()="
				+ getStatus() + ", getItemId()=" + getItemId() + ", getTitle()=" + getTitle() + ", getDueDate()="
				+ getDueDate() + "]";
	}

}
