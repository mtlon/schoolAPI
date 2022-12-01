package com.SchoolAPI.SchoolAPI.school;

import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Repository;

@Getter
@Setter
@Table(name = "school")
@RequiredArgsConstructor
@Repository
public class School {
    private int school_Account = 5000;
}
