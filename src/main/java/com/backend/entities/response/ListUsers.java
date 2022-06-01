package com.backend.entities.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ListUsers {
    int page;
    int per_page;
    int total;
    int total_pages;
    List<User> data;
}
