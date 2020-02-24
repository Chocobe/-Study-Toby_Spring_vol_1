package vo;

import java.time.LocalDate;

public class MyBoard {
	private int idx;
	private String id;
	private String title;
	private String content;
	private LocalDate writeDate;
	private int watch;
	
	
	public MyBoard() {}
	
	public MyBoard(String id,
				   String title,
				   String content) {
		this(0, 
			 id, 
			 title, 
			 content, 
			 null, 
			 0);
	}
	
	public MyBoard(int idx,
				   String id,
				   String title,
				   String content,
				   LocalDate writeDate,
				   int watch) {
		this.idx = idx;
		this.id = id;
		this.title = title;
		this.content = content;
		this.writeDate = writeDate;
		this.watch = watch;
	}

	
// id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	
// title
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	
// count
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
