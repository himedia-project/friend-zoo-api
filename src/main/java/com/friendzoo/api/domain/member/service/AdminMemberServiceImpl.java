package com.friendzoo.api.domain.member.service;

import com.friendzoo.api.domain.member.dto.MemberResDTO;
import com.friendzoo.api.domain.member.entity.Member;
import com.friendzoo.api.domain.member.repository.MemberRepository;
import com.friendzoo.api.dto.PageRequestDTO;
import com.friendzoo.api.dto.PageResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class AdminMemberServiceImpl implements AdminMemberService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    @Override
    public PageResponseDTO<MemberResDTO> getList(PageRequestDTO requestDTO) {

        Page<Member> result = memberRepository.findAllBy(requestDTO);

        return PageResponseDTO.<MemberResDTO>withAll()
                .dtoList(result.stream().map(this::entityToDTO).toList())
                .totalCount(result.getTotalElements())
                .pageRequestDTO(requestDTO)
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public MemberResDTO getOne(String email) {
        return entityToDTO(getMember(email));
    }


    /**
     * member 찾기
     * @param email 이메일
     * @return Member
     */
    private Member getMember(String email) {
        return memberRepository.findById(email)
                .orElseThrow(() -> new EntityNotFoundException("해당 회원이 존재하지 않습니다. email: " + email));
    }
}
