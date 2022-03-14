package com.minboard.mapper;

import com.minboard.vo.MemberSaveVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    void createMember(MemberSaveVo memberSaveVo);

}
