package com.peaqock.aml.utils.constants;

import lombok.Builder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * The Class PageableConstants.
 */
public class PageableConstants {


    public static final String DEFAULT_PAGE_NUMBER = "0";
    public static final String DEFAULT_PAGE_SIZE = "10";
    public static final String DEFAULT_SORT_BY = "id";
    public static final String DEFAULT_SORT_DIRECTION = "asc";

    private PageableConstants() {
    }

    @Builder
    public static Pageable pageable(int pageNo, int pageSize, String sortBy, String sortDir) {
        final var sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        // create Pageable instance
        return PageRequest.of(pageNo, pageSize, sort);
    }
}
