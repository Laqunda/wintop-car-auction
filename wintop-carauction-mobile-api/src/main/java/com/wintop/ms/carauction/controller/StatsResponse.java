package com.wintop.ms.carauction.controller;

class StatsResponse {
    public int quantity;
	public String text;

	public StatsResponse(int quantity, String text) {
		this.quantity = quantity;
		this.text = text;
	}

	public StatsResponse() {
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}
}