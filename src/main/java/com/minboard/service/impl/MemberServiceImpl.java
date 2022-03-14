package com.minboard.service.impl;

import com.minboard.mapper.MemberMapper;
import com.minboard.service.MemberService;
import com.minboard.vo.MemberSaveVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;


    /** 회원생성 **/
    @Override
    public void createMember(MemberSaveVo memberSaveVo) {

        memberMapper.createMember(memberSaveVo);

    }
}
