package com.peaqock.aml.service.aml;

import com.peaqock.aml.dao.AmlScenarioRepository;
import com.peaqock.aml.dao.AmlSummaryRepository;
import com.peaqock.aml.dto.payload.AmlResponse;
import com.peaqock.aml.exception.ApiErrorMessage;
import com.peaqock.aml.exception.errors.NoRecordFoundException;
import com.peaqock.aml.mapper.IAmlScenarioMapper;
import com.peaqock.aml.service.excel.ExcelService;
import com.peaqock.aml.utils.constants.PageableConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.peaqock.aml.utils.ObjectUtils.convertTupleToMap;
import static com.peaqock.aml.utils.ObjectUtils.convertTuplesToMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultAmlServiceImpl implements AmlService {

    private final IAmlScenarioMapper mapper;
    private final AmlSummaryRepository summaryRepository;
    private final AmlScenarioRepository repository;
    private final ExcelService excelService;

    @PersistenceContext
    private EntityManager em;

    @Override
    public AmlResponse allAmlScenarios(String search, int pageNo, int pageSize, String sortBy, String sortDir) {
        final var pageable = PageableConstants.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .sortDir(sortDir)
                .sortBy(sortBy)
                .build();

        final var result = repository.findAllBySearch(search, pageable);

        return AmlResponse.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .last(result.isLast())
                .totalPages(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .content(mapper.toDTOList(result.getContent()))
                .build();
    }

    @Override
    public String handleScenarioComment(Long id, String comment) {
        var scenario = repository.findById(id)
                .orElseThrow(() -> new NoRecordFoundException(ApiErrorMessage.NO_RECORD_FOUND.getErrorMessage()));
        scenario.setComment(comment);
        scenario.setHasComment(true);
        repository.save(scenario);
        return "Comment saved.";
    }

    @Override
    public byte[] convertAmlScenarios() throws Exception {
        var query = "select id as Id, code_senario as \"Code Senario\" , nom_senario as \"Nom Senario\" ,\n" +
                "       description_senario as \"Description Senario\", matricule as \"Matricule\" ,\n" +
                "       raison_sociale as \"Raison Sociale\" , score as \"Score\" ,\n" +
                "       alert_key as \"Alert Key\" , policy_key as \"Policy Key\" ,\n" +
                "       complexite as \"Complexite\"\n" +
                "from aml_senario";
        final var result = pageableQuery(query);
        return excelService.toExcel(result, "Scenarios");
    }

    @Override
    public byte[] convertAmlSummaries() throws Exception {
        var query = "select id as Id, code_senario as \"Code Senario\" , nom_senario as \"Nom Senario\" ,\n" +
                "       description_senario as \"Description Senario\", matricule as \"Matricule\" ,\n" +
                "       raison_sociale as \"Raison Sociale\" , score_totale as \"Score Totale\" ,\n" +
                "       score_moyen as \"Score Moyen\" , nombre_alert as \"Nombre Alert\" ,\n" +
                "       complexite as \"Complexite\"\n" +
                "from aml_summary";
        final var result = pageableQuery(query);
        return excelService.toExcel(result, "Summaries");
    }

    @Override
    public AmlResponse allAmlSummaries(String scenario, String search, int pageNo, int pageSize, String sortBy, String sortDir) {
        final var pageable = PageableConstants.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .sortDir(sortDir)
                .sortBy(sortBy)
                .build();

        final var result = summaryRepository.findAllBySearch(
                StringUtils.hasText(scenario) ? scenario : null,
                StringUtils.hasText(search) ? search : null, pageable
        );

        return AmlResponse.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .last(result.isLast())
                .totalPages(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .content(mapper.toSummaryDTOList(result.getContent()))
                .build();
    }

    @Override
    public List<Map<String, Object>> findAmlDistinctSummaries() {
        final var query = "select distinct s.code_senario as code,s.nom_senario as name from public.aml_summary s order by s.nom_senario";
        return pageableQuery(query);
    }

    @Override
    public Object findAmlClientBySubjectId(String subjectId) {
        final var query = String.format("select d.* from public.client d where d.matricule = '%s'",
                subjectId.toLowerCase()
        );
        return pageableQuerySingleResult(query);
    }

    @Override
    public Object findAmlScenarioDetail(String scenario, String alertKey) {
        final var query = String.format("select d.* from public.\"%s\" d where d.alert_key = '%s'",
                scenario.toLowerCase(), alertKey
        );
        return pageableQuerySingleResult(query);
    }

    @Override
    public AmlResponse findAmlTransactionBySubjectId(String subjectId, int pageNo, int pageSize, String sortBy, String sortDir) {
        final var pageable = PageableConstants.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .sortDir(sortDir)
                .sortBy(sortBy)
                .build();

        final var sql = String.format("select d.* from public.transaction d where d.matricule = '%s' order by niveau_risque",
                subjectId.toLowerCase()
        );
        final var result = pageableQuery(sql, pageable);

        return AmlResponse.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .last(result.isLast())
                .totalPages(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .graphs(graphTransactionBySubjectId(subjectId))
                .content(result.getContent())
                .build();
    }

    private Map<String, Object> graphTransactionBySubjectId(String subjectId) {
        var byDay = pageableQuery(String.format("SELECT date_operation, sum(montant) as total\n" +
                "FROM transaction\n" +
                "where matricule='%s'\n" +
                "group by 1\n" +
                "order by 1", subjectId.toLowerCase())
        );
        var byAvg = pageableQuery(String.format("SELECT extract(year from date_operation) as year,\n" +
                "       sum(case when moyen_paiement='VIREMENT       ' then montant else null end) as VIREMENT,\n" +
                "       sum(case when moyen_paiement='CHQUE     ' then montant else null end) as CHQUE\n" +
                "FROM transaction\n" +
                "where matricule='%s'\n" +
                "group by 1\n" +
                "order by 1,2", subjectId.toLowerCase())
        );
        var byRisk = pageableQuery(String.format("SELECT niveau_risque as risk_level, sum(montant) as total\n" +
                "FROM transaction\n" +
                "where matricule='%s' and niveau_risque!='N/A'\n" +
                "group by 1", subjectId.toLowerCase())
        );
        return new HashMap<>() {{
            put("by_day", byDay);
            put("by_avg", byAvg);
            put("by_risk", byRisk);
        }};
    }

    @Override
    public AmlResponse findAmlScenariosBySubjectId(String scenario, String subjectId, int pageNo, int pageSize, String sortBy, String sortDir) {
        final var pageable = PageableConstants.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .sortDir(sortDir)
                .sortBy(sortBy)
                .build();

        final var result = repository.findAllBySubjectIdAndScenarioCode(subjectId, scenario, pageable);

        return AmlResponse.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .last(result.isLast())
                .totalPages(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .content(mapper.toDTOList(result.getContent()))
                .build();
    }

    public Page<Map<String, Object>> pageableQuery(String sql, Pageable pageable) {
        final var query = em.createNativeQuery(sql, Tuple.class);
        int totalRows = query.getResultList().size();

        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        final var results = query.getResultList();

        return new PageImpl<Map<String, Object>>(convertTuplesToMap(results), pageable, totalRows);
    }

    public List<Map<String, Object>> pageableQuery(String sql) {
        final var query = em.createNativeQuery(sql, Tuple.class);

        final var results = query.getResultList();

        return convertTuplesToMap(results);
    }

    public Map<String, Object> pageableQuerySingleResult(String sql) {
        final var query = em.createNativeQuery(sql, Tuple.class);

        final List<Tuple> results = query.getResultList();

        return convertTupleToMap(results.stream().findFirst().orElse(null));
    }
}
