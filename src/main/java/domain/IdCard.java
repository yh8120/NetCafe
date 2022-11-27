package domain;

public class IdCard {
	private Integer cardId;
	private String cardName;
	private Boolean canCopyNumber;
	
	
	public Integer getCardId() {
		return cardId;
	}
	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public Boolean getCanCopyNumber() {
		return canCopyNumber;
	}
	public void setCanCopyNumber(Boolean canCopyNumber) {
		this.canCopyNumber = canCopyNumber;
	}

}
