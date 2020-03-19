package com.ws.service.imp;

import com.ws.bean.Region;
import com.ws.mapper.RegionMapper;
import com.ws.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.annotation.WebListener;
import java.util.List;

/**
 * @author 王朔
 * Description:
 * @date: 2020.03.17 11:05
 * @since JDK 1.8
 */
@Service
public class RegionServiceImpl implements RegionService {
    @Autowired
    RegionMapper regionMapper;
    @Override
    public List<Region> findAll() {
        return regionMapper.selectByExample(null);
    }
}
