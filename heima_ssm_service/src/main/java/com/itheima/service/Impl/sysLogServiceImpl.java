package com.itheima.service.Impl;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.ISysLogDao;
import com.itheima.domain.SysLog;
import com.itheima.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class sysLogServiceImpl implements ISysLogService {
    @Autowired
    private ISysLogDao sysLogDao;
    @Override
    public void save(SysLog sysLog) throws Exception {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        sysLog.setId(uuid);
        sysLogDao.save(sysLog);

    }

    @Override
    public List<SysLog> findAll(Integer pageNum, Integer pageSize) throws Exception {
        PageHelper.startPage(pageNum,pageSize);
        return sysLogDao.findAll(pageNum,pageSize);
    }
}
