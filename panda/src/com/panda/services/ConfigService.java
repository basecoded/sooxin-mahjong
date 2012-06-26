package com.panda.services;

import java.util.List;

import com.panda.dao.Config;
import com.panda.dao.ConfigDAO;
import com.panda.inter.IConfigService;

public class ConfigService implements IConfigService {
	private ConfigDAO configDao;
	public Config getConfig() {
		// TODO Auto-generated method stub
		return configDao.findById(1);
	}
	public ConfigDAO getConfigDao() {
		return configDao;
	}
	public void setConfigDao(ConfigDAO configDao) {
		this.configDao = configDao;
	}

}
