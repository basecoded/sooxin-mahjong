package com.robertSyncServer.services;

import java.util.ArrayList;
import com.robertSyncServer.model.Cjhistory;
import com.robertSyncServer.model.Order;

public class MainService {
	public static MainService instance;
	ArrayList<RobertService> robertServices = new ArrayList<RobertService>();
	
	ArrayList<StockService> stockServices = new ArrayList<StockService>();
	
	public static MainService getInstance(){
		if(instance == null){
			instance = new MainService();
		}
		return instance;
	}

	public MainService(){
		for(int i=0;i<10000;i++){
			RobertService robertService = new RobertService();
			robertServices.add(robertService);
		}
		
	}
	
	public void doTimer(){
		for(int i=0;i<robertServices.size();i++){
			robertServices.get(i).todo();
		}
	}
	
	
	
	public void init(String stockCode, String stockName,int allStockNum,
			int liutongStockNum, double shouyi, double PE,
			double lastDayEndPrice, double todayStartPrice, double topPrice,
			double bottomPrice, double nowPrice, double nowCjNum,
			ArrayList<Cjhistory> cjhistorys, ArrayList<Order> buyOrders,
			ArrayList<Order> saleOrders){
		
		StockService stockService = new StockService();
		stockService.stock.setStockCode(stockCode);
		stockService.stock.setStockName(stockName);
		stockService.stock.setAllStockNum(allStockNum);
		stockService.stock.setLiutongStockNum(liutongStockNum);
		stockService.stock.setShouyi(shouyi);
		stockService.stock.setPE(PE);
		stockService.stock.setLastDayEndPrice(lastDayEndPrice);
		stockService.stock.setTodayStartPrice(todayStartPrice);
		stockService.stock.setTopPrice(topPrice);
		stockService.stock.setBottomPrice(bottomPrice);
		stockService.stock.setNowPrice(nowPrice);
		stockService.stock.setNowCjNum(nowCjNum);
		stockService.stock.setCjhistorys(cjhistorys);
		stockService.stock.setBuyOrders(buyOrders);
		stockService.stock.setSaleOrders(saleOrders);
		stockServices.add(stockService);
	}
	
	public void updateJiaoyi(String stockCode, double topPrice,
			double bottomPrice, double nowPrice, double nowCjNum,
			ArrayList<Order> buyOrders, ArrayList<Order> saleOrders,
			ArrayList<Cjhistory> thisCjhistoryS){
		
		for(int i=0; i<stockServices.size(); i++){
			if(stockServices.get(i).stock.stockCode.equals(stockCode)){
				stockServices.get(i).stock.topPrice = topPrice;
				stockServices.get(i).stock.bottomPrice = bottomPrice;
				stockServices.get(i).stock.nowPrice = nowPrice;
				stockServices.get(i).stock.nowCjNum = nowCjNum;
				stockServices.get(i).stock.buyOrders = buyOrders;
				stockServices.get(i).stock.saleOrders = saleOrders;
				
				if(thisCjhistoryS.size()>1){
					stockServices.get(i).stock.cjhistorys.addAll(thisCjhistoryS);
				}
				
			}
		}
	}

}
