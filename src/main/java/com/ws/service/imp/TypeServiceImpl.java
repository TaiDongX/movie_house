package com.ws.service.imp;

import com.ws.bean.Type;
import com.ws.mapper.TypeMapper;
import com.ws.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 王朔
 * Description:
 * @date: 2020.03.17 11:02
 * @since JDK 1.8
 */
@Service
public class TypeServiceImpl  implements TypeService {
    @Autowired
    TypeMapper typeMapper;
    @Override
    public List<Type> findAll() {
        return typeMapper.selectByExample(null);
    }
}
