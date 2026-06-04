package com.example.diet_analysis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.diet_analysis.model.DailyStats;

import java.time.LocalDate;
import java.time.YearMonth;

@RestController
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "<html>" +
               "<head><title>Diet & Exercise Analysis Service</title></head>" +
               "<body style=\"font-family: Arial, sans-serif; margin: 40px; line-height: 1.6; max-width: 800px; color: #333;\">" +
               "  <h1 style=\"color: #0066cc;\">🟢 Diet & Exercise Analysis Service is Running</h1>" +
               "  <p>식단 및 운동 분석 서비스가 정상적으로 구동 중입니다.</p>" +
               "  <hr/>" +
               "  <h3>📌 API Endpoint 안내</h3>" +
               "  <h4>📸 식단 분석</h4>" +
               "  <ul>" +
               "    <li><strong>엔드포인트:</strong> <code>POST /api/analysis/photo</code></li>" +
               "    <li><strong>형식:</strong> <code>multipart/form-data</code></li>" +
               "    <li><strong>파라미터:</strong> <code>image</code> (이미지 파일)</li>" +
               "  </ul>" +
               "  <h4>💪 운동 기록</h4>" +
               "  <ul>" +
               "    <li><strong>운동 추가:</strong> <code>POST /api/exercise</code> (JSON)" +
               "      <br/>예: <code>{\"exerciseName\": \"달리기\", \"duration\": 30, \"caloriesBurned\": 300, \"date\": \"2026-06-04\", \"intensity\": \"중간\"}</code>" +
               "    </li>" +
               "    <li><strong>날짜별 운동 조회:</strong> <code>GET /api/exercise/date/{date}</code></li>" +
               "    <li><strong>날짜별 소모 칼로리:</strong> <code>GET /api/exercise/calories/date/{date}</code></li>" +
               "    <li><strong>모든 운동 기록:</strong> <code>GET /api/exercise</code></li>" +
               "    <li><strong>운동 기록 삭제:</strong> <code>DELETE /api/exercise?exerciseName=달리기&date=2026-06-04</code></li>" +
               "  </ul>" +
               "  <h4>📊 일일 통계</h4>" +
               "  <ul>" +
               "    <li><strong>일일 통계 조회:</strong> <code>GET /daily-stats/{date}</code></li>" +
               "    <li>예: <code>GET /daily-stats/2026-06-04</code></li>" +
               "    <li>응답: 목표칼로리, 섭취칼로리, 소모칼로리, 순칼로리, 피드백</li>" +
               "  </ul>" +
               "</body>" +
               "</html>";
    }

    // 특정 날짜의 일일 통계 조회 (목표칼로리, 섭취, 소모, 순칼로리)
    @GetMapping("/daily-stats/{date}")
    public DailyStats getDailyStats(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        
        // NOTE: 여기서는 고정값을 반환합니다. 실제로는 DB에서 조회하세요.
        int goalCalories = 2000;        // 일일 목표 칼로리
        int consumedCalories = 1800;    // 섭취 칼로리 (식단 분석에서 가져옴)
        int burnedCalories = 500;       // 소모 칼로리 (운동 기록에서 합계)
        
        return new DailyStats(localDate, goalCalories, consumedCalories, burnedCalories);
    }
}
