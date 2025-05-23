package com.techBTG.testBTG.Controller.DTO;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public record ApiResponse<T>(Map<String, Object> summary,
                             List<T> data,
                             PaginationResponse pagination) {
}
