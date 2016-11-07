package com.pi.swing.bean;
import java.io.Serializable;


public class ImagePayLoad implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String scheduleId;
	private String fileName;
	private String fileType;
	private String mimeType;
	private String mediaByteArray;
	private int duration;
	
	public String getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getMediaByteArray() {
		return mediaByteArray;
	}

	public void setMediaByteArray(String mediaByteArray) {
		this.mediaByteArray = mediaByteArray;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

}
