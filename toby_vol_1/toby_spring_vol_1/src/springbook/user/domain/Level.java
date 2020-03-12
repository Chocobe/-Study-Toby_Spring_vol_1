package springbook.user.domain;

public enum Level {
	GOLD(3, null),
	SILVER(2, GOLD),
	BASIC(1, SILVER); 
	 
	
	
	private final int value;
	private final Level next;
	
	
// DB에 저장할 값을 넣어줄 생성자
	private Level(int value, Level next) {
		this.value = value;
		this.next = next;
	}
	
	
// int값 가져오기 (POJO -> DB 경우에 사용)
	public int intValue() {
		return value;
	}
	
	
// 다음 등급 가져오기
	public Level nextLevel() {
		return next;
	}
	
	
// enum값 가져오기 (DB -> POJO 경우에 사용)
	public static Level valueOf(int value) {
		switch(value) {
			case 1: return BASIC;
			case 2: return SILVER;
			case 3: return GOLD;
			default: throw new AssertionError("Unkown value: " + value);
		}
	}
}
