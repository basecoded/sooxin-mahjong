package com.stock.dao;

import java.sql.Timestamp;

/**
 * Kline entity. @author MyEclipse Persistence Tools
 */

public class Kline implements java.io.Serializable {

	// Fields

	private Long id;
	private Timestamp timestamp;
	private String stockCode;
	private String date;
	private Double finistNum;
	private Double startNum;
	private Double topNum;
	private Double lastNum;
	private Double turnover;

	// Constructors

	/** default constructor */
	public Kline() {
	}

	/** full constructor */
	public Kline(String stockCode, String date, Double finistNum,
			Double startNum, Double topNum, Double lastNum, Double turnover) {
		this.stockCode = stockCode;
		this.date = date;
		this.finistNum = finistNum;
		this.startNum = startNum;
		this.topNum = topNum;
		this.lastNum = lastNum;
		this.turnover = turnover;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getStockCode() {
		return this.stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Double getFinistNum() {
		return this.finistNum;
	}

	public void setFinistNum(Double finistNum) {
		this.finistNum = finistNum;
	}

	public Double getStartNum() {
		return this.startNum;
	}

	public void setStartNum(Double startNum) {
		this.startNum = startNum;
	}

	public Double getTopNum() {
		return this.topNum;
	}

	public void setTopNum(Double topNum) {
		this.topNum = topNum;
	}

	public Double getLastNum() {
		return this.lastNum;
	}

	public void setLastNum(Double lastNum) {
		this.lastNum = lastNum;
	}

	public Double getTurnover() {
		return this.turnover;
	}

	public void setTurnover(Double turnover) {
		this.turnover = turnover;
	}

}