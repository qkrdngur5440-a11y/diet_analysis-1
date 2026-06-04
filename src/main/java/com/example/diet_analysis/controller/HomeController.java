package com.example.diet_analysis.controller;

import com.example.diet_analysis.model.DailyStats;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;

@RestController
public class HomeController {

    @GetMapping("/")
    public RedirectView index() {
        return new RedirectView("/index.html");
    }

    // 특정 날짜의 일일 통계 조회 (목표칼로리, 섭취, 소모, 순칼로리)
    @GetMapping("/daily-stats/{date}")
    public DailyStats getDailyStats(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);

        int goalCalories = 2000;        // 일일 목표 칼로리
        int consumedCalories = 1500 + (localDate.getDayOfMonth() % 5) * 100;
        int burnedCalories = 220 + (localDate.getDayOfWeek().getValue() * 40);

        if (localDate.equals(LocalDate.now())) {
            consumedCalories = 1800;
            burnedCalories = 500;
        }

        return new DailyStats(localDate, goalCalories, consumedCalories, burnedCalories);
    }
}
