package com.minboard.mapper;

import com.minboard.dto.MemberDto;
import com.minboard.vo.member.MemberSaveVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    void insertMember(MemberDto memberDto);
    MemberSaveVo findByUserName(MemberDto memberDto);
}
