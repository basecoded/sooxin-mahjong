package com.leafSyncServer.services;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import org.red5.server.api.service.IServiceCapableConnection;

import com.leafSyncServer.model.Cjhistory;
import com.leafSyncServer.model.Message;
import com.leafSyncServer.model.Order;

public class MainService {
	public ArrayList<PlayerService> playerServices;
	public static MainService instance;
	
	ArrayList<StockService> stockServices = new ArrayList<StockService>();

	public MainService() {
		playerServices = new ArrayList<PlayerService>();
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
	
	public static MainService getInstance(){
		if(instance == null){
			instance = new MainService();
		}
		
		return instance;
	}

	public void addPlayerService(String playerName,IServiceCapableConnection iserver) {
		PlayerService playerService = new PlayerService();
		playerService.getPlayer().setPlayerName(playerName);
		playerService.getPlayer().setIserver(iserver);
		playerService.getPlayer().setClientID(iserver.getClient().getId());
		playerServices.add(playerService);
		
		Message message = new Message();
		message.setHead("initI");
		message.setContent(new Object[]{stockServices});
		MessageService.instance.sendOnly(playerService, message);
	}

	public void removePlayerService(IServiceCapableConnection iserver) {
		for (int i = 0; i < playerServices.size(); i++) {
			if (playerServices.get(i).getPlayer().getClientID().equals(iserver.getClient().getId())) {
				playerServices.remove(i);
			}
		}
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
		
		Message message = new Message();
		message.setHead("updateJiaoyiI");
		message.setContent(new Object[]{buyOrders,saleOrders,thisCjhistoryS});
		MessageService.instance.send(stockCode, message);
	}
	
	public void dealStock(String playerName, String stockCode){
		for(int i=0; i<this.playerServices.size(); i++){
			if(playerServices.get(i).getPlayer().getPlayerName().equals(playerName)){
				playerServices.get(i).getPlayer().setNowStockCode(stockCode);
				
				
				//初始化
				for(int j=0; j<stockServices.size();j++){
					if(stockServices.get(j).stock.getStockCode().equals(stockCode)){
						Message message = new Message();
						message.setHead("updateJiaoyiI");
						message.setContent(new Object[]{stockServices.get(j).stock.buyOrders,stockServices.get(j).stock.saleOrders,stockServices.get(j).stock.cjhistorys});
						MessageService.instance.send(stockCode, message);
					}
				}
			}
		}
	}

}
