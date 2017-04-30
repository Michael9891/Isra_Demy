package tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="files")
public class Files {
	@Id
	@Column(name="fileid",nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int fileId;
	@Column(name="filename" , nullable=false)
	private String fileName;
	@Column(name="college" , nullable=false)
	private String collegeName;
	@ManyToOne
	@JoinColumn(name = "username", nullable=false)
	private Users userName;

	public Files() {

	}
	
	public Files(String fileName, String collegeName, Users userName) {
		super();
		this.fileName = fileName;
		this.collegeName = collegeName;
		this.userName = userName;
	}
	
	public Integer getFileId() {
		return fileId;
	}
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getCollegeName() {
		return collegeName;
	}
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	public Users getUserName() {
		return userName;
	}
	public void setUserName(Users userName) {
		this.userName = userName;
	}
	
}
