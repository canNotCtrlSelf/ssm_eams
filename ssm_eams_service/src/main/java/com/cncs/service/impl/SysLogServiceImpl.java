package com.cncs.service.impl;

import com.cncs.dao.ISysLogDao;
import com.cncs.domain.SysLog;
import com.cncs.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 系统日志业务层的实现类
 */
@Service
@Transactional
public class SysLogServiceImpl  implements ISysLogService {

    @Autowired
    private ISysLogDao sysLogDao;

    @Override
    public void save(SysLog sysLog) {
        sysLogDao.save(sysLog);
    }

    @Override
    public List<SysLog> findAll() {
        return sysLogDao.findAll();
    }
}
