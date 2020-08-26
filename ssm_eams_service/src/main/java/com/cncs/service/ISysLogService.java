package com.cncs.service;

import com.cncs.domain.SysLog;

import java.util.List;

//系统日志业务层接口
public interface ISysLogService {

    public void save(SysLog sysLog);

    List<SysLog> findAll();
}
