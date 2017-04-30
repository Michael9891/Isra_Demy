package tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="messages")
public class Messages {
	@Id
	@Column(name="msgid",nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int msgId;
	@Column(name="msg")
	private String msg;
	@Column(name="username")
	private String userName;

	public Messages() {

	}

	public Messages(String msg, String userName) {
		super();
		this.msg = msg;
		this.userName = userName;
	}

	public int getMsgId() {
		return msgId;
	}

	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
