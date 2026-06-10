package oe.demo;

public interface MyInter {
	void send(String from, String to, String subject, String content);
	default void send(String to, String subject, String content) {
		var from = "FPT Polytechnic <poly@gmail.com>";
		this.send(from, to, subject, content);
	}
}
