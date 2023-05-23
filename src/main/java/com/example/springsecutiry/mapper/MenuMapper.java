package com.example.springsecutiry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springsecutiry.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    List<String> selectPermsByUserid(Long userid);
}
