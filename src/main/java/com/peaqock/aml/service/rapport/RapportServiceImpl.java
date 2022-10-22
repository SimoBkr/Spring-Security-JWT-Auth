package com.peaqock.aml.service.rapport;

import com.peaqock.aml.dao.PepRepository;
import com.peaqock.aml.dao.RapportRepository;
import com.peaqock.aml.domain.Pep;
import com.peaqock.aml.domain.RapportEnity;
import com.peaqock.aml.dto.RapportDto;
import com.peaqock.aml.dto.payload.RapportResponse;
import com.peaqock.aml.exception.errors.ResourceNotFoundException;
import com.peaqock.aml.mapper.IRapportMapper;
import com.peaqock.aml.service.email.EmailSenderService;
import com.peaqock.aml.service.user.UserService;
import com.peaqock.aml.shared.enums.GroupEnum;
import com.peaqock.aml.utils.constants.PageableConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RapportServiceImpl implements RapportService {

    private final RapportRepository rapportRepository;

    private final PepRepository pepRepository;
    private final IRapportMapper mapper;

    private final EmailSenderService emailSenderService;

    private final UserService userService;


    @Override
    public RapportResponse getALlReports(int pageNo, int pageSize, String sortBy, String sortDir) {
        final var pageable = PageableConstants.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .sortDir(sortDir)
                .sortBy(sortBy)
                .build();

        final var result = rapportRepository.findAll(pageable);

        return RapportResponse.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .last(result.isLast())
                .totalPages(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .content(result.getContent())
                .build();
    }

    @Override
    public RapportEnity createRapport(RapportDto rapportDto) {
        RapportEnity rapportEnity = mapper.toEntity(rapportDto);

        Pep pep = pepRepository.findById(rapportDto.getPepId()).orElseThrow(() -> new ResourceNotFoundException("Pep not found !"));
        pep.setPepStatus(rapportDto.getStatus());
        rapportEnity.setPep(pep);

        userService.listAllUsersByGroup(GroupEnum.G1)
                .forEach(user ->
                        emailSenderService.addComment(user.getEmail(),
                                rapportEnity.getPep().getName()
                        )
                );

        return rapportRepository.save(rapportEnity);
    }

    @Override
    public RapportEnity getRapport(long rapportId) {
        return getRapportById(rapportId);
    }

    @Override
    public void deleteRapport(long rappordId) {
        rapportRepository.delete(getRapportById(rappordId));
    }

    private RapportEnity getRapportById(long id) {
        return rapportRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Rapport not found !"));
    }

}
