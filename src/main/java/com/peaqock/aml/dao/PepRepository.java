package com.peaqock.aml.dao;

import com.peaqock.aml.domain.Pep;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PepRepository extends JpaRepository<Pep, Long> {

    @Query(value = "SELECT * FROM peps p where (levenshtein(name, :NAME) <=:ACCURACY or levenshtein(tl_name, :NAME)<= :ACCURACY)" +
            "and gender in ('MALE') "
//            "    and pep_type in (:PEPTYPES) " +
//            "    and entity_type in ('POLITICIAN')"
            , nativeQuery = true)
    Page<Pep> getAllBySearched(String NAME, int ACCURACY, Pageable pageRequest);


    @Query(value = "SELECT  " +
            "id , " +
            "name ," +
            "tl_name ," +
            " last_name ," +
            "last_names,\n" +
            "    given_names,\n" +
            "    alias_names,\n" +
            "    alias_given_names,\n" +
            "    gender,\n" +
            "    citizenship,\n" +
            "    nationality,\n" +
            "    address,\n" +
            "    pep_type,\n" +
            "    entity_type,\n" +
            "    source_type,\n" +
            "    date_of_birth,\n" +
            "    place_of_birth,\n" +
            "    siblings,\n" +
            "    spouse,\n" +
            "    description,\n" +
            "    occupations,\n" +
            "    titles,\n" +
            "    political_parties,\n" +
            "    sanction_details,\n" +
            "    citizenship_remarks,\n" +
            "    other_information,\n" +
            "    links ," +
            "    country ," +
            "    city ," +
            "    cin ," +
            "    pep_status , " +
            "    images , " +
            "    source_id , " +
            "    source_name , " +
            "    100 * (GREATEST(length(name),length(:NAME)) - levenshtein(lower(name),:NAME)) / GREATEST(length(name),length(:NAME)) as score " +
            "   FROM peps p where (levenshtein(lower(name), :NAME) <=:ACCURACY " +
            "   and  (100 * (GREATEST(length(name),length(:NAME)) - levenshtein(lower(name),:NAME)) / GREATEST(length(name),length(:NAME)) ) > 80  ) " +
            "   and lower(gender) in (lower(:GENDER) , lower('UNKNOWN') ) " +
            "   and entity_type in (:TYPE) " +
            "   and source_type in (:PepTYPES) " +
            "   and country like %:COUNTRY% " +
            "   and city like %:CITY% " +
            "   and cin like %:CIN% " +
            " order by levenshtein(lower(name),:NAME),score desc " +
            " LIMIT 300"
            , nativeQuery = true)
    List<Pep> getAllBySearch(String NAME, int ACCURACY, String GENDER, String CIN, String TYPE, String COUNTRY, String CITY, List<String> PepTYPES);


    @Query(value = "SELECT  " +
            "id , " +
            "name ," +
            "tl_name ," +
            " last_name ," +
            "last_names,\n" +
            "    given_names,\n" +
            "    alias_names,\n" +
            "    alias_given_names,\n" +
            "    gender,\n" +
            "    citizenship,\n" +
            "    nationality,\n" +
            "    address,\n" +
            "    pep_type,\n" +
            "    entity_type,\n" +
            "    source_type,\n" +
            "    date_of_birth,\n" +
            "    place_of_birth,\n" +
            "    siblings,\n" +
            "    spouse,\n" +
            "    description,\n" +
            "    occupations,\n" +
            "    titles,\n" +
            "    political_parties,\n" +
            "    sanction_details,\n" +
            "    citizenship_remarks,\n" +
            "    other_information,\n" +
            "    links ," +
            "    country ," +
            "    city ," +
            "    cin ," +
            "    pep_status , " +
            "    images , " +
            "    source_id , " +
            "    source_name , " +
            "    100 * (GREATEST(length(name),length(:NAME)) - levenshtein(lower(name),:NAME)) / GREATEST(length(name),length(:NAME)) as score " +
            "   FROM peps p where (levenshtein(lower(name), :NAME) <=:ACCURACY" +
            "   and  (100 * (GREATEST(length(name),length(:NAME)) - levenshtein(lower(name),:NAME)) / GREATEST(length(name),length(:NAME)) ) > 80  ) " +
            "   and lower(gender) in (lower(:GENDER) , lower('UNKNOWN') ) " +
            " order by levenshtein(lower(name),:NAME),score desc " +
            " LIMIT 300"
            , nativeQuery = true)
    List<Pep> getAllBySearchForBatch(String NAME, int ACCURACY, String GENDER);

}
