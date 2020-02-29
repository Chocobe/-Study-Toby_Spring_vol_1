package vo;

import java.time.LocalDate;

public class MyBoard {
	private int idx;
	private String writerID;
	private String title;
	private String content;
	private LocalDate writeDate;
	private int watch;
	
	
	public MyBoard() {}
	
	public MyBoard(String writerID,
				   String title,
				   String content) {
		this(0,
			 writerID,
			 title,
			 content,
			 null,
			 0);
	}
	
	public MyBoard(int idx,
				   String writerID,
				   String title,
				   String content,
				   LocalDate writeDate,
				   int watch) {
		this.idx = idx;
		this.writerID = writerID;
		this.title = title;
		this.content = content;
		this.writeDate = writeDate;
		this.watch = watch;
	}

	
// idx
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}

	
// writerID
	public String getWriterID() {
		return writerID;
	}
	public void setWriterID(String writerID) {
		this.writerID = writerID;
	}

	
// title
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	
// content
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	
// writeDate
	public LocalDate getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(LocalDate writeDate) {
		this.writeDate = writeDate;
	}

	
// watch
	public int getWatch() {
		return watch;
	}
	public void setWatch(int watch) {
		this.watch = watch;
	}
}
