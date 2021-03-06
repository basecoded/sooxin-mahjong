package com.services
{
	import com.model.Alert;
	
	import mx.managers.CursorManager;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.InvokeEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.mxml.RemoteObject;

	public class RemoteService
	{
		private static var _instance:RemoteService;
		
		public var chongzhiService:RemoteObject;
		public var duihuanService:RemoteObject;
		public var playlogService:RemoteObject;
		public var playerService:RemoteObject;
		public var shangpinService:RemoteObject;
		public var noticeService:RemoteObject;		
		public var configService:RemoteObject;	

		public function RemoteService()
		{
			chongzhiService=getConfiguredRO("chongzhiService");
			duihuanService=getConfiguredRO("duihuanService");
			playlogService=getConfiguredRO("playlogService");
			playerService=getConfiguredRO("playerService");
			shangpinService=getConfiguredRO("shangpinService");
			noticeService=getConfiguredRO("noticeService");
			configService=getConfiguredRO("configService");
		}

		//得到单例
		public static function get instance():RemoteService
		{
			if(_instance == null){
				_instance = new RemoteService();
			}
			return _instance;
		}

		private function resultHandler(e:ResultEvent):void
		{
			CursorManager.removeBusyCursor();
		}

		private function addResultEvent(ro:RemoteObject):void
		{
			ro.addEventListener(ResultEvent.RESULT, resultHandler);
		}

		private function invokeHandler(e:InvokeEvent):void
		{
			CursorManager.setBusyCursor();
		}

		private function addInvokeEvent(ro:RemoteObject):void
		{
			ro.addEventListener(InvokeEvent.INVOKE, invokeHandler);
		}


		private function faultHandler(e:FaultEvent):void
		{
			Alert.show("读取信息失败，请刷新页面！"+e.toString());
			trace(e.toString());
			CursorManager.removeBusyCursor();
		}

		private function addFaultEvent(ro:RemoteObject):void
		{
			ro.addEventListener(FaultEvent.FAULT, faultHandler);
		}


		private function addAllEvent(ro:RemoteObject):void
		{
			addInvokeEvent(ro);
			addResultEvent(ro);
			addFaultEvent(ro);
		}

		private function setEndPoint(ro:RemoteObject):void
		{

			ro.endpoint="http://s-32472.gotocdn.com:8080/panda/messagebroker/amf";
			trace(ro.endpoint);
		}


		//得到配置好的RemoteObject
		private function getConfiguredRO(destination:String):RemoteObject
		{
			var ro:RemoteObject=new RemoteObject(destination);

			setEndPoint(ro);
			addAllEvent(ro);

			return ro;
		}
	}
}