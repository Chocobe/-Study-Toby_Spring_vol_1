package springbook.user.domain;

public enum Level {
	BASIC(1), SILVER(2), GOLD(3);
	
	private final int value;
	
	
// DB에 저장할 값을 넣어줄 생성자
	private Level(int value) {
		this.value = value;
	}
	
	
// int값 가져오기 (POJO -> DB 경우에 사용)
	public int intValue() {
		return value;
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
