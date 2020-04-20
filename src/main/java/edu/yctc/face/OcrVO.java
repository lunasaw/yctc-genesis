package edu.yctc.face;

/**
 * @author Luna@win10
 * @date 2020/4/20 12:45
 */
public class OcrVO {

	private String words;

	private String value;

	public String getWords() {
		return words;
	}

	public void setWords(String words) {
		this.words = words;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "OcrVO{" +
				"words='" + words + '\'' +
				", value='" + value + '\'' +
				'}';
	}
}
