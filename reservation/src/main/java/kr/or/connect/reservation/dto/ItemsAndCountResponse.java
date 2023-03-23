package kr.or.connect.reservation.dto;


public class ItemsAndCountResponse<T> {
	private int totalCount;
	private T items;
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public T getItems() {
		return items;
	}
	public void setItems(T items) {
		this.items = items;
	}
	
	@Override
	public String toString() {
		return "Response [totalCount=" + totalCount + ", items=" + items + "]";
	}
}
